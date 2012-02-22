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

import org.apache.commons.httpclient.methods.GetMethod;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;

public abstract class AbstractListTasksAction extends AbstractRemoteActivitiAction<GetMethod>
{
    private int start;
    private int size;
    
    /**
     * {@inheritDoc}
     */
    protected void prepareMethod(GetMethod method, MuleMessage message)
    {
        // DO NOTHING
    }

    /**
     * {@inheritDoc}
     */
    protected String getRelativeUrl(MuleEvent event)
    {
        StringBuffer uri = new StringBuffer();
        uri.append("tasks?");
        this.appendType(uri, event);

        uri.append("&start=");
        uri.append(this.getStart());
    
        uri.append("&size=");
        uri.append(this.getSize());

        return uri.toString();
    }

    protected abstract void appendType(StringBuffer uri, MuleEvent event);

    public GetMethod getMethod()
    {
        return new GetMethod();
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
