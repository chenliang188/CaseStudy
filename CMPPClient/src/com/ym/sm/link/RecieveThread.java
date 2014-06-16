/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-29，yinmin 创建。 
 */
package com.ym.sm.link;

import java.io.InputStream;

import com.ym.sm.message.BaseMessage;
import com.ym.sm.message.encode.IStreamCoder;

/**
 * 接收数据线程
 * 
 * @author yinmin
 */
public class RecieveThread extends Thread
{
    
    /** 输入流 */
    private InputStream input;
    
    /** 编解码器 */
    private IStreamCoder coder;
    
    /** 是否退出 */
    private boolean exit = false;
    
    private ClientConnection con;
    
    /**
     * 构造函数
     * 
     * @param name
     *            线程名
     * @param input
     *            输入流
     * @param coder
     *            编码器
     */
    public RecieveThread(String name, InputStream input, IStreamCoder coder, ClientConnection con)
    {
        super(name);
        this.input = input;
        this.coder = coder;
        this.con = con;
        this.setDaemon(true);
    }
    
    /**
     * 关闭线程执行
     * 
     */
    public void close()
    {
        exit = true;
    }
    
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        
        while (!exit)
        {
            try
            {
                
                BaseMessage obj = coder.decode(input);
                // 处理消息
                con.onRead(obj);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }
            finally
            {
                
            }
        }
    }
    
}
