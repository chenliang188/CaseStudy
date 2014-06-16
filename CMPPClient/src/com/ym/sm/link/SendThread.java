/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-29，yinmin 创建。 
 */
package com.ym.sm.link;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

import com.ym.sm.message.BaseMessage;
import com.ym.sm.message.encode.IStreamCoder;

/**
 * 发送线程
 * 
 * @author yinmin
 */
public class SendThread extends Thread
{
    
    /** 输出流 */
    private OutputStream output;
    
    /** 编解码接口 */
    private IStreamCoder coder;
    
    /** 发送列表，一般使用BlockQueue，这里是简单实现 * */
    private static LinkedList<BaseMessage> sendList = new LinkedList<BaseMessage>();
    
    private boolean exit = false;
    
    /**
     * 构造函数
     * 
     * @param name
     *            线程名
     * @param out
     *            输出流
     * @param coder
     *            编码器
     */
    public SendThread(String name, OutputStream out, IStreamCoder coder)
    {
        super(name);
        this.output = out;
        this.coder = coder;
        this.setDaemon(true);
    }
    
    /**
     * 发送消息
     * 
     * @param msg
     */
    public void sendMessage(BaseMessage msg)
    {
        sendList.add(msg);
        // 唤醒
        synchronized (this)
        {
            this.notify();
        }
    }
    
    /**
     * 关闭线程运行
     * 
     */
    public void close()
    {
        exit = false;
    }
    
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        while (!exit)
        {
            boolean isEmpty = false;
            BaseMessage msg = null;
            synchronized (sendList)
            {
                isEmpty = sendList.isEmpty();
                if (!isEmpty)
                {
                    msg = sendList.removeFirst();
                    isEmpty = sendList.isEmpty();
                }
            }
            // 发送消息
            if (null != msg)
            {
                try
                {
                    coder.encode(msg, output);
                    output.flush();
                    System.out.println("发送消息");
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            // 如果没有消息了，则稍等1秒
            if (isEmpty)
            {
                try
                {
                    synchronized (this)
                    {
                        if (!exit)
                        {
                            wait(1000);
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
