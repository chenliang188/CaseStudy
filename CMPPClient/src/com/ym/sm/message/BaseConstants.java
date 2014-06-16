/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-30��yinmin ������ 
 */
package com.ym.sm.message;

/**
 * ��Ϣ�����ֳ���
 * 
 * @author yinmin
 */
public interface BaseConstants
{
    
    /** �������� */
    public static final int CMPP_CONNECT = 0x00000001;
    
    /** ��������Ӧ�� */
    public static final int CMPP_CONNECT_RESP = 0x80000001;
    
    /** ��ֹ���� */
    public static final int CMPP_TERMINATE = 0x00000002;
    
    /** ��ֹ����Ӧ�� */
    public static final int CMPP_TERMINATE_RESP = 0x80000002;
    
    /** �ύ���� */
    public static final int CMPP_SUBMIT = 0x00000004;
    
    /** �ύ����Ӧ�� */
    public static final int CMPP_SUBMIT_RESP = 0x80000004;
    
    /** �������� */
    public static final int CMPP_DELIVER = 0x00000005;
    
    /** ��������Ӧ�� */
    public static final int CMPP_DELIVER_RESP = 0x80000005;
    
    /** ���Ͷ���״̬��ѯ */
    public static final int CMPP_QUERY = 0x00000006;
    
    /** ����״̬��ѯӦ�� */
    public static final int CMPP_QUERY_RESP = 0x80000006;
    
    /** ɾ������ */
    public static final int CMPP_CANCEL = 0x00000007;
    
    /** ɾ������Ӧ�� */
    public static final int CMPP_CANCEL_RESP = 0x80000007;
    
    /** ������� */
    public static final int CMPP_ACTIVE_TEST = 0x00000008;
    
    /** �������Ӧ�� */
    public static final int CMPP_ACTIVE_TEST_RESP = 0x80000008;
    
    /** bind ��Ӧ��Ϣ�峤 */
    public static final int LEN_CONNECT_RESP_BODY = 18;
    
    /** Submit ��Ӧ��Ϣ�峤 */
    public static final int LEN_SUBMIT_RESP_BODY = 147;
    
}
