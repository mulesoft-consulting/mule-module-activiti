/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.activiti.action.common;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleRuntimeException;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.activiti.ActivitiConnector;
import org.mule.module.activiti.ActivitiRemoteConnector;
import org.mule.module.activiti.i18n.ActivitiMessages;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractGetVariableOfProcessAction implements MessageProcessor
{
    protected static final Log logger = LogFactory.getLog(AbstractGetVariableOfProcessAction.class);

    private ActivitiConnector connector;
    private String processInstanceIdExpression;
    private String variableExpression;
    private String query;

    protected abstract String primGetQuery();

    protected abstract String getTypeColumn();

    protected Object[] getParameters(MuleEvent event) {
        String processInstanceId = this.getProcessInstanceId(event);
        String variable = this.getVariable(event);
        
        return new Object[]{processInstanceId, variable};
    }

    private String getVariable(MuleEvent event)
    {
        return (String) event.getMuleContext().getExpressionManager().evaluate(
            this.variableExpression, event.getMessage());
    }

    private String getProcessInstanceId(MuleEvent event)
    {
        return (String) event.getMuleContext().getExpressionManager().evaluate(
            this.processInstanceIdExpression, event.getMessage());
    }

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        try
        {
            Map result = (Map) this.getConnector().getJdbcConnector().getQueryRunner().query(this.getQuery(),
                new MapHandler(), this.getParameters(event));

            if (result == null)
            {
                event.getMessage().setPayload(null);
            }
            else
            {
                Object payload = null;
                String typeColumn = this.getTypeColumn();
                if (((String) result.get(typeColumn)).equalsIgnoreCase("string"))
                {
                    payload = result.get("TEXT_");
                }
                else if (((String) result.get(typeColumn)).equalsIgnoreCase("double"))
                {
                    payload = result.get("DOUBLE_");
                }
                else if (((String) result.get(typeColumn)).equalsIgnoreCase("long"))
                {
                    payload = result.get("LONG_");
                }
                else if (((String) result.get(typeColumn)).equalsIgnoreCase("integer"))
                {
                    payload = ((Long) result.get("LONG_")).intValue();
                }
                event.getMessage().setPayload(payload);
            }
            
            return event;
        }
        catch (SQLException e)
        {
            throw new MuleRuntimeException(ActivitiMessages.failtToGetVariable(), e);
        }
    }

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

    public String getQuery()
    {
        if (this.query == null) {
            this.query = this.primGetQuery();
        }
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public ActivitiConnector getConnector()
    {
        return connector;
    }

    public void setConnector(ActivitiConnector connector)
    {
        this.connector = connector;
    }
}
