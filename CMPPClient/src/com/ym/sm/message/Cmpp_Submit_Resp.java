/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-31��yinmin ������ 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;
import com.ym.sm.util.NetBits;

/**
 * @author yinmin
 */
public class Cmpp_Submit_Resp extends BaseMessage
{
    
    /** ��ϢID */
    private long msg_id;
    
    /** ��Ϣ��� 0��ȷ */
    private int result;
    
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
        int i = 0;
        msg_id = Long.parseLong(NetBits.getString(data, i, 8));
        i += 8;
        result = NetBits.getByte(data, i);
        
    }
    
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public long getMsg_id()
    {
        return msg_id;
    }
    
    public void setMsg_id(long msg_id)
    {
        this.msg_id = msg_id;
    }
    
    public int getResult()
    {
        return result;
    }
    
    public void setResult(int result)
    {
        this.result = result;
    }
    
    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        StringBuffer str = new StringBuffer();
        str.append("msg_id[").append(msg_id).append("],result[").append(result).append("]");
        return str.toString();
    }
}
