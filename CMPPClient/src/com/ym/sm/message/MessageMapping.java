/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-30，yinmin 创建。 
 */
package com.ym.sm.message;

import java.io.IOException;
import java.util.Hashtable;

import net.i314.util.NetBits;

/**
 * 消息映射类，通过命令字创建消息实例
 * 
 * @author yinmin
 */
public class MessageMapping implements BaseConstants
{
    
    /** 以命令字为关键字，以类对象为值 */
    private Hashtable<Integer, Class> mapping = null;
    
    /** 创建单实例 */
    private static MessageMapping instance = new MessageMapping();
    
    /** 调用入口 */
    public static MessageMapping getInstance()
    {
        return instance;
    }
    
    /*
     * 私有构造函数
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
     * 读取消息头命令字，创建消息实例，并解码消息体给实例赋值
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
            throw new IOException("解析消息头失败，命令字非法：" + command);
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
        // 消息体为空，则只读消息头
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
