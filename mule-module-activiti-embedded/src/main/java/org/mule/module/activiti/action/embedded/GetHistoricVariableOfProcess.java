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

import org.activiti.engine.history.HistoricDetailQuery;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.registry.RegistrationException;
import org.mule.module.activiti.ActivitiEmbeddedConnector;
import org.mule.module.activiti.i18n.ActivitiMessages;

//GET_HISTORY_VARIABLE_QUERY = "SELECT * FROM act_hi_detail WHERE PROC_INST_ID_ = ? AND NAME_ = ? AND ACT_INST_ID_ = ?";

public class GetHistoricVariableOfProcess extends AbstractGetVariableOfProcessAction
{
    private String activitiInstanceIdExpression;

    public void setMuleContext(MuleContext context)
    {
        if (getConnector() == null)
        {
            try
            {
                super.setConnector((ActivitiEmbeddedConnector)context.getRegistry().lookupObject(ActivitiEmbeddedConnector.class));
            }
            catch (RegistrationException e)
            {
                logger.warn(ActivitiMessages.noConnectorOrMultipleDefined());
            }
        }
    }

    @Override
    protected HistoricDetailQuery getQuery(MuleEvent event) {
        ActivitiEmbeddedConnector embeddedConnector = (ActivitiEmbeddedConnector)super.getConnector();
        return embeddedConnector.getHistoryService().createHistoricDetailQuery().variableUpdates()
                            .processInstanceId(super.getProcessInstanceId(event))
                            .activityInstanceId(getActivitiInstanceId(event));
    }
    
    private String getActivitiInstanceId(MuleEvent event)
    {
        return event.getMuleContext().getExpressionManager().evaluate(
            this.activitiInstanceIdExpression, event.getMessage()).toString();
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


