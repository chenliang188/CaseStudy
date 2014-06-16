/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-29��yinmin ������ 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;

/**
 * MO��Ϣ
 * 
 * @author yinmin
 */
public class DeliverMessage extends BaseMessage
{
    
    /** ��ϢID MMDDHHMMSS+�������ش��� +���к� * */
    private long msg_Id;
    
    /** sp������� * */
    private String dest_Id;
    
    /** ҵ�����ͣ������֡���ĸ�ͷ��ŵ���� * */
    private String service_Id;
    
    /** TPID * */
    private int tp_pid;
    
    /** �Ƿ���UDHIͷ�� * */
    private int tp_Udhi;
    
    /** ��Ϣ��ʽ 0��ASCII�� 3������д������ 4����������Ϣ 8��UCS2����15����GB���� * */
    private int msg_Fmt;
    
    /** Դ�ն˺��� */
    private String src_terminal_Id;
    
    /** �Ƿ���״̬���� */
    private String registered_Delivery;
    
    /** ��Ϣ���� */
    private String msg_Length;
    
    /** ��Ϣ���� */
    private String msg_Content;
    
    /** ״̬�������ϢID */
    private String dr_Msg_Id;
    
    /** ״̬��� */
    private String stat;
    
    public String getDest_Id()
    {
        return dest_Id;
    }
    
    public void setDest_Id(String dest_Id)
    {
        this.dest_Id = dest_Id;
    }
    
    public String getDr_Msg_Id()
    {
        return dr_Msg_Id;
    }
    
    public void setDr_Msg_Id(String dr_Msg_Id)
    {
        this.dr_Msg_Id = dr_Msg_Id;
    }
    
    public String getMsg_Content()
    {
        return msg_Content;
    }
    
    public void setMsg_Content(String msg_Content)
    {
        this.msg_Content = msg_Content;
    }
    
    public int getMsg_Fmt()
    {
        return msg_Fmt;
    }
    
    public void setMsg_Fmt(int msg_Fmt)
    {
        this.msg_Fmt = msg_Fmt;
    }
    
    public long getMsg_Id()
    {
        return msg_Id;
    }
    
    public void setMsg_Id(long msg_Id)
    {
        this.msg_Id = msg_Id;
    }
    
    public String getMsg_Length()
    {
        return msg_Length;
    }
    
    public void setMsg_Length(String msg_Length)
    {
        this.msg_Length = msg_Length;
    }
    
    public String getRegistered_Delivery()
    {
        return registered_Delivery;
    }
    
    public void setRegistered_Delivery(String registered_Delivery)
    {
        this.registered_Delivery = registered_Delivery;
    }
    
    public String getService_Id()
    {
        return service_Id;
    }
    
    public void setService_Id(String service_Id)
    {
        this.service_Id = service_Id;
    }
    
    public String getSrc_terminal_Id()
    {
        return src_terminal_Id;
    }
    
    public void setSrc_terminal_Id(String src_terminal_Id)
    {
        this.src_terminal_Id = src_terminal_Id;
    }
    
    public String getStat()
    {
        return stat;
    }
    
    public void setStat(String stat)
    {
        this.stat = stat;
    }
    
    public int getTp_pid()
    {
        return tp_pid;
    }
    
    public void setTp_pid(int tp_pid)
    {
        this.tp_pid = tp_pid;
    }
    
    public int getTp_Udhi()
    {
        return tp_Udhi;
    }
    
    public void setTp_Udhi(int tp_Udhi)
    {
        this.tp_Udhi = tp_Udhi;
    }
    
    @Override
    public void checkValid()
        throws MessageInvalidException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void readBody(byte[] data)
        throws IOException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public byte[] writeBody()
        throws IOException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
