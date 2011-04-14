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
import org.mule.module.activiti.action.model.Operation;

public class PerformTaskOperationAction extends AbstractEmbeddedActivitiAction
{
    private String taskIdExpression;
    private String userExpression;
    private Operation operation;

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        String taskId = (String) event.getMuleContext().getExpressionManager().evaluate(this.taskIdExpression, event.getMessage());
        String user = (String) event.getMuleContext().getExpressionManager().evaluate(this.userExpression, event.getMessage());
        switch (this.operation)
        {
            case CLAIM :
                this.getTaskService().claim(taskId, user);
                break;
            case COMPLETE :
                this.getTaskService().complete(taskId);
                break;
            default :
                break;
        }
        return null;
    }
    
    public Operation getOperation()
    {
        return operation;
    }

    public void setOperation(Operation operation)
    {
        this.operation = operation;
    }

    public String getTaskIdExpression()
    {
        return taskIdExpression;
    }

    public void setTaskIdExpression(String taskIdExpression)
    {
        this.taskIdExpression = taskIdExpression;
    }

    public String getUserExpression()
    {
        return userExpression;
    }

    public void setUserExpression(String userExpression)
    {
        this.userExpression = userExpression;
    }
}
