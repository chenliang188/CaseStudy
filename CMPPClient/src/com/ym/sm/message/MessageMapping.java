/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-30��yinmin ������ 
 */
package com.ym.sm.message;

import java.io.IOException;
import java.util.Hashtable;

import net.i314.util.NetBits;

/**
 * ��Ϣӳ���࣬ͨ�������ִ�����Ϣʵ��
 * 
 * @author yinmin
 */
public class MessageMapping implements BaseConstants
{
    
    /** ��������Ϊ�ؼ��֣��������Ϊֵ */
    private Hashtable<Integer, Class> mapping = null;
    
    /** ������ʵ�� */
    private static MessageMapping instance = new MessageMapping();
    
    /** ������� */
    public static MessageMapping getInstance()
    {
        return instance;
    }
    
    /*
     * ˽�й��캯��
     */
    private MessageMapping()
    {
        mapping = new Hashtable<Integer, Class>();
        mapping.put(CMPP_CONNECT, com.ym.sm.message.Cmpp_Connect.class);
        mapping.put(CMPP_CONNECT_RESP, com.ym.sm.message.Cmpp_Connect_Resp.class);
        mapping.put(CMPP_SUBMIT, com.ym.sm.message.Cmpp_Submit.class);
        mapping.put(CMPP_SUBMIT_RESP, com.ym.sm.message.Cmpp_Submit_Resp.class);
        mapping.put(CMPP_ACTIVE_TEST, com.ym.sm.message.Cmpp_Active_Test.class);
        mapping.put(CMPP_ACTIVE_TEST_RESP, com.ym.sm.message.Cmpp_Active_Test_Resp.class);
        mapping.put(CMPP_TERMINATE, com.ym.sm.message.Cmpp_Terminate.class);
        mapping.put(CMPP_TERMINATE_RESP, com.ym.sm.message.Cmpp_Terminate_Resp.class);
    }
    
    /**
     * ��ȡ��Ϣͷ�����֣�������Ϣʵ������������Ϣ���ʵ����ֵ
     * 
     * @param head
     * @param body
     * @return
     * @throws IOException
     */
    public BaseMessage decode(byte[] head, byte[] body)
        throws IOException
    {
        int command = NetBits.getInt(head, 4);
        Class msgClass = mapping.get(command);
        if (msgClass == null)
        {
            throw new IOException("������Ϣͷʧ�ܣ������ַǷ���" + command);
        }
        
        BaseMessage msg = null;
        
        try
        {
            msg = (BaseMessage)msgClass.newInstance();
        }
        catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // ��Ϣ��Ϊ�գ���ֻ����Ϣͷ
        if (body == null || body.length == 0)
        {
            msg.readObject(head);
            return msg;
        }
        
        byte[] data = new byte[head.length + body.length];
        System.arraycopy(head, 0, data, 0, head.length);
        System.arraycopy(body, 0, data, head.length, body.length);
        msg.readObject(data);
        return msg;
        
    }
    
}
