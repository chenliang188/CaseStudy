/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-8-3��yinmin ������ 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;
import com.ym.sm.util.NetBits;

/**
 * ������Ϣ��Ӧ
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
            throw new IOException("������Ϣ��Ӧ����ȷ");
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
