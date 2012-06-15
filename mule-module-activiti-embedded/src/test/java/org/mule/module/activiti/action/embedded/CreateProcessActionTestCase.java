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

import java.util.HashMap;
import java.util.Map;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.transport.PropertyScope;
import org.mule.module.activiti.ActivitiTestUtils;
import org.mule.module.activiti.action.model.ProcessInstance;
import org.mule.tck.junit4.FunctionalTestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CreateProcessActionTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "src/test/resources/org/mule/module/activiti/action/embedded/create-process.xml";
    }

    @Test
    public void testCreateProcess() throws Exception
    {
        MuleClient client = muleContext.getClient();
        DefaultMuleMessage message = new DefaultMuleMessage("", muleContext);
        Map parameterMap = new HashMap();
        parameterMap.put("processDefinitionKey", ActivitiTestUtils.MULTIPLY_WAIT_PROCESS_DEF_KEY);
        message.setProperty("createProcessParameters", parameterMap , PropertyScope.OUTBOUND);
        
        MuleMessage responseMessage = client.send("vm://in", message);
        assertNotNull(message);
        
        ProcessInstance instance = (ProcessInstance) responseMessage.getPayload();
        assertNotNull(instance);
    }
}
