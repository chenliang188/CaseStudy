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
 * 拆除连接请求消息，无消息体
 * 
 * @author yinmin
 */
public class Cmpp_Terminate extends BaseMessage
{
    
    /**
     * 拆除连接请求
     * 
     */
    public Cmpp_Terminate()
    {
        this.setCommand_Id(CMPP_TERMINATE);
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
    
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
