/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-30��yinmin ������ 
 */
package com.ym.sm.message.encode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import net.i314.util.NetBits;

import com.ym.sm.message.BaseMessage;
import com.ym.sm.message.MessageMapping;

/**
 * cmpp2��Ϣ�����
 * 
 * @author yinmin
 */
public class MessageCoder implements IStreamCoder
{
    
    /**
     * ��Ϣ����
     */
    public BaseMessage decode(InputStream input)
        throws IOException
    {
        // TODO Auto-generated method stub
        
        try
        {
            int readCount = 0;// ��Ƕ�ȡ�ֽ���
            int ret = 0;// ��ȡ��������û�л��쳣������-1
            byte[] head = new byte[BaseMessage.HEADER_LEN];
            while (readCount < head.length)
            {
                // ��ȡͷ��
                ret = input.read(head, 0, BaseMessage.HEADER_LEN - readCount);
                if (ret == -1)
                {
                    throw new IOException("��ȡͷ�������쳣");
                }
                // �Ѷ�ȡ�ֽ�����
                readCount += ret;
            }
            
            int msgSize = NetBits.getInt(head, 0);// ��Ϣ�ܳ�
            if (msgSize < BaseMessage.HEADER_LEN)
            {
                throw new IOException("���ݰ�����С����Ϣͷ����");
            }
            if (msgSize > BaseMessage.MAXPACKEAGE_SIZE)
                throw new IOException("���ݳ���ֵ�쳣�����ݰ�����ֵ̫��" + msgSize);
            // ��Ϣ�峤
            int bodyLenth = msgSize - BaseMessage.HEADER_LEN;
            byte[] body = new byte[bodyLenth];
            ret = 0;
            readCount = 0;
            while (readCount < bodyLenth)
            {
                ret = input.read(body, readCount, bodyLenth - readCount);
                if (ret == -1)
                {
                    throw new IOException("�����������쳣");
                }
                readCount += ret;
            }
            
            BaseMessage msg = MessageMapping.getInstance().decode(head, body);
            return msg;
        }
        catch (SocketException e1)
        {
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * ��Ϣ����
     */
    public void encode(Object obj, OutputStream output)
        throws IOException
    {
        // TODO Auto-generated method stub
        BaseMessage msg = (BaseMessage)obj;
        byte[] data = msg.writeObject();
        output.write(data);
    }
    
}
