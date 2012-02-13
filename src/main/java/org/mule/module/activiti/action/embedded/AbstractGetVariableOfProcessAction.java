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

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleRuntimeException;
import org.mule.api.context.MuleContextAware;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.module.activiti.ActivitiConnector;
import org.mule.module.activiti.i18n.ActivitiMessages;

import java.util.List;
import java.util.Iterator;

import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.activiti.engine.history.HistoricDetailQuery; 
import org.activiti.engine.history.HistoricVariableUpdate; 
import org.activiti.engine.history.HistoricDetail; 

 
public abstract class AbstractGetVariableOfProcessAction implements MessageProcessor, MuleContextAware
{
    protected static final Log logger = LogFactory.getLog(AbstractGetVariableOfProcessAction.class);

    private ActivitiConnector connector;
    private String processInstanceIdExpression;
    private String variableExpression;

    protected abstract HistoricDetailQuery getQuery(MuleEvent event);
    
    @Override
    public MuleEvent process(MuleEvent event) throws MuleException
     {
         try {
            event.getMessage().setPayload(null);
            
            HistoricDetailQuery query = getQuery(event);
            List<HistoricDetail> updatesList = query.list();
            
            Iterator updatesIterator = updatesList.iterator();

            boolean found = false;
            
            while (updatesIterator.hasNext() && !found) {
                HistoricVariableUpdate update = (HistoricVariableUpdate)updatesIterator.next();

                if (update.getVariableName().equals(getVariable(event))) {
                    event.getMessage().setPayload(update.getValue());
                    found = true;
                }    
            }
            
            return event;
         } catch (Exception e) {
             throw new MuleRuntimeException(ActivitiMessages.failtToGetVariable(), e);
         }
    }
    
    /**
     * {@inheritDoc}
     */
    protected String getVariable(MuleEvent event)
    {
        return (String) event.getMuleContext().getExpressionManager().evaluate(
            this.variableExpression, event.getMessage());
    }

    protected String getProcessInstanceId(MuleEvent event)
    {
        return (String) event.getMuleContext().getExpressionManager().evaluate(
            this.processInstanceIdExpression, event.getMessage());
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

    public ActivitiConnector getConnector()
    {
        return connector;
    }

    public void setConnector(ActivitiConnector connector)
    {
        this.connector = connector;
    }
}
