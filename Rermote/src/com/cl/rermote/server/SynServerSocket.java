/*
 * 文 件 名:  SynServerSocket.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  陈亮
 * 修改时间:  2011-7-31
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cl.rermote.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.cl.rermote.client.SynClientSocket;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  陈亮
 * @version  [版本号, 2011-7-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SynServerSocket extends Thread
{
    boolean isRunning = false;
    
    ServerSocket serverSocket = null;
    
    public void openSocket() throws IOException
    {
        
        serverSocket = new ServerSocket();
        
        //若IP为空，则只绑定端口
        serverSocket.bind(new InetSocketAddress(5784));
        
        //IP不为空，绑定IP 端口
        //serverSocket.bind(new InetSocketAddress(ip, port));
        
        isRunning = true;
        //启动线程
        this.start();
        
    }
    
    /**
     * 关闭
     * @see [类、类#方法、类#成员]
     */
    public void close()
    {
        isRunning = false;
        
        if (null != serverSocket)
        {
            try
            {
                Thread.sleep(1000);
                //DebugLogger.inf("close serversocket!");
                serverSocket.close();
            }
            catch (Exception e)
            {
                //DebugLogger.err("close serversocket error" + e);
            }
        }
        
    }
    
    /**
     * 接收客户端之请求
     */
    public void run()
    {
        while (isRunning)
        {
            try
            {
                Socket socket = serverSocket.accept();
                SynClientSocket clientSocket = new SynClientSocket(socket);
                clientSocket.init();
                //OperLogger.info("receive a connection!" + socket.getRemoteSocketAddress());
            }
            catch (IOException e)
            {
                //DebugLogger.err("get stream error");
            }
        }
    }
    
    /** <一句话功能简述>
     * <功能详细描述>
     * @param args [参数说明]
     * 
     * @return void [返回类型说明]
     * @throws IOException 
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args) throws IOException
    {
        // TODO Auto-generated method stub
        SynServerSocket synServerSOcket = new SynServerSocket();
        synServerSOcket.openSocket();
    }
    
}
