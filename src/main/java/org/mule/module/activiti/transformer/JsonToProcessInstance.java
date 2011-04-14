/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.activiti.transformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.module.activiti.action.model.ProcessInstance;
import org.mule.module.activiti.i18n.ActivitiMessages;
import org.mule.module.json.transformers.AbstractJsonTransformer;
import org.mule.transformer.types.DataTypeFactory;
import org.mule.util.BeanUtils;

import java.util.Map;

public class JsonToProcessInstance extends AbstractJsonTransformer
{

    public JsonToProcessInstance()
    {
        this.setReturnDataType(DataTypeFactory.create(ProcessInstance.class));
    }

    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException
    {
        try
        {
            ProcessInstance processInstance = new ProcessInstance();
            Map<String, Object> rootAsMap = this.getMapper().readValue(message.getPayloadAsString(), Map.class);
            BeanUtils.populateWithoutFail(processInstance, rootAsMap, false);

            return processInstance;
        }
        catch (Exception e)
        {
            throw new TransformerException(ActivitiMessages.failToProcessJson(), e);
        }
    }
}
