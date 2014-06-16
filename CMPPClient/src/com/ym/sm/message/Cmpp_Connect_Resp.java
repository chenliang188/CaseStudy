/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-29��yinmin ������ 
 */
package com.ym.sm.message;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.ym.sm.exception.MessageInvalidException;

/**
 * CMPP����������Ϣ��Ӧ
 * 
 * @author yinmin
 */
public class Cmpp_Connect_Resp extends BaseMessage
{
    
    /**
     * ״̬0����ȷ1����Ϣ�ṹ�� 2���Ƿ�Դ��ַ 3����֤�� 4���汾̫�� 5~ ����������
     */
    private int Status;
    
    /**
     * ISMG��֤��
     */
    private String AuthenticatorISMG;
    
    /**
     * ������֧�ֵ���߰汾��
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
        // �����Ϣ����
        if (data.length != LEN_CONNECT_RESP_BODY)
        {
            throw new IOException("Bind ��Ϣ��Ӧ����");
        }
        // �ֽ���Ϣ�壬����ByteBuffer
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
