/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-8-3，yinmin 创建。 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;

/**
 * 心跳消息
 * 
 * @author yinmin
 */
public class Cmpp_Active_Test extends BaseMessage
{
    
    /**
     * 构造函数
     * 
     */
    public Cmpp_Active_Test()
    {
        this.setCommand_Id(CMPP_ACTIVE_TEST);
    }
    
    @Override
    public void checkValid()
        throws MessageInvalidException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void readBody(byte[] data)
        throws IOException
    {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 心跳消息无消息体
     */
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        
        return null;
    }
    
}
