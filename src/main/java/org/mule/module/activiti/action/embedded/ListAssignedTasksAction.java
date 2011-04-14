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

import org.activiti.engine.TaskService;
import org.activiti.engine.task.TaskQuery;


public class ListAssignedTasksAction extends AbstractListTasksAction
{
    private String userExpression;
    
    @Override
    protected TaskQuery createTaskQuery(TaskService taskService, MuleEvent event)
    {
        return taskService.createTaskQuery().taskAssignee((String) this.getUser(event));
    }
    
    private Object getUser(MuleEvent event)
    {
        Object user = event.getMuleContext().getExpressionManager()
            .evaluate(this.getUserExpression(), event.getMessage());
        return user;
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
