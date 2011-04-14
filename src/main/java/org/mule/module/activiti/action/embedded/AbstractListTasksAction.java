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
import org.mule.module.activiti.util.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

public abstract class AbstractListTasksAction extends AbstractEmbeddedActivitiAction
{
    private int start;
    private int size;
    
    public MuleEvent process(MuleEvent event)
    {
        TaskService taskService = this.getTaskService();
        TaskQuery taskQuery = this.createTaskQuery(taskService, event);
        List<Task> tasks = taskQuery.listPage(this.start, this.size);
        List<org.mule.module.activiti.action.model.Task> result = new ArrayList<org.mule.module.activiti.action.model.Task>();
        
        for (Task task : tasks) {
            org.mule.module.activiti.action.model.Task t = new org.mule.module.activiti.action.model.Task();
            BeanUtils.safeCopyProperties(t, task);
            result.add(t);
        }
        
        event.getMessage().setPayload(result);
        return event;
    }
    
    protected abstract TaskQuery createTaskQuery(TaskService taskService, MuleEvent event);

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
