/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-29，yinmin 创建。 
 */
package com.ym.sm.message.encode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ym.sm.message.BaseMessage;

/**
 * 编码和解码接口
 * 
 * @author yinmin
 */
public interface IStreamCoder {

	/**
	 * 将消息流解码成消息对象
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public BaseMessage decode(InputStream input) throws IOException;

	/**
	 * 将消息编码后，写入output输出
	 * 
	 * @param obj
	 * @param output
	 * @throws IOException
	 */
	public void encode(Object obj, OutputStream output) throws IOException;
}
