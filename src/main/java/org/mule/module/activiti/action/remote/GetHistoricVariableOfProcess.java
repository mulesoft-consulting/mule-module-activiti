/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.activiti.action.remote;

import org.mule.api.MuleEvent;

import java.util.Arrays;

public class GetHistoricVariableOfProcess extends AbstractGetVariableOfProcessAction
{
    public static final String GET_HISTORY_VARIABLE_QUERY = "SELECT * FROM act_hi_detail WHERE PROC_INST_ID_ = ? AND NAME_ = ? AND ACT_INST_ID_ = ?";

    private String activitiInstanceIdExpression;
    
    @Override
    protected String primGetQuery()
    {
        return GET_HISTORY_VARIABLE_QUERY;
    }
    
    @Override
    protected String getTypeColumn()
    {
        return "VAR_TYPE_";
    }

    @Override
    protected Object[] getParameters(MuleEvent event)
    {
        Integer revision = (Integer) event.getMuleContext().getExpressionManager().evaluate(
            this.activitiInstanceIdExpression, event.getMessage());
        
        Object[] parameters = super.getParameters(event);
        parameters = Arrays.copyOf(parameters, parameters.length + 1);
        parameters[parameters.length - 1] = revision;
        return parameters;
    }

    public String getActivitiInstanceIdExpression()
    {
        return activitiInstanceIdExpression;
    }

    public void setActivitiInstanceIdExpression(String activitiInstanceIdExpression)
    {
        this.activitiInstanceIdExpression = activitiInstanceIdExpression;
    }
}


