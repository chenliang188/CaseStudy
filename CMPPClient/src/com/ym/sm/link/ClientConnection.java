package com.ym.sm.link;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import net.i314.util.MD5;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ym.sm.exception.ClientException;
import com.ym.sm.exception.MessageInvalidException;
import com.ym.sm.message.BaseMessage;
import com.ym.sm.message.Cmpp_Active_Test;
import com.ym.sm.message.Cmpp_Active_Test_Resp;
import com.ym.sm.message.Cmpp_Connect;
import com.ym.sm.message.Cmpp_Connect_Resp;
import com.ym.sm.message.Cmpp_Submit;
import com.ym.sm.message.Cmpp_Submit_Resp;
import com.ym.sm.message.Cmpp_Terminate;
import com.ym.sm.message.Cmpp_Terminate_Resp;
import com.ym.sm.message.DeliverMessage;
import com.ym.sm.message.encode.IStreamCoder;
import com.ym.sm.message.encode.MessageCoder;
import com.ym.sm.util.AuthString;
import com.ym.sm.util.TimeUtil;

/**
 * 建立客户端连接
 * 
 * @author yinmin
 * 
 */
public class ClientConnection extends Thread
{
    
    private Log logger = LogFactory.getLog(ClientConnection.class);
    
    /** 连接断连 * */
    private static final int BROKEN = 0;
    
    /** 已连接上 */
    private static final int CONNECTED = 1;
    
    /** 连接处理活动状态 */
    private static final int ACTIVED = 2;
    
    /** 退出 */
    private static final int EXIT = 3;
    
    /** 当前连接状态 */
    private int status = BROKEN;
    
    /** 客户端socket */
    private Socket client;
    
    /** 输入流 * */
    private InputStream din;
    
    /** 输出流 * */
    private OutputStream dout;
    
    static Object obj = new Object();
    
    /** 请求存放列表 */
    private static Map<Integer, BaseMessage> requestMap = new HashMap<Integer, BaseMessage>();
    
    /** 数据发送线程 */
    private SendThread sender;
    
    /** 数据接收线程 */
    private RecieveThread reciver;
    
    private boolean reconnectValid = false; // 允许重连
    
    /** 连接配置 */
    private Config config;
    
    private IStreamCoder coder;
    
    /**
     * 构造函数，进行连接初化
     * 
     * @param config
     */
    public ClientConnection(Config config)
    {
        this.config = config;
        
    }
    
