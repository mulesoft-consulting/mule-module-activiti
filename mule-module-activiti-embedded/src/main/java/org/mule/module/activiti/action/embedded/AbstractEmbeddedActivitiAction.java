/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.activiti.action.embedded;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.module.activiti.ActivitiEmbeddedConnector;
import org.mule.module.activiti.i18n.ActivitiMessages;

public abstract class AbstractEmbeddedActivitiAction implements MessageProcessor, MuleContextAware
{

    protected static final Log logger = LogFactory.getLog(AbstractEmbeddedActivitiAction.class);
    
    private ActivitiEmbeddedConnector connector;

    /**
     * {@inheritDoc}
     */
    public void setMuleContext(MuleContext context)
    {
        if (this.connector == null)
        {
            try
            {
                this.connector = context.getRegistry().lookupObject(ActivitiEmbeddedConnector.class);
            }
            catch (RegistrationException e)
            {
                logger.warn(ActivitiMessages.noConnectorOrMultipleDefined());
            }
        }
    }
    
    public RepositoryService getRepositoryService()
    {
        return this.getConnector().getRepositoryService();
    }

    public RuntimeService getRuntimeService()
    {
        return this.getConnector().getRuntimeService();
    }

    public TaskService getTaskService()
    {
        return this.getConnector().getTaskService();
    }

    public HistoryService getHistoryService()
    {
        return this.getConnector().getHistoryService();
    }

    public ActivitiEmbeddedConnector getConnector()
    {
        return connector;
    }

    public void setConnector(ActivitiEmbeddedConnector connector)
    {
        this.connector = connector;
    }
}
