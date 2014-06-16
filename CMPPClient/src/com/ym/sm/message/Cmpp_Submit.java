/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-31，yinmin 创建。 
 */
package com.ym.sm.message;

import java.io.IOException;

import com.ym.sm.exception.MessageInvalidException;
import com.ym.sm.util.NetBits;
import com.ym.sm.util.TimeUtil;

/**
 * cmpp_submit消息
 * 
 * @author yinmin
 */
public class Cmpp_Submit extends BaseMessage
{
    
    /** 消息ID，由网关产生 */
    private long msg_id;
    
    /** 信息总条数 */
    private int pk_total;
    
    /** 信息序号 */
    private int pk_number;
    
    /** 是否需要状态报告,0不需,1需要,2产生smc话单 */
    private int registered_delivery;
    
    /** 信息级别 */
    private int msg_level;
    
    /** 业务类型 */
    private String service_id;
    
    /** 计费用户类型字段 0对目地终端计费 1对源终端计费 2对sp计费 3本字段无效，见fee_terminal_id */
    private int fee_userType;
    
    /** 被计费用户的号码 与fee_userType字段互斥 */
    private String fee_termianl_Id;
    
    /** pid */
    private int tp_pid;
    
    /** 消息体是否有udhi头部 */
    private int tp_udhi;
    
    /** 信息格式 0:ASCII;3：短信写卡;4二进制 8:ucs2 15：含GB汉字 */
    private int msg_fmt;
    
    /** 信息内容来源sp_id */
    private String msg_src;
    
    /** 资费类别 01免费 02按条 03包月 04信息封顶 05由sp实现 */
    private String fee_Type;
    
    /** 资费代码 以“分”为单位 */
    private String feeCode;
    
    /** 存活有效期 */
    private String valid_time;
    
    /** 定时发送时间 */
    private String at_time;
    
    /** 源号码 portal号 */
    private String src_id;
    
    /** 接收消息有用户数 <100 */
    private int destusr_tl;
    
    /** 接收短信的用户MSISDN */
    private String dest_terminal_id;
    
    /** 信息长度 msg_fmt=0:<160字节 其它<140字节 */
    private int msg_length;
    
    private final int MESG_ASCII_LEN = 160;
    
    private final int MESG_OTHER_LEN = 140;
    
    /** 消息内容 */
    private byte[] msg_content;
    
    public Cmpp_Submit()
    {
        this.setCommand_Id(CMPP_SUBMIT);
        pk_total = 1;
        pk_number = 1;
        destusr_tl = 1;
    }
    
    public String getAt_time()
    {
        return at_time;
    }
    
    public void setAt_time(String at_time)
    {
        this.at_time = at_time;
    }
    
    public String getDest_terminal_id()
    {
        return dest_terminal_id;
    }
    
    public void setDest_terminal_id(String dest_terminal_id)
    {
        this.dest_terminal_id = dest_terminal_id;
    }
    
    public int getDestusr_tl()
    {
        return destusr_tl;
    }
    
    public void setDestusr_tl(int destusr_tl)
    {
        this.destusr_tl = destusr_tl;
    }
    
    public String getFee_termianl_Id()
    {
        return fee_termianl_Id;
    }
    
    public void setFee_termianl_Id(String fee_termianl_Id)
    {
        this.fee_termianl_Id = fee_termianl_Id;
    }
    
    public int getFee_userType()
    {
        return fee_userType;
    }
    
    public void setFee_userType(int fee_userType)
    {
        this.fee_userType = fee_userType;
    }
    
    public String getFeeCode()
    {
        return feeCode;
    }
    
    public void setFeeCode(String feeCode)
    {
        this.feeCode = feeCode;
    }
    
    public String getFee_Type()
    {
        return fee_Type;
    }
    
    public void setFee_Type(String fee_Type)
    {
        this.fee_Type = fee_Type;
    }
    
    public byte[] getMsg_content()
    {
        return msg_content;
    }
    
    public void setMsg_content(byte[] msg_content)
    {
        this.msg_content = msg_content;
    }
    
    public int getMsg_fmt()
    {
        return msg_fmt;
    }
    
    public void setMsg_fmt(int msg_fmt)
    {
        this.msg_fmt = msg_fmt;
    }
    
    public long getMsg_id()
    {
        return msg_id;
    }
    
    public void setMsg_id(long msg_id)
    {
        this.msg_id = msg_id;
    }
    
    public int getMsg_length()
    {
        return msg_length;
    }
    
    public void setMsg_length(int msg_length)
    {
        this.msg_length = msg_length;
    }
    
