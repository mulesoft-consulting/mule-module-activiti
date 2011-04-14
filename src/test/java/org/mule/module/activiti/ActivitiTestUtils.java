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

public class ActivitiTestUtils
{
    public static final String ACTIVITI_LAST_VERSION = "5.4";

    public static String getActivitiVersion()
    {
        String version = System.getProperty("activiti.version");
        if (version == null)
        {
            return ACTIVITI_LAST_VERSION;
        }
        else
        {
            return version;
        }
    }
}
