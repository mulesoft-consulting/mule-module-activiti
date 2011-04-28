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

import org.activiti.engine.RuntimeService;

public class SignalAction extends AbstractEmbeddedActivitiAction
{
    private String executionIdExpression;

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        String executionId = (String) event.getMuleContext().getExpressionManager().evaluate(this.executionIdExpression, event.getMessage());
        RuntimeService service = this.getRuntimeService();
        service.signal(executionId);
        return event;
    }

    public String getExecutionIdExpression()
    {
        return executionIdExpression;
    }

    public void setExecutionIdExpression(String executionIdExpression)
    {
        this.executionIdExpression = executionIdExpression;
    }

}


