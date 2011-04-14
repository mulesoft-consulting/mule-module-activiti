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

public class GetHistoricVariableOfProcess extends AbstractGetVariableOfProcessAction
{
    private String activitiInstanceIdExpression;

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        // TODO Auto-generated method stub
        return null;
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


