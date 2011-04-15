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

public class ListCandidateTasksAction extends AbstractListTasksAction
{
    private String userExpression;
    
    @Override
    protected void appendType(StringBuffer uri, MuleEvent event)
    {
        if (this.getUserExpression() != null)
        {
            Object user = this.getUser(event);
            uri.append("candidate=");
            uri.append(user);
        }
    }
    
    private Object getUser(MuleEvent event)
    {
        Object user = event.getMuleContext().getExpressionManager()
            .evaluate(this.getUserExpression(), event.getMessage());
        return user;
    }

    public String getUserExpression()
    {
        return userExpression;
    }

    public void setUserExpression(String userExpression)
    {
        this.userExpression = userExpression;
    }
}
