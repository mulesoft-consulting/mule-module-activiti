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

public class GetRuntimeVariableOfProcess extends AbstractEmbeddedActivitiAction
{

    private String processInstanceIdExpression;
    private String variableExpression;
    
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        String processInstanceId = this.getProcessInstanceId(event);
        String variable = this.getVariable(event);
        Object object = this.getRuntimeService().getVariable(processInstanceId, variable);
        event.getMessage().setPayload(object);
        return event;
    }

    private String getVariable(MuleEvent event)
    {
        return (String) event.getMuleContext().getExpressionManager().evaluate(
            this.variableExpression, event.getMessage());
    }

    private String getProcessInstanceId(MuleEvent event)
    {
        return (String) event.getMuleContext().getExpressionManager().evaluate(
            this.processInstanceIdExpression, event.getMessage());
    }

    public String getProcessInstanceIdExpression()
    {
        return processInstanceIdExpression;
    }

    public void setProcessInstanceIdExpression(String processInstanceIdExpression)
    {
        this.processInstanceIdExpression = processInstanceIdExpression;
    }

    public String getVariableExpression()
    {
        return variableExpression;
    }

    public void setVariableExpression(String variableExpression)
    {
        this.variableExpression = variableExpression;
    }
}


