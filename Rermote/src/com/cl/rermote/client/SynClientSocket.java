/*
 * 文 件 名:  SysClientSocket.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  陈亮
 * 修改时间:  2011-7-31
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cl.rermote.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.cl.rermote.util.Util;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  陈亮
 * @version  [版本号, 2011-7-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SynClientSocket extends Thread
{
    /**给客户端发送的消息队列*/
    private LinkedList<byte[]> sendList;
    
    /**接收到的消息队列*/
    private BlockingQueue<byte[]> msgQueue;
    
    private Socket socket = null;
    
    /** 输入流 */
    private DataInputStream dIn = null;
    
    /** 输出流 */
    private DataOutputStream dOut = null;
    
    /**解码消息类*/
    //private ReadHelper readHelper = null;
    
    /**是否为运行状态*/
    private boolean isRunning;
    
    /**sessionID*/
    private String sessionID = null;
    
    public SynClientSocket(Socket socket)
    {
        this.socket = socket;
    }
    
    public void init() throws IOException
    {
        //初始化发送集合
        sendList = new LinkedList<byte[]>();
        //初始化接收集合
        msgQueue = new LinkedBlockingQueue<byte[]>();
        //获得输入流
        dIn = new DataInputStream(socket.getInputStream());
        //获得输出流
        dOut = new DataOutputStream(socket.getOutputStream());
        //读取流
        //readHelper = new ReadHelper();
        //运行状态
        isRunning = true;
        //初始化发送线程
        new SenderThread().start();
        //初始化处理线程
        new ProcessThread().start();
        //接收线程启动
        this.start();
    }
    
    public void run()
    {
        while (isRunning)
        {
            try
            {
                byte[] temp = null;
                while (dIn.available() >= 0)
                {
                    temp = new byte[dIn.available()];
                    dIn.readFully(temp);
                    if (temp.length != 0)
                    {
                        msgQueue.put(temp);
                    }
                }
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 关闭
     * @see [类、类#方法、类#成员]
     */
    public void close()
    {
        
        try
        {
            isRunning = false;
            
            if (null != sessionID)
            {
                //SynOperator.getInstance().removeClientSocket(sessionID);
                //SynOperator.getInstance().removeAll(sessionID);
                //OperLogger.info("sessionID:" + sessionID + " socket closed!");
            }
            
            if (null != dIn)
            {
                dIn.close();
            }
            if (null != dOut)
            {
                dOut.close();
            }
            
            if (null != socket)
            {
                socket.close();
            }
        }
        catch (IOException e)
        {
            //DebugLogger.err("close socket");
        }
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author  陈亮
     * @version  [版本号, 2011-7-31]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    class SenderThread extends Thread
    {
        public void run()
        {
            byte[] temp;
            while (isRunning)
            {
                try
                {
                    temp = sendList.poll();
                    if (null != temp)
                    {
                        dOut.write(sendList.poll());
                        dOut.flush();
                    }
                    else
                    {
                        sleep(1000);
                    }
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author  陈亮
     * @version  [版本号, 2011-7-31]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    class ProcessThread extends Thread
    {
        public void run()
        {
            byte[] temp;
            
            while (isRunning)
            {
                try
                {
                    temp = msgQueue.poll();
                    if (null != temp)
                    {
                        sendList.addLast(temp);
                        System.out.println(Util.toHEXString(temp));
                    }
                    else
                    {
                        sleep(1000);
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
    
    /** <一句话功能简述>
     * <功能详细描述>
     * @param args [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        
    }
    
}
