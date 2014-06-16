package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;
import com.ym.sm.util.NetBits;

/**
 * CMPP连接请求消息
 * @author yinmin
 *
 */
public class Cmpp_Connect extends BaseMessage
{
    
    /**
     * SP_ID
     */
    private String Source_Addr;
    
    /**
     * 鉴权字符串
     */
    private byte[] AuthenticatorSource;
    
    /**
     * 协议版本号，高四位为主版本号，低四位为次版本号
     */
    private int Version = 0;
    
    /**
     * 时间戳的明文,由客户端产生,格式为MMDDHHMMSS
     */
    private int Timestamp;
    
    /**
     * 构造函数
     *
     */
    public Cmpp_Connect()
    {
        this.setCommand_Id(CMPP_CONNECT);
    }
    
    public byte[] getAuthenticatorSource()
    {
        return AuthenticatorSource;
    }
    
    public void setAuthenticatorSource(byte[] authenticatorSource)
    {
        AuthenticatorSource = authenticatorSource;
    }
    
    public String getSource_Addr()
    {
        return Source_Addr;
    }
    
    public void setSource_Addr(String source_Addr)
    {
        Source_Addr = source_Addr;
    }
    
    public int getTimestamp()
    {
        return Timestamp;
    }
    
    public void setTimestamp(int timestamp)
    {
        Timestamp = timestamp;
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
        
    }
    
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        byte[] msg = new byte[27];
        NetBits.putFixedStringToBytes(msg, 0, Source_Addr, 6);
        NetBits.putBytes(msg, 6, AuthenticatorSource);
        NetBits.putIntToBytes(msg, 22, Version, 1);
        NetBits.putIntToBytes(msg, 23, Timestamp, 4);
        return msg;
    }
    
}
