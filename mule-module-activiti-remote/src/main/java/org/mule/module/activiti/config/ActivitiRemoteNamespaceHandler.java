/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.activiti.config;

import org.mule.config.spring.handlers.AbstractMuleNamespaceHandler;
import org.mule.config.spring.parsers.specific.MessageProcessorDefinitionParser;
import org.mule.module.activiti.ActivitiRemoteConnector;
import org.mule.module.activiti.action.remote.CreateProcessAction;
import org.mule.module.activiti.action.remote.GetRuntimeVariableOfProcess;
import org.mule.module.activiti.action.remote.GetTask;
import org.mule.module.activiti.action.remote.ListAssignedTasksAction;
import org.mule.module.activiti.action.remote.ListCandidateGroupTasksAction;
import org.mule.module.activiti.action.remote.ListCandidateTasksAction;
import org.mule.module.activiti.action.remote.ListProcessDefinitionsAction;
import org.mule.module.activiti.action.remote.PerformTaskOperationAction;
import org.mule.module.activiti.transformer.JsonToProcessDefinitions;
import org.mule.module.activiti.transformer.JsonToProcessInstance;
import org.mule.module.activiti.transformer.JsonToTask;
import org.mule.module.activiti.transformer.JsonToTasks;

/**
 * Registers a Bean Definition Parser for handling <code>&lt;activiti:connector&gt;</code> elements
 * and supporting endpoint elements.
 */
public class ActivitiRemoteNamespaceHandler extends AbstractMuleNamespaceHandler
{

    public void init()
    {
        registerConnectorDefinitionParser(ActivitiRemoteConnector.class);
        
        //ACTIVITI ACTIONS
        registerBeanDefinitionParser("list-process-definitions", new MessageProcessorDefinitionParser(ListProcessDefinitionsAction.class));
        registerBeanDefinitionParser("list-assigned-tasks", new MessageProcessorDefinitionParser(ListAssignedTasksAction.class));
        registerBeanDefinitionParser("list-candidate-tasks", new MessageProcessorDefinitionParser(ListCandidateTasksAction.class));
        registerBeanDefinitionParser("list-candidate-group-tasks", new MessageProcessorDefinitionParser(ListCandidateGroupTasksAction.class));
        registerBeanDefinitionParser("create-process", new MessageProcessorDefinitionParser(CreateProcessAction.class));
        registerBeanDefinitionParser("perform-task-operation", new MessageProcessorDefinitionParser(PerformTaskOperationAction.class));
        registerBeanDefinitionParser("get-runtime-variable-of-process", new MessageProcessorDefinitionParser(GetRuntimeVariableOfProcess.class));
        registerBeanDefinitionParser("get-task", new MessageProcessorDefinitionParser(GetTask.class));

        //TRANSFORMERS
        registerBeanDefinitionParser("json-to-process-definitions", new MessageProcessorDefinitionParser(JsonToProcessDefinitions.class));
        registerBeanDefinitionParser("json-to-tasks", new MessageProcessorDefinitionParser(JsonToTasks.class));
        registerBeanDefinitionParser("json-to-task", new MessageProcessorDefinitionParser(JsonToTask.class));
        registerBeanDefinitionParser("json-to-process-instance", new MessageProcessorDefinitionParser(JsonToProcessInstance.class));
    }
}
