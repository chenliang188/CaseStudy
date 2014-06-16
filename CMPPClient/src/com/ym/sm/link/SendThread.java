/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-29��yinmin ������ 
 */
package com.ym.sm.link;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

import com.ym.sm.message.BaseMessage;
import com.ym.sm.message.encode.IStreamCoder;

/**
 * �����߳�
 * 
 * @author yinmin
 */
public class SendThread extends Thread
{
    
    /** ����� */
    private OutputStream output;
    
    /** �����ӿ� */
    private IStreamCoder coder;
    
    /** �����б�һ��ʹ��BlockQueue�������Ǽ�ʵ�� * */
    private static LinkedList<BaseMessage> sendList = new LinkedList<BaseMessage>();
    
    private boolean exit = false;
    
    /**
     * ���캯��
     * 
     * @param name
     *            �߳���
     * @param out
     *            �����
     * @param coder
     *            ������
     */
    public SendThread(String name, OutputStream out, IStreamCoder coder)
    {
        super(name);
        this.output = out;
        this.coder = coder;
        this.setDaemon(true);
    }
    
    /**
     * ������Ϣ
     * 
     * @param msg
     */
    public void sendMessage(BaseMessage msg)
    {
        sendList.add(msg);
        // ����
        synchronized (this)
        {
            this.notify();
        }
    }
    
    /**
     * �ر��߳�����
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
            // ������Ϣ
            if (null != msg)
            {
                try
                {
                    coder.encode(msg, output);
                    output.flush();
                    System.out.println("������Ϣ");
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            // ���û����Ϣ�ˣ����Ե�1��
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
