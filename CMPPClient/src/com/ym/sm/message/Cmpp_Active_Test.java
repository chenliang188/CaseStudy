/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-8-3��yinmin ������ 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;

/**
 * ������Ϣ
 * 
 * @author yinmin
 */
public class Cmpp_Active_Test extends BaseMessage
{
    
    /**
     * ���캯��
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
     * ������Ϣ����Ϣ��
     */
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        
        return null;
    }
    
}
