/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-29，yinmin 创建。 
 */
package com.ym.sm.message;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.ym.sm.exception.MessageInvalidException;

/**
 * CMPP连接请求消息响应
 * 
 * @author yinmin
 */
public class Cmpp_Connect_Resp extends BaseMessage
{
    
    /**
     * 状态0：正确1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5~ ：其他错误
     */
    private int Status;
    
    /**
     * ISMG验证码
     */
    private String AuthenticatorISMG;
    
    /**
     * 服务器支持的最高版本号
     */
    private int Version;
    
    public String getAuthenticatorISMG()
    {
        return AuthenticatorISMG;
    }
    
    public void setAuthenticatorISMG(String authenticatorISMG)
    {
        AuthenticatorISMG = authenticatorISMG;
    }
    
    public int getStatus()
    {
        return Status;
    }
    
    public void setStatus(int status)
    {
        Status = status;
    }
    
    public int getVersion()
    {
        return Version;
    }
    
    public void setVersion(int version)
    {
        Version = version;
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
        // 检查消息长度
        if (data.length != LEN_CONNECT_RESP_BODY)
        {
            throw new IOException("Bind 消息响应错误");
        }
        // 分解消息体，改用ByteBuffer
        ByteBuffer bodyBuffer = ByteBuffer.wrap(data);
        Status = bodyBuffer.get();
        byte[] ismg = new byte[16];
        bodyBuffer.get(ismg, 0, ismg.length);
        AuthenticatorISMG = new String(ismg);
        Version = bodyBuffer.get();
        
    }
    
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        return null;
    }
}