    /**
     * 建立socket连接
     * 
     */
    public void connect()
        throws Exception
    {
        // 建立连接
        try
        {
            client = new Socket(config.getIp(), config.getPort());
            din = client.getInputStream();
            dout = client.getOutputStream();
            // 改变连接的状态
            toConnected();
            // 启动消息收发线程
            coder = new MessageCoder();
            sender = new SendThread("sendThread", dout, coder);
            sender.start();
            reciver = new RecieveThread("recieveThread", din, coder, this);
            reciver.start();
        }
        catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            throw e;
            
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            throw e;
        }
        
    }
    
    /**
     * 启动即开始收发心跳消息
     */
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        
        int i = 0;
        logger.info("心跳线程启动....");
        int shaketime = config.getShakeHandTime();
        while (!isExit())
        {
            i++;
            try
            {
                sleep(1000);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            if (!isActived() && reconnectValid)
            {
                logger.info("重连网关.....");
                try
                {
                    connect();
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    logger.error("重连网关错误", e);
                }
                continue;
            }
            
            // 超过握手时间，则发握手消息
            if (isActived() && i > shaketime)
            {
                shakeHand();
                i = 0;
            }
        }
        
        logger.info("心跳线程退出.....");
    }
    
    /**
     * 发送消息接口
     * 
     * @param request
     * @return
     * @throws ClientException
     */
    private void sendMessage(BaseMessage request)
        throws ClientException, MessageInvalidException, Exception
    {
        try
        {
            // 检查消息是否合法有效
            request.checkValid();
            // 检查连接状态
            if (!isConnected())
            {
                throw new ClientException();
            }
            request.setSequence_Id(CreateSequence.getSequence());
            // 存放请求
            synchronized (requestMap)
            {
                requestMap.put(request.getSequence_Id(), request);
            }
            
            // 发送消息
            sender.sendMessage(request);
            
        }
        catch (MessageInvalidException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
    /**
     * 发送cmpp_connect消息
     * 
     * @throws ClientException
     */
    public void bind()
        throws Exception
    {
        Cmpp_Connect req = new Cmpp_Connect();
        req.setSource_Addr(config.getUserName());
        
        req.setTimestamp(Integer.parseInt(TimeUtil.getTimeStamp()));
        byte[] authenticatorSource =
            AuthString.getAuthString(req.getSource_Addr(), config.getPassword(), req.getTimestamp() + "");
        req.setAuthenticatorSource(new MD5().getMD5ofBytes(authenticatorSource, authenticatorSource.length));
        
        try
        {
            // Cmpp_Connect_Resp resp = (Cmpp_Connect_Resp) sendMessage(req);
            sendMessage(req);
        }
        catch (MessageInvalidException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     * 发送cmpp_submit消息
     * 
     * @throws Exception
     */
    public void submit(BaseMessage msg)
        throws Exception
    {
        Cmpp_Submit req = (Cmpp_Submit)msg;
        
        try
        {
            sendMessage(req);
            
        }
        catch (MessageInvalidException e)
        {
            // TODO Auto-generated catch block
            logger.error("submit请求消息格式不正确", e);
            throw e;
        }
        catch (ClientException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     * 发送心跳测试消息
     * 
     */
    private void shakeHand()
    {
        Cmpp_Active_Test req = new Cmpp_Active_Test();
        
        try
        {
            sendMessage(req);
        }
        catch (ClientException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MessageInvalidException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 拆除连接
     * 
     */
    public void terminate()
    {
        Cmpp_Terminate req = new Cmpp_Terminate();
        
        try
        {
            sendMessage(req);
            
        }
        catch (ClientException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MessageInvalidException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     * 收到消息
     * 
     * @param msg
     */
    public void onRead(BaseMessage msg)
        throws Exception
    {
        if (msg instanceof DeliverMessage)
        {
            // 处理上行MO
        }
        else
        {
            // 请求响应
            BaseMessage req = null;
            synchronized (requestMap)
            {
                req = requestMap.get(msg.getSequence_Id());
                if (req != null)
                {
                    req.setResponse(msg);
                    requestMap.remove(msg.getSequence_Id());
                }
                
            }
            
            if (null == req)
            {
                logger.debug("没有找到对应请求");
                return;
            }
            
            // 判断响应消息的类型
            if (msg instanceof Cmpp_Connect_Resp)
            {
                // 绑定的响应
                Cmpp_Connect_Resp resp = (Cmpp_Connect_Resp)msg;
                if (null == resp || resp.getStatus() != 0)
                {
                    throw new ClientException();
                }
                // 绑定成功，允许重连
                reconnectValid = true;
                toActived();
                logger.info("收到connect_resp，status[" + resp.getStatus() + "]，AuthenticatorISMG["
                    + resp.getAuthenticatorISMG() + "]");
            }
            else if (msg instanceof Cmpp_Submit_Resp)
            {
                // submit响应
                Cmpp_Submit_Resp resp = (Cmpp_Submit_Resp)msg;
                
                if (resp == null)
                {
                    throw new IOException();
                }
                
                logger.info("收到submit_resp，" + resp.toString());
            }
            else if (msg instanceof Cmpp_Active_Test_Resp)
            {
                // 心跳消息响应
                Cmpp_Active_Test_Resp resp = (Cmpp_Active_Test_Resp)msg;
                if (resp == null)
                {
                    throw new IOException("收到心跳消息响应为空");
                }
                logger.info("收到Active_Test_Resp," + resp.toString());
            }
            else if (msg instanceof Cmpp_Terminate_Resp)
            {
                // 拆除连接响应
                Cmpp_Terminate_Resp resp = (Cmpp_Terminate_Resp)msg;
                if (resp == null)
                {
                    throw new IOException("收到终止连接消息响应为空");
                }
                // 修改连接状态
                toExit();
                // 关闭收发线程
                reciver.close();
                sender.close();
                // 关闭连接
                din.close();
                dout.close();
                client.close();
                logger.info("收到CMPP_Terminate_Resp，连接拆除");
                
            }
            else
            {
                logger.info("消息类型错误");
            }
            
        }
    }
    
    /**
     * 连接是否已经退出
     * 
     * @return
     */
    private boolean isExit()
    {
        return status == EXIT;
    }
    
    /**
     * 连接是否正常
     * 
     * @return
     */
    private boolean isConnected()
    {
        return status == ACTIVED || status == CONNECTED;
    }
    
    /**
     * 连接是否活动状态
     * 
     * @return
     */
    private boolean isActived()
    {
        return status == ACTIVED;
    }
    
    /**
     * 将状态改为已连接
     * 
     */
    private void toConnected()
    {
        synchronized (obj)
        {
            this.status = CONNECTED;
        }
    }
    
    /**
     * 将状态改为已退出
     * 
     */
    private void toExit()
    {
        synchronized (obj)
        {
            this.status = EXIT;
        }
    }
    
    /**
     * 将状态改为已连接
     * 
     */
    private void toActived()
    {
        synchronized (obj)
        {
            this.status = ACTIVED;
        }
    }
    
    /**
     * 产生序列号
     * 
     * @author yinmin
     */
    private static class CreateSequence
    {
        public static int sequence = 0;
        
        public synchronized static int getSequence()
        {
            if (sequence >= 0x7FFFFFFF)
            {
                sequence++;
            }
            else
            {
                sequence = 1;
            }
            return sequence;
        }
    }
    
    public boolean isReconnectValid()
    {
        return reconnectValid;
    }
    
    public void setReconnectValid(boolean reconnectValid)
    {
        this.reconnectValid = reconnectValid;
    }
}