    public int getMsg_level()
    {
        return msg_level;
    }
    
    public void setMsg_level(int msg_level)
    {
        this.msg_level = msg_level;
    }
    
    public String getMsg_src()
    {
        return msg_src;
    }
    
    public void setMsg_src(String msg_src)
    {
        this.msg_src = msg_src;
    }
    
    public int getPk_number()
    {
        return pk_number;
    }
    
    public void setPk_number(int pk_number)
    {
        this.pk_number = pk_number;
    }
    
    public int getPk_total()
    {
        return pk_total;
    }
    
    public void setPk_total(int pk_total)
    {
        this.pk_total = pk_total;
    }
    
    public int getRegistered_delivery()
    {
        return registered_delivery;
    }
    
    public void setRegistered_delivery(int registered_delivery)
    {
        this.registered_delivery = registered_delivery;
    }
    
    public String getService_id()
    {
        return service_id;
    }
    
    public void setService_id(String service_id)
    {
        this.service_id = service_id;
    }
    
    public String getSrc_id()
    {
        return src_id;
    }
    
    public void setSrc_id(String src_id)
    {
        this.src_id = src_id;
    }
    
    public int getTp_pid()
    {
        return tp_pid;
    }
    
    public void setTp_pid(int tp_pid)
    {
        this.tp_pid = tp_pid;
    }
    
    public int getTp_udhi()
    {
        return tp_udhi;
    }
    
    public void setTp_udhi(int tp_udhi)
    {
        this.tp_udhi = tp_udhi;
    }
    
    public String getValid_time()
    {
        return valid_time;
    }
    
    public void setValid_time(String valid_time)
    {
        this.valid_time = valid_time;
    }
    
    /**
     * 检查消息是否合法，只检查了消息长度
     */
    @Override
    public void checkValid()
        throws MessageInvalidException
    {
        // TODO Auto-generated method stub
        if (msg_fmt == 0 && msg_content.length > MESG_ASCII_LEN)
        {
            throw new MessageInvalidException();
        }
        else
        {
            if (msg_content.length > MESG_OTHER_LEN)
            {
                throw new MessageInvalidException();
            }
        }
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
        int i = 0;
        byte[] data = new byte[139 + msg_content.length + 8];
        NetBits.putLong(data, i, msg_id);
        i += 8;
        NetBits.putIntToBytes(data, i, pk_total, 1);
        i += 1;
        NetBits.putIntToBytes(data, i, pk_number, 1);
        i += 1;
        NetBits.putIntToBytes(data, i, registered_delivery, 1);
        i += 1;
        NetBits.putIntToBytes(data, i, msg_level, 1);
        i += 1;
        NetBits.putFixedStringToBytes(data, i, service_id, 10);
        i += 10;
        NetBits.putIntToBytes(data, i, fee_userType, 1);
        i += 1;
        NetBits.putFixedStringToBytes(data, i, fee_termianl_Id, 21);
        i += 21;
        NetBits.putIntToBytes(data, i, tp_pid, 1);
        i += 1;
        NetBits.putIntToBytes(data, i, tp_udhi, 1);
        i += 1;
        NetBits.putIntToBytes(data, i, msg_fmt, 1);
        i += 1;
        NetBits.putFixedStringToBytes(data, i, msg_src, 6);
        i += 6;
        NetBits.putFixedStringToBytes(data, i, fee_Type, 2);
        i += 2;
        NetBits.putFixedStringToBytes(data, i, feeCode, 6);
        i += 6;
        if (valid_time != null && !valid_time.equals(""))
        {
            valid_time = TimeUtil.getCmppDateStr(valid_time);
        }
        else
        {
            valid_time = "";
        }
        if (at_time != null && !at_time.equals(""))
        {
            at_time = TimeUtil.getCmppDateStr(at_time);
        }
        else
        {
            at_time = "";
        }
        NetBits.putFixedStringToBytes(data, i, valid_time, 17);
        i += 17;
        NetBits.putFixedStringToBytes(data, i, at_time, 17);
        i += 17;
        NetBits.putFixedStringToBytes(data, i, src_id, 21);
        i += 21;
        NetBits.putIntToBytes(data, i, destusr_tl, 1);
        i += 1;
        NetBits.putFixedStringToBytes(data, i, dest_terminal_id, 21);
        i += 21;
        NetBits.putIntToBytes(data, i, msg_length, 1);
        i += 1;
        NetBits.putBytes(data, i, msg_content, msg_content.length);
        
        return data;
    }
    
}
