/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-30，yinmin 创建。 
 */
package com.ym.sm.message;

/**
 * 消息命令字常量
 * 
 * @author yinmin
 */
public interface BaseConstants
{
    
    /** 连接请求 */
    public static final int CMPP_CONNECT = 0x00000001;
    
    /** 连接请求应答 */
    public static final int CMPP_CONNECT_RESP = 0x80000001;
    
    /** 终止连接 */
    public static final int CMPP_TERMINATE = 0x00000002;
    
    /** 终止连接应答 */
    public static final int CMPP_TERMINATE_RESP = 0x80000002;
    
    /** 提交短信 */
    public static final int CMPP_SUBMIT = 0x00000004;
    
    /** 提交短信应答 */
    public static final int CMPP_SUBMIT_RESP = 0x80000004;
    
    /** 短信上行 */
    public static final int CMPP_DELIVER = 0x00000005;
    
    /** 短信上行应答 */
    public static final int CMPP_DELIVER_RESP = 0x80000005;
    
    /** 发送短信状态查询 */
    public static final int CMPP_QUERY = 0x00000006;
    
    /** 短信状态查询应答 */
    public static final int CMPP_QUERY_RESP = 0x80000006;
    
    /** 删除短信 */
    public static final int CMPP_CANCEL = 0x00000007;
    
    /** 删除短信应答 */
    public static final int CMPP_CANCEL_RESP = 0x80000007;
    
    /** 激活测试 */
    public static final int CMPP_ACTIVE_TEST = 0x00000008;
    
    /** 激活测试应答 */
    public static final int CMPP_ACTIVE_TEST_RESP = 0x80000008;
    
    /** bind 响应消息体长 */
    public static final int LEN_CONNECT_RESP_BODY = 18;
    
    /** Submit 响应消息体长 */
    public static final int LEN_SUBMIT_RESP_BODY = 147;
    
}
