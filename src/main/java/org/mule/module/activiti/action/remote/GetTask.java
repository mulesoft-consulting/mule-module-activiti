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

import org.apache.commons.httpclient.methods.GetMethod;

public class GetTask extends AbstractRemoteActivitiAction<GetMethod>
{
    private String taskIdExpression;

    @Override
    protected GetMethod getMethod()
    {
        return new GetMethod();
    }

    @Override
    protected String getRelativeUrl(MuleEvent event)
    {
        String taskId = (String) event.getMuleContext().getExpressionManager().evaluate(this.taskIdExpression, event.getMessage());
        return "task/" + taskId;
    }

    @Override
    protected void prepareMethod(GetMethod method, MuleMessage message) throws Exception
    {
        // DO NOTHING
    }

    public String getTaskIdExpression()
    {
        return taskIdExpression;
    }

    public void setTaskIdExpression(String taskIdExpression)
    {
        this.taskIdExpression = taskIdExpression;
    }
}


