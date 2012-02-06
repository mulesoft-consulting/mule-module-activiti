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

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.transport.PropertyScope;
import org.mule.module.activiti.ActivitiTestUtils;
import org.mule.module.activiti.action.model.ProcessInstance;
import org.mule.tck.FunctionalTestCase;

import java.util.HashMap;
import java.util.Map;

public class SetAndSignalActionsTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "org/mule/module/activiti/action/embedded/set-and-signal.xml";
    }

    public void testGetVariable() throws Exception
    {
        MuleClient client = muleContext.getClient();
        DefaultMuleMessage message = new DefaultMuleMessage("", muleContext);
        
        Map parameterMap = new HashMap();
        parameterMap.put("processDefinitionKey", ActivitiTestUtils.MULTIPLY_DOUBLE_WAIT_PROCESS_DEF_KEY);
        
        message.setProperty("createProcessParameters", parameterMap , PropertyScope.OUTBOUND);
        
        MuleMessage responseMessage = client.send("vm://in", message);
        assertNotNull(responseMessage);
        
        
        ProcessInstance instance = (ProcessInstance) responseMessage.getPayload();
        
        DefaultMuleMessage setAndSignalMessage = new DefaultMuleMessage(instance, muleContext);
        setAndSignalMessage.setProperty("numberValue", 3, PropertyScope.OUTBOUND);
        
        MuleMessage setAndSignalResponseMessage = client.send("vm://receiveMessage", setAndSignalMessage);
        assertNotNull(setAndSignalResponseMessage);
        assertEquals("6", setAndSignalResponseMessage.getPayloadAsString());
    }
}
