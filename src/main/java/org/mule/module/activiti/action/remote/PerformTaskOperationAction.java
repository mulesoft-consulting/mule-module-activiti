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
import org.mule.module.activiti.action.model.Operation;

import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class PerformTaskOperationAction extends AbstractRemoteActivitiAction<PutMethod>
{
    private String taskIdExpression;
    private String userExpression;
    private Operation operation;

    public MuleEvent processEmbedded(ActivitiEmbeddedConnector connector, MuleEvent event)
    {
        String taskId = (String) event.getMuleContext().getExpressionManager().evaluate(this.taskIdExpression, event.getMessage());
        String user = (String) event.getMuleContext().getExpressionManager().evaluate(this.userExpression, event.getMessage());
        switch (this.operation)
        {
            case CLAIM :
                connector.getTaskService().claim(taskId, user);
                break;
            case COMPLETE :
                connector.getTaskService().complete(taskId);
                break;
            default :
                break;
        }
        return null;
    }
    
    @Override
    protected void processResponse(MuleEvent event, String response)
    {
        //DO NOTHING
    }
    
    /**
     * {@inheritDoc}
     */
    protected void prepareMethod(PutMethod method, MuleMessage message) throws Exception
    {
        RequestEntity requestEntity = new StringRequestEntity("{}", "application/json", "UTF-8");
        method.setRequestEntity(requestEntity);
    }

    /**
     * {@inheritDoc}
     */
    protected String getRelativeUrl(MuleEvent event)
    {
        String taskId = (String) event.getMuleContext().getExpressionManager().evaluate(this.taskIdExpression, event.getMessage());
        return "task/" + taskId + "/" + this.getOperation();
    }

    /**
     * {@inheritDoc}
     */
    public PutMethod getMethod()
    {
        return new PutMethod();
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
