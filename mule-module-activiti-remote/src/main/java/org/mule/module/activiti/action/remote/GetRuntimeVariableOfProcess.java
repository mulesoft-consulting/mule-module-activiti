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

import org.mule.api.MuleEvent;

//GET_RUNTIME_VARIABLE_QUERY = "SELECT * FROM act_ru_variable WHERE PROC_INST_ID_ = ? AND NAME_ = ?";
public class GetRuntimeVariableOfProcess extends AbstractListTasksAction
{
    @Override
    protected String getRelativeUrl(MuleEvent event)
    {
        StringBuffer uri = new StringBuffer();
        uri.append("management/table/ACT_RU_VARIABLE/data");
        //this.appendType(uri, event);

        uri.append("?start=0");
        //uri.append(this.getStart());
    
        uri.append("&size=2");
        //uri.append(this.getSize());

        return uri.toString();
    }
    
    @Override
    protected void appendType(StringBuffer uri, MuleEvent event) {}
    
}


