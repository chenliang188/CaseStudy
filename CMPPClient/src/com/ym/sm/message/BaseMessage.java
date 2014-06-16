package com.ym.sm.message;

import java.io.IOException;

import net.i314.util.NetBits;

import com.ym.sm.exception.MessageInvalidException;

/**
 * ����cmpp��Ϣ�Ļ���
 * 
 * @author yinmin
 * 
 */
public abstract class BaseMessage implements BaseConstants
{
    
    /** ��Ϣ�ܳ��� 4�ֽ�* */
    private int Total_Length;
    
    /** ��Ϣ������ 4�ֽ�* */
    private int Command_Id;
    
    /** ��Ϣ���кţ���������Ӧ��ϢӦ��Ӧ 4�ֽ� * */
    private int Sequence_Id;
    
    /** ��Ӧ��Ϣ */
    private BaseMessage response;
    
    /** ��Ϣͷ���� 12�ֽ� */
    public static final int HEADER_LEN = 12;
    
    /** ��Ϣ����󳤶� */
    public static final int MAXPACKEAGE_SIZE = 1024;
    
    /**
     * ��ȡͷ����
     * 
     * @return
     */
    private byte[] getHead()
    {
        byte[] head = new byte[12];
        NetBits.putInt(head, 0, Total_Length);
        NetBits.putInt(head, 4, Command_Id);
        NetBits.putInt(head, 8, Sequence_Id);
        return head;
    }
    
    /**
     * �����Ϣ��ʽ�Ƿ���ȷ������ȷ���׳�MessageInvalidException
     * 
     * @throws MessageInvalidException
     */
    public abstract void checkValid()
        throws MessageInvalidException;
    
    /**
     * �̵߳ȴ���Ӧ��Ϣ
     * 
     * @param waitTime
     * @throws InterruptedException
     */
    public synchronized void waitForResponse(long waitTime)
        throws InterruptedException
    {
        this.wait(waitTime);
    }
    
    /**
     * ֪ͨ�ȴ����̼߳���
     * 
     * @throws InterruptedException
     */
    public synchronized void notifyWaitThread()
        throws InterruptedException
    {
        this.notify();
    }
    
    /**
     * ��ȡ��Ϣ��ֻ��ȡͷ������ȡ��Ϣ��������ʵ��
     * 
     * @param data
     * @throws IOException
     */
    public final void readObject(byte[] data)
        throws IOException
    {
        Total_Length = NetBits.getInt(data, 0);
        Command_Id = NetBits.getInt(data, 4);
        Sequence_Id = NetBits.getInt(data, 8);
        if (data.length > 12)
        {
            byte[] body = NetBits.getBytes(data, 12, data.length - 12);
            readBody(body);
        }
    }
    
    /**
     * д����Ϣ��ֻд��Ϣͷ����д��Ϣ��������ʵ��
     * 
     * @throws IOException
     */
    public final byte[] writeObject()
        throws IOException
    {
        byte[] body = writeBody();
        if (body == null || body.length == 0)
        {
            Total_Length = HEADER_LEN;
            return getHead();
        }
        
        Total_Length = HEADER_LEN + body.length;
        byte[] head = getHead();
        byte[] data = new byte[Total_Length];
        NetBits.putBytes(data, 0, head);
        NetBits.putBytes(data, head.length, body);
        return data;
    }
    
    /**
     * ��ȡ��Ϣ�壬����ʵ��
     * 
     * @param data
     * @throws IOException
     */
    public abstract void readBody(byte[] data)
        throws IOException;
    
    /**
     * д��Ϣ�壬����ʵ��
     * 
     * @return
     * @throws IOException
     */
    public abstract byte[] writeBody()
        throws IOException;
    
    public int getTotal_Length()
    {
        return Total_Length;
    }
    
    public void setTotal_Length(int total_Length)
    {
        Total_Length = total_Length;
    }
    
    public int getCommand_Id()
    {
        return Command_Id;
    }
    
    public void setCommand_Id(int command_Id)
    {
        Command_Id = command_Id;
    }
    
    public int getSequence_Id()
    {
        return Sequence_Id;
    }
    
    public void setSequence_Id(int sequence_Id)
    {
        Sequence_Id = sequence_Id;
    }
    
    public BaseMessage getResponse()
    {
        return response;
    }
    
    public void setResponse(BaseMessage response)
    {
        this.response = response;
        
    }
    
}
