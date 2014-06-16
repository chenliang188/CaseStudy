package com.ym.sm.message;

import java.io.IOException;

import net.i314.util.NetBits;

import com.ym.sm.exception.MessageInvalidException;

/**
 * 所有cmpp消息的基类
 * 
 * @author yinmin
 * 
 */
public abstract class BaseMessage implements BaseConstants
{
    
    /** 消息总长度 4字节* */
    private int Total_Length;
    
    /** 消息命令字 4字节* */
    private int Command_Id;
    
    /** 消息序列号，请求与响应消息应对应 4字节 * */
    private int Sequence_Id;
    
    /** 响应消息 */
    private BaseMessage response;
    
    /** 消息头长度 12字节 */
    public static final int HEADER_LEN = 12;
    
    /** 消息包最大长度 */
    public static final int MAXPACKEAGE_SIZE = 1024;
    
    /**
     * 获取头编码
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
     * 检查消息格式是否正确，不正确则抛出MessageInvalidException
     * 
     * @throws MessageInvalidException
     */
    public abstract void checkValid()
        throws MessageInvalidException;
    
    /**
     * 线程等待响应消息
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
     * 通知等待的线程继续
     * 
     * @throws InterruptedException
     */
    public synchronized void notifyWaitThread()
        throws InterruptedException
    {
        this.notify();
    }
    
    /**
     * 读取消息，只读取头部，读取消息体由子类实现
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
     * 写入消息，只写消息头部，写消息体由子类实现
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
     * 读取消息体，子类实现
     * 
     * @param data
     * @throws IOException
     */
    public abstract void readBody(byte[] data)
        throws IOException;
    
    /**
     * 写消息体，子类实现
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
