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

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.module.activiti.util.BeanUtils;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CreateProcessAction extends AbstractEmbeddedActivitiAction
{
    protected final Log logger = LogFactory.getLog(getClass());
    
    private String parametersExpression;

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        Map parameters = this.getParametersMap(event.getMessage());
        ProcessInstance processInstance;
        if (parameters.containsKey("processDefinitionKey"))
        {
            String key = (String) parameters.get("processDefinitionKey");
            Map parametersClone = new HashMap(parameters);
            parametersClone.remove("processDefinitionKey");
            processInstance = this.getRuntimeService().startProcessInstanceByKey(key, parametersClone);
        }
        else
        {
            String id = (String) parameters.get("processDefinitionId");
            Map parametersClone = new HashMap(parameters);
            parametersClone.remove("processDefinitionId");
            processInstance = this.getRuntimeService().startProcessInstanceById(id, parametersClone);
        }
        
        org.mule.module.activiti.action.model.ProcessInstance instance = new org.mule.module.activiti.action.model.ProcessInstance();
        
        BeanUtils.safeCopyProperties(instance, processInstance);
        
        event.getMessage().setPayload(instance);
        
        return event;
    }

    private Map getParametersMap(MuleMessage message)
    {
        Object parameters = message.getMuleContext().getExpressionManager().evaluate(
            this.parametersExpression, message);
        if (parameters instanceof Map)
        {
            return (Map) parameters;
        }
        else
        {
            throw new IllegalStateException("A map is expected in the parameters expression");
        }
    }

    public String getParametersExpression()
    {
        return parametersExpression;
    }

    public void setParametersExpression(String parametersExpression)
    {
        this.parametersExpression = parametersExpression;
    }
}
