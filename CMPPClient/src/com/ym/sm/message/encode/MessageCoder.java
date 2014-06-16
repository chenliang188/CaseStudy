/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-30，yinmin 创建。 
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
 * cmpp2消息编解码
 * 
 * @author yinmin
 */
public class MessageCoder implements IStreamCoder
{
    
    /**
     * 消息解码
     */
    public BaseMessage decode(InputStream input)
        throws IOException
    {
        // TODO Auto-generated method stub
        
        try
        {
            int readCount = 0;// 标记读取字节数
            int ret = 0;// 读取结果，如果没有或异常，返回-1
            byte[] head = new byte[BaseMessage.HEADER_LEN];
            while (readCount < head.length)
            {
                // 读取头部
                ret = input.read(head, 0, BaseMessage.HEADER_LEN - readCount);
                if (ret == -1)
                {
                    throw new IOException("读取头部数据异常");
                }
                // 已读取字节增加
                readCount += ret;
            }
            
            int msgSize = NetBits.getInt(head, 0);// 消息总长
            if (msgSize < BaseMessage.HEADER_LEN)
            {
                throw new IOException("数据包长度小于消息头长度");
            }
            if (msgSize > BaseMessage.MAXPACKEAGE_SIZE)
                throw new IOException("数据长度值异常，数据包长度值太大：" + msgSize);
            // 消息体长
            int bodyLenth = msgSize - BaseMessage.HEADER_LEN;
            byte[] body = new byte[bodyLenth];
            ret = 0;
            readCount = 0;
            while (readCount < bodyLenth)
            {
                ret = input.read(body, readCount, bodyLenth - readCount);
                if (ret == -1)
                {
                    throw new IOException("读包体数据异常");
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
     * 消息编码
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
