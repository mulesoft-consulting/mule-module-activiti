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

import org.activiti.engine.TaskService;
import org.activiti.engine.task.TaskQuery;
import org.mule.api.MuleEvent;


public class ListCandidateGroupTasksAction extends AbstractListTasksAction
{
    private String candidateGroupExpression;
    
    @Override
    protected TaskQuery createTaskQuery(TaskService taskService, MuleEvent event)
    {
        return taskService.createTaskQuery().taskCandidateGroup((String) this.getCandidateGroup(event));
    }
    
    private Object getCandidateGroup(MuleEvent event)
    {
        Object candidateGroup = event.getMuleContext().getExpressionManager()
            .evaluate(this.getCandidateGroupExpression(), event.getMessage());
        return candidateGroup;
    }

    public String getCandidateGroupExpression()
    {
        return candidateGroupExpression;
    }

    public void setCandidateGroupExpression(String candidateGroupExpression)
    {
        this.candidateGroupExpression = candidateGroupExpression;
    }
}
