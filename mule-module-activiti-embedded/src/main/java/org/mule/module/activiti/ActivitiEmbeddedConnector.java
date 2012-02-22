/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.transport.AbstractConnector;


public class ActivitiEmbeddedConnector extends AbstractConnector implements ActivitiConnector
{
    public static final String ACTIVITI_EMBEDDED = "activiti-embedded";

    private String version;

    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;
    private HistoryService historyService;

    private SpringProcessEngineConfiguration processEngineConfiguration;
    
    public ActivitiEmbeddedConnector(MuleContext context)
    {
        super(context);
    }
    
    @Override
    protected void doConnect() throws Exception
    {
        // DO NOTHING
    }

    @Override
    protected void doDisconnect() throws Exception
    {
        // DO NOTHING
    }

    @Override
    protected void doDispose()
    {
        // DO NOTHING
    }

    @Override
    protected void doInitialise() throws InitialisationException
    {
        ProcessEngine engine = processEngineConfiguration.buildProcessEngine(); 
        repositoryService = engine.getRepositoryService();
        runtimeService = engine.getRuntimeService();
        taskService = engine.getTaskService();
        historyService = engine.getHistoryService();        
    }

    @Override
    protected void doStart() throws MuleException
    {
        // DO NOTHING
    }

    @Override
    protected void doStop() throws MuleException
    {
        // DO NOTHING
    }
    
    /**
     * {@inheritDoc}
     */
    public String getProtocol()
    {
        return ACTIVITI_EMBEDDED;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public RepositoryService getRepositoryService()
    {
        return repositoryService;
    }

    public void setRepositoryService(RepositoryService repositoryService)
    {
        this.repositoryService = repositoryService;
    }

    public RuntimeService getRuntimeService()
    {
        return this.runtimeService;
    }

    public void setRuntimeService(RuntimeService runtimeService)
    {
        this.runtimeService = runtimeService;
    }

    public TaskService getTaskService()
    {
        return taskService;
    }

    public void setTaskService(TaskService taskService)
    {
        this.taskService = taskService;
    }

    public HistoryService getHistoryService()
    {
        return historyService;
    }

    public void setHistoryService(HistoryService historyService)
    {
        this.historyService = historyService;
    }

    public SpringProcessEngineConfiguration getProcessEngineConfiguration() 
    { 
        return this.processEngineConfiguration; 
    }

    public void setprocessEngineConfiguration(SpringProcessEngineConfiguration processEngineConfiguration ) 
    { 
        this.processEngineConfiguration = processEngineConfiguration; 
    }
    
}


