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

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.transport.PropertyScope;
import org.mule.module.activiti.ActivitiTestUtils;
import org.mule.tck.FunctionalTestCase;

import java.util.HashMap;
import java.util.Map;

public class GetRuntimeVariableOfProcessActionTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "org/mule/module/activiti/action/remote/get-runtime-variable-of-process.xml";
    }

    public void testGetVariable() throws Exception
    {
        MuleClient client = muleContext.getClient();
        DefaultMuleMessage message = new DefaultMuleMessage("", muleContext);
        
        Map parameterMap = new HashMap();
        parameterMap.put("processDefinitionKey", ActivitiTestUtils.MULTIPLY_WAIT_PROCESS_DEF_KEY);
        parameterMap.put("number", 2);
        
        message.setProperty("createProcessParameters", parameterMap , PropertyScope.OUTBOUND);
        
        MuleMessage responseMessage = client.send("vm://in", message);
        assertNotNull(responseMessage);
        assertEquals("2", responseMessage.getPayloadAsString());
    }
}
