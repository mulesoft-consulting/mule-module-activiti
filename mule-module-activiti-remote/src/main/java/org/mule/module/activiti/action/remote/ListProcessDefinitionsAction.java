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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;

public class ListProcessDefinitionsAction extends AbstractRemoteActivitiAction<GetMethod>
{
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * {@inheritDoc}
     */
    protected String getRelativeUrl(MuleEvent event)
    {
        return "process-definitions";
    }

    /**
     * {@inheritDoc}
     */
    public GetMethod getMethod()
    {
        return new GetMethod();
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareMethod(GetMethod method, MuleMessage message)
    {
        // DO NOTHING
    }
}
