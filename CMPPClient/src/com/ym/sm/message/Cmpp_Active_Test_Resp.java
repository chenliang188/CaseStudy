/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-8-3，yinmin 创建。 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;
import com.ym.sm.util.NetBits;

/**
 * 心跳消息响应
 * 
 * @author yinmin
 */
public class Cmpp_Active_Test_Resp extends BaseMessage
{
    
    private int reserved;
    
    public int getReserved()
    {
        return reserved;
    }
    
    public void setReserved(int reserved)
    {
        this.reserved = reserved;
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
        if (data.length != 1)
        {
            throw new IOException("心跳消息响应不正确");
        }
        reserved = NetBits.getByte(data, 0);
    }
    
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return "reserved[" + reserved + "]";
    }
    
}
