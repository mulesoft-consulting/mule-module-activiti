/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.activiti.action.remote;

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.MuleRuntimeException;
import org.mule.api.context.MuleContextAware;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.module.activiti.ActivitiRemoteConnector;
import org.mule.module.activiti.i18n.ActivitiMessages;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractRemoteActivitiAction<V extends HttpMethod> implements MessageProcessor, MuleContextAware
{

    protected static final Log logger = LogFactory.getLog(AbstractRemoteActivitiAction.class);
    private ActivitiRemoteConnector connector;

    /**
     * {@inheritDoc}
     */
    public void setMuleContext(MuleContext context)
    {
        if (this.connector == null)
        {
            try
            {
                this.connector = context.getRegistry().lookupObject(ActivitiRemoteConnector.class);
            }
            catch (RegistrationException e)
            {
                logger.warn(ActivitiMessages.noConnectorOrMultipleDefined());
            }
        }
    }
    
    /**
     * @return
     */
    protected abstract V getMethod();

    /**
     * @param event
     * @return
     */
    protected abstract String getRelativeUrl(MuleEvent event);
    
    /**
     * @param method
     * @param message
     */
    protected abstract void prepareMethod(V method, MuleMessage message) throws Exception;
    
    /**
     * {@inheritDoc}
     */
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        V method = null;
        HttpClient client = connector.createClient();
        try
        {
            method = this.getMethod();
            connector.prepareMethod(method, client);
            URI methodURI = new URI(new URI(connector.getActivitiServerURL(), false), this.getRelativeUrl(event));
            method.setURI(methodURI);
            this.prepareMethod(method, event.getMessage());
            client.executeMethod(method);
            String response = method.getResponseBodyAsString();
            this.processResponse(event, response);
        }
        catch (Exception e)
        {
            throw new MuleRuntimeException(ActivitiMessages.failToExecuteAction(this), e);
        }
        finally
        {
            method.releaseConnection();
        }
        return event;
    }
    
    protected void processResponse(MuleEvent event, String response)
    {
        event.getMessage().setPayload(response);
    }

    public ActivitiRemoteConnector getConnector()
    {
        return connector;
    }

    public void setConnector(ActivitiRemoteConnector connector)
    {
        this.connector = connector;
    }
}
