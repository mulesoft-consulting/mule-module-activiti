/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.activiti.util;

import java.lang.reflect.InvocationTargetException;

public class BeanUtils extends org.mule.util.BeanUtils
{

    public static void safeCopyProperties(Object dest, Object orig) {
        try
        {
            BeanUtils.copyProperties(dest, orig);
        }
        catch (IllegalAccessException e)
        {
        }
        catch (InvocationTargetException e)
        {
        }
    }
}


