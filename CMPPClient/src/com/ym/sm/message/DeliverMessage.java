/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-29，yinmin 创建。 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;

/**
 * MO消息
 * 
 * @author yinmin
 */
public class DeliverMessage extends BaseMessage
{
    
    /** 消息ID MMDDHHMMSS+短信网关代码 +序列号 * */
    private long msg_Id;
    
    /** sp服务代码 * */
    private String dest_Id;
    
    /** 业务类型，是数字、字母和符号的组合 * */
    private String service_Id;
    
    /** TPID * */
    private int tp_pid;
    
    /** 是否有UDHI头部 * */
    private int tp_Udhi;
    
    /** 信息格式 0：ASCII串 3：短信写卡操作 4：二进制信息 8：UCS2编码15：含GB汉字 * */
    private int msg_Fmt;
    
    /** 源终端号码 */
    private String src_terminal_Id;
    
    /** 是否是状态报告 */
    private String registered_Delivery;
    
    /** 消息长度 */
    private String msg_Length;
    
    /** 消息内容 */
    private String msg_Content;
    
    /** 状态报告的消息ID */
    private String dr_Msg_Id;
    
    /** 状态结果 */
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
