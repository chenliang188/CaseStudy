/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-29，yinmin 创建。 
 */
package com.ym.sm.link;

import com.ym.sm.message.DeliverMessage;

/**
 * 接收MO消息的监听器
 * 
 * @author yinmin
 */
public interface DeliveryMsgListener
{
    
    /**
     * 收到MO消息的处理方法
     * 
     * @param deMessage
     */
    public void onDeliver(DeliverMessage deMessage);
}
