/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-29��yinmin ������ 
 */
package com.ym.sm.link;

import java.io.InputStream;

import com.ym.sm.message.BaseMessage;
import com.ym.sm.message.encode.IStreamCoder;

/**
 * ���������߳�
 * 
 * @author yinmin
 */
public class RecieveThread extends Thread
{
    
    /** ������ */
    private InputStream input;
    
    /** ������� */
    private IStreamCoder coder;
    
    /** �Ƿ��˳� */
    private boolean exit = false;
    
    private ClientConnection con;
    
    /**
     * ���캯��
     * 
     * @param name
     *            �߳���
     * @param input
     *            ������
     * @param coder
     *            ������
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
     * �ر��߳�ִ��
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
                // ������Ϣ
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
