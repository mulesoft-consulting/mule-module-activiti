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

public class SetVariableAction extends AbstractEmbeddedActivitiAction
{
    private String executionIdExpression;
    private String variableExpression;
    private String valueExpression;
    
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        String executionId = (String) event.getMuleContext().getExpressionManager().evaluate(this.executionIdExpression, event.getMessage());
        
        String variableName = (String) event.getMuleContext().getExpressionManager().evaluate(this.variableExpression, event.getMessage());
        Object value = event.getMuleContext().getExpressionManager().evaluate(this.valueExpression, event.getMessage());
        
        this.getRuntimeService().setVariable(executionId, variableName, value);
        
        return event;
    }

    public String getVariableExpression()
    {
        return variableExpression;
    }

    public void setVariableExpression(String variableExpression)
    {
        this.variableExpression = variableExpression;
    }

    public String getValueExpression()
    {
        return valueExpression;
    }

    public void setValueExpression(String valueExpression)
    {
        this.valueExpression = valueExpression;
    }

    public String getExecutionIdExpression()
    {
        return executionIdExpression;
    }

    public void setExecutionIdExpression(String executionIdExpression)
    {
        this.executionIdExpression = executionIdExpression;
    }
}
