/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-29��yinmin ������ 
 */
package com.ym.sm.link;

import com.ym.sm.message.DeliverMessage;

/**
 * ����MO��Ϣ�ļ�����
 * 
 * @author yinmin
 */
public interface DeliveryMsgListener
{
    
    /**
     * �յ�MO��Ϣ�Ĵ�����
     * 
     * @param deMessage
     */
    public void onDeliver(DeliverMessage deMessage);
}
