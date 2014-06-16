/*
 * 文 件 名:  DP3Response.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright 2008-2009,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  f00150373
 * 修改时间:  2010-3-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cl.rermote.message;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  f00150373
 * @version  [版本号, 2010-3-25]
 * @see  [相关类/方法]
 * @since  DPFV100R001C01B010
 */
public abstract class DP3Response extends DP3Message
{
    private short responseStatus;
    
    private String responseText;
    
    public short getResponseStatus()
    {
        return responseStatus;
    }
    
    public void setResponseStatus(short responseStatus)
    {
        this.responseStatus = responseStatus;
    }
    
    public String getResponseText()
    {
        return this.responseText;
    }
    
    public void setResponseText(String responseText)
    {
        this.responseText = responseText;
    }
}
