/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-29��yinmin ������ 
 */
package com.ym.sm.message.encode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ym.sm.message.BaseMessage;

/**
 * ����ͽ���ӿ�
 * 
 * @author yinmin
 */
public interface IStreamCoder {

	/**
	 * ����Ϣ���������Ϣ����
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public BaseMessage decode(InputStream input) throws IOException;

	/**
	 * ����Ϣ�����д��output���
	 * 
	 * @param obj
	 * @param output
	 * @throws IOException
	 */
	public void encode(Object obj, OutputStream output) throws IOException;
}
