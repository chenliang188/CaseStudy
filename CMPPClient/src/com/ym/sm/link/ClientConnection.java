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
 * �����ͻ�������
 * 
 * @author yinmin
 * 
 */
public class ClientConnection extends Thread
{
    
    private Log logger = LogFactory.getLog(ClientConnection.class);
    
    /** ���Ӷ��� * */
    private static final int BROKEN = 0;
    
    /** �������� */
    private static final int CONNECTED = 1;
    
    /** ���Ӵ���״̬ */
    private static final int ACTIVED = 2;
    
    /** �˳� */
    private static final int EXIT = 3;
    
    /** ��ǰ����״̬ */
    private int status = BROKEN;
    
    /** �ͻ���socket */
    private Socket client;
    
    /** ������ * */
    private InputStream din;
    
    /** ����� * */
    private OutputStream dout;
    
    static Object obj = new Object();
    
    /** �������б� */
    private static Map<Integer, BaseMessage> requestMap = new HashMap<Integer, BaseMessage>();
    
    /** ���ݷ����߳� */
    private SendThread sender;
    
    /** ���ݽ����߳� */
    private RecieveThread reciver;
    
    private boolean reconnectValid = false; // ��������
    
    /** �������� */
    private Config config;
    
    private IStreamCoder coder;
    
    /**
     * ���캯�����������ӳ���
     * 
     * @param config
     */
    public ClientConnection(Config config)
    {
        this.config = config;
        
    }
    
    /**
     * ����socket����
     * 
     */
    public void connect()
        throws Exception
    {
        // ��������
        try
        {
            client = new Socket(config.getIp(), config.getPort());
            din = client.getInputStream();
            dout = client.getOutputStream();
            // �ı����ӵ�״̬
            toConnected();
            // ������Ϣ�շ��߳�
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
     * ��������ʼ�շ�������Ϣ
     */
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        
        int i = 0;
        logger.info("�����߳�����....");
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
                logger.info("��������.....");
                try
                {
                    connect();
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    logger.error("�������ش���", e);
                }
                continue;
            }
            
            // ��������ʱ�䣬��������Ϣ
            if (isActived() && i > shaketime)
            {
                shakeHand();
                i = 0;
            }
        }
        
        logger.info("�����߳��˳�.....");
    }
    
    /**
     * ������Ϣ�ӿ�
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
            // �����Ϣ�Ƿ�Ϸ���Ч
            request.checkValid();
            // �������״̬
            if (!isConnected())
            {
                throw new ClientException();
            }
            request.setSequence_Id(CreateSequence.getSequence());
            // �������
            synchronized (requestMap)
            {
                requestMap.put(request.getSequence_Id(), request);
            }
            
            // ������Ϣ
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
     * ����cmpp_connect��Ϣ
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
     * ����cmpp_submit��Ϣ
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
            logger.error("submit������Ϣ��ʽ����ȷ", e);
            throw e;
        }
        catch (ClientException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     * ��������������Ϣ
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
     * �������
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
     * �յ���Ϣ
     * 
     * @param msg
     */
    public void onRead(BaseMessage msg)
        throws Exception
    {
        if (msg instanceof DeliverMessage)
        {
            // ��������MO
        }
        else
        {
            // ������Ӧ
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
                logger.debug("û���ҵ���Ӧ����");
                return;
            }
            
            // �ж���Ӧ��Ϣ������
            if (msg instanceof Cmpp_Connect_Resp)
            {
                // �󶨵���Ӧ
                Cmpp_Connect_Resp resp = (Cmpp_Connect_Resp)msg;
                if (null == resp || resp.getStatus() != 0)
                {
                    throw new ClientException();
                }
                // �󶨳ɹ�����������
                reconnectValid = true;
                toActived();
                logger.info("�յ�connect_resp��status[" + resp.getStatus() + "]��AuthenticatorISMG["
                    + resp.getAuthenticatorISMG() + "]");
            }
            else if (msg instanceof Cmpp_Submit_Resp)
            {
                // submit��Ӧ
                Cmpp_Submit_Resp resp = (Cmpp_Submit_Resp)msg;
                
                if (resp == null)
                {
                    throw new IOException();
                }
                
                logger.info("�յ�submit_resp��" + resp.toString());
            }
            else if (msg instanceof Cmpp_Active_Test_Resp)
            {
                // ������Ϣ��Ӧ
                Cmpp_Active_Test_Resp resp = (Cmpp_Active_Test_Resp)msg;
                if (resp == null)
                {
                    throw new IOException("�յ�������Ϣ��ӦΪ��");
                }
                logger.info("�յ�Active_Test_Resp," + resp.toString());
            }
            else if (msg instanceof Cmpp_Terminate_Resp)
            {
                // ���������Ӧ
                Cmpp_Terminate_Resp resp = (Cmpp_Terminate_Resp)msg;
                if (resp == null)
                {
                    throw new IOException("�յ���ֹ������Ϣ��ӦΪ��");
                }
                // �޸�����״̬
                toExit();
                // �ر��շ��߳�
                reciver.close();
                sender.close();
                // �ر�����
                din.close();
                dout.close();
                client.close();
                logger.info("�յ�CMPP_Terminate_Resp�����Ӳ��");
                
            }
            else
            {
                logger.info("��Ϣ���ʹ���");
            }
            
        }
    }
    
    /**
     * �����Ƿ��Ѿ��˳�
     * 
     * @return
     */
    private boolean isExit()
    {
        return status == EXIT;
    }
    
    /**
     * �����Ƿ�����
     * 
     * @return
     */
    private boolean isConnected()
    {
        return status == ACTIVED || status == CONNECTED;
    }
    
    /**
     * �����Ƿ�״̬
     * 
     * @return
     */
    private boolean isActived()
    {
        return status == ACTIVED;
    }
    
    /**
     * ��״̬��Ϊ������
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
     * ��״̬��Ϊ���˳�
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
     * ��״̬��Ϊ������
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
     * �������к�
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
