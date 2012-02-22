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

import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;

public class CreateProcessAction extends AbstractRemoteActivitiAction<PostMethod>
{
    protected final Log logger = LogFactory.getLog(getClass());
    
    private String parametersExpression;
    private ObjectMapper mapper;
    
    public CreateProcessAction() {
        this.mapper = new ObjectMapper();
    }

    /**
     * {@inheritDoc}
     */
    protected String getRelativeUrl(MuleEvent event)
    {
        return "process-instance";
    }

    /**
     * {@inheritDoc}
     */
    public PostMethod getMethod()
    {
        return new PostMethod();
    }

    @Override
    protected void prepareMethod(PostMethod method, MuleMessage message) throws Exception
    {
        String json = "";

        Map map = this.getParametersMap(message);

        if (map.containsKey("processDefinitionKey"))
        {
            method.setParameter("processDefinitionKey", (String) map.get("processDefinitionKey"));
        }

        json = this.mapper.writeValueAsString(map);

        RequestEntity requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
        method.setRequestEntity(requestEntity);
    }

    private Map getParametersMap(MuleMessage message)
    {
        Object parameters = message.getMuleContext().getExpressionManager().evaluate(
            this.parametersExpression, message);
        if (parameters instanceof Map)
        {
            return (Map) parameters;
        }
        else
        {
            throw new IllegalStateException("A map is expected in the parameters expression");
        }
    }

    public String getParametersExpression()
    {
        return parametersExpression;
    }

    public void setParametersExpression(String parametersExpression)
    {
        this.parametersExpression = parametersExpression;
    }
}
