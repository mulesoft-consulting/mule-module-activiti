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
import org.mule.module.activiti.ActivitiEmbeddedConnector;
import org.mule.module.activiti.action.common.GetHistoricVariableOfProcess;
import org.mule.module.activiti.action.common.GetLatestHistoricVariableOfProcess;
import org.mule.module.activiti.action.embedded.CreateProcessAction;
import org.mule.module.activiti.action.embedded.GetRuntimeVariableOfProcess;
import org.mule.module.activiti.action.embedded.GetTask;
import org.mule.module.activiti.action.embedded.ListAssignedTasksAction;
import org.mule.module.activiti.action.embedded.ListCandidateGroupTasksAction;
import org.mule.module.activiti.action.embedded.ListCandidateTasksAction;
import org.mule.module.activiti.action.embedded.ListProcessDefinitionsAction;
import org.mule.module.activiti.action.embedded.PerformTaskOperationAction;

/**
 * Registers a Bean Definition Parser for handling <code>&lt;activiti:connector&gt;</code> elements
 * and supporting endpoint elements.
 */
public class ActivitiEmbeddedNamespaceHandler extends AbstractMuleNamespaceHandler
{

    public void init()
    {
        registerConnectorDefinitionParser(ActivitiEmbeddedConnector.class);
        
        //ACTIVITI ACTIONS
        registerBeanDefinitionParser("list-process-definitions", new MessageProcessorDefinitionParser(ListProcessDefinitionsAction.class));
        registerBeanDefinitionParser("list-assigned-tasks", new MessageProcessorDefinitionParser(ListAssignedTasksAction.class));
        registerBeanDefinitionParser("list-candidate-tasks", new MessageProcessorDefinitionParser(ListCandidateTasksAction.class));
        registerBeanDefinitionParser("list-candidate-group-tasks", new MessageProcessorDefinitionParser(ListCandidateGroupTasksAction.class));
        registerBeanDefinitionParser("create-process", new MessageProcessorDefinitionParser(CreateProcessAction.class));
        registerBeanDefinitionParser("perform-task-operation", new MessageProcessorDefinitionParser(PerformTaskOperationAction.class));
        registerBeanDefinitionParser("get-runtime-variable-of-process", new MessageProcessorDefinitionParser(GetRuntimeVariableOfProcess.class));
        registerBeanDefinitionParser("get-historic-variable-of-process", new MessageProcessorDefinitionParser(GetHistoricVariableOfProcess.class));
        registerBeanDefinitionParser("get-latest-historic-variable-of-process", new MessageProcessorDefinitionParser(GetLatestHistoricVariableOfProcess.class));
        registerBeanDefinitionParser("get-task", new MessageProcessorDefinitionParser(GetTask.class));
    }
}
