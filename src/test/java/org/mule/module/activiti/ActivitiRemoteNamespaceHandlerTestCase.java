/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.activiti;

import org.mule.tck.FunctionalTestCase;

public class ActivitiRemoteNamespaceHandlerTestCase extends FunctionalTestCase
{
    protected String getConfigResources()
    {
        return "activiti-remote-namespace-config.xml";
    }

    public void testActivitiConfig() throws Exception
    {
        ActivitiRemoteConnector c = (ActivitiRemoteConnector) muleContext.getRegistry().lookupConnector("actServer");
        assertNotNull(c);
        assertTrue(c.isConnected());
        assertTrue(c.isStarted());

        assertEquals("http://localhost:8080/activiti-rest/service/", c.getActivitiServerURL());
        assertEquals("kermit", c.getUsername());
        assertEquals("kermit2", c.getPassword());
        assertEquals(System.getProperty("activiti.version"), c.getVersion());
    }
}
