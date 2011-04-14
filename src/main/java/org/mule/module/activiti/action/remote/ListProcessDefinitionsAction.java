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

import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.module.activiti.ActivitiEmbeddedConnector;
import org.mule.module.activiti.util.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListProcessDefinitionsAction extends AbstractRemoteActivitiAction<GetMethod>
{
    protected final Log logger = LogFactory.getLog(getClass());

    public MuleEvent processEmbedded(ActivitiEmbeddedConnector connector, MuleEvent event)
    {
        List<ProcessDefinition> definitions = connector.getRepositoryService()
            .createProcessDefinitionQuery()
            .list();
        List<org.mule.module.activiti.action.model.ProcessDefinition> result = new ArrayList<org.mule.module.activiti.action.model.ProcessDefinition>();
        
        for (ProcessDefinition definition : definitions)
        {
            org.mule.module.activiti.action.model.ProcessDefinition def = new org.mule.module.activiti.action.model.ProcessDefinition();
            BeanUtils.safeCopyProperties(def, definition);
            result.add(def);
        }
        
        event.getMessage().setPayload(result);
        return event;
    }

    /**
     * {@inheritDoc}
     */
    protected String getRelativeUrl(MuleEvent event)
    {
        return "process-definitions";
    }

    /**
     * {@inheritDoc}
     */
    public GetMethod getMethod()
    {
        return new GetMethod();
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareMethod(GetMethod method, MuleMessage message)
    {
        // DO NOTHING
    }
}
