/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.activiti.i18n;

import org.mule.config.i18n.Message;
import org.mule.config.i18n.MessageFactory;

public class ActivitiMessages extends MessageFactory
{
    private static final ActivitiMessages factory = new ActivitiMessages();
    
    private static final String BUNDLE_PATH = getBundlePath("activiti");

    public static Message failToExecuteAction(Object action)
    {
        return factory.createMessage(BUNDLE_PATH, 1, action);
    }
    
    public static Message failToProcessJson()
    {
        return factory.createMessage(BUNDLE_PATH, 2);
    }

    public static Message failtToGetVariable()
    {
        return factory.createMessage(BUNDLE_PATH, 3);
    }

    public static Object noConnectorOrMultipleDefined()
    {
        return factory.createMessage(BUNDLE_PATH, 4);
    }
}
