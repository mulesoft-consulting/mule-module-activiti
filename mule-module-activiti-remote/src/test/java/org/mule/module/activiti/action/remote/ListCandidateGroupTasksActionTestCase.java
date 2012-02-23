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

import java.util.List;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.FunctionalTestCase;

public class ListCandidateGroupTasksActionTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "org/mule/module/activiti/action/remote/list-candidate-group-tasks.xml";
    }

    public void testListCandidateGroupTasks() throws Exception
    {
        MuleClient client = muleContext.getClient();
        MuleMessage message = client.send("vm://in", new DefaultMuleMessage("admin", muleContext));
        assertNotNull(message);
        
        List tasks = (List) message.getPayload();
        assertNotNull(tasks);
    }
}