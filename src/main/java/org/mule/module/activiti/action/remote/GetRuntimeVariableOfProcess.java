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

import org.mule.module.activiti.action.common.AbstractGetVariableOfProcessAction;


public class GetRuntimeVariableOfProcess extends AbstractGetVariableOfProcessAction
{
    public static final String GET_RUNTIME_VARIABLE_QUERY = "SELECT * FROM act_ru_variable WHERE PROC_INST_ID_ = ? AND NAME_ = ?";

    @Override
    protected String primGetQuery()
    {
        return GET_RUNTIME_VARIABLE_QUERY;
    }
    
    @Override
    protected String getTypeColumn()
    {
        return "TYPE_";
    }
}


