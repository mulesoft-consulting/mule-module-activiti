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

public class GetLatestHistoricVariableOfProcess extends AbstractGetVariableOfProcessAction
{
    public static final String GET_LATEST_HISTORY_VARIABLE_QUERY = "SELECT * FROM act_hi_detail WHERE PROC_INST_ID_ = ? AND NAME_ = ? ORDER BY TIME_ DESC";

    @Override
    protected String primGetQuery()
    {
        return GET_LATEST_HISTORY_VARIABLE_QUERY;
    }
    
    @Override
    protected String getTypeColumn()
    {
        return "VAR_TYPE_";
    }
}


