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

import org.mule.api.processor.MessageProcessor;
import org.mule.module.activiti.ActivitiEmbeddedConnector;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

public abstract class AbstractEmbeddedActivitiAction implements MessageProcessor
{

    private ActivitiEmbeddedConnector connector;

    public RepositoryService getRepositoryService() {
        return this.getConnector().getRepositoryService();
    }
    
    public RuntimeService getRuntimeService() {
        return this.getConnector().getRuntimeService();
    }

    public TaskService getTaskService() {
        return this.getConnector().getTaskService();
    }

    public ActivitiEmbeddedConnector getConnector()
    {
        return connector;
    }

    public void setConnector(ActivitiEmbeddedConnector connector)
    {
        this.connector = connector;
    }
}


