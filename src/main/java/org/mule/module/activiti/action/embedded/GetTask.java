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
import org.mule.module.activiti.util.BeanUtils;

import org.activiti.engine.task.Task;

public class GetTask extends AbstractEmbeddedActivitiAction
{
    private String taskIdExpression;

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        String taskId = (String) event.getMuleContext().getExpressionManager().evaluate(this.taskIdExpression, event.getMessage());
        Task task = (Task) this.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        org.mule.module.activiti.action.model.Task copyTask = new org.mule.module.activiti.action.model.Task();
        BeanUtils.safeCopyProperties(copyTask, task);
        event.getMessage().setPayload(copyTask);
        return event;
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


