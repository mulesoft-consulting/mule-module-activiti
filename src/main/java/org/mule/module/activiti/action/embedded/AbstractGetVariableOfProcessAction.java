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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractGetVariableOfProcessAction implements MessageProcessor
{
    protected static final Log logger = LogFactory.getLog(AbstractGetVariableOfProcessAction.class);

    private String processInstanceIdExpression;
    private String variableExpression;

    public String getProcessInstanceIdExpression()
    {
        return processInstanceIdExpression;
    }

    public void setProcessInstanceIdExpression(String processInstanceIdExpression)
    {
        this.processInstanceIdExpression = processInstanceIdExpression;
    }

    public String getVariableExpression()
    {
        return variableExpression;
    }

    public void setVariableExpression(String variableExpression)
    {
        this.variableExpression = variableExpression;
    }
}
