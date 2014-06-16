/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-30，yinmin 创建。 
 */
package com.ym.sm.util;

/**
 * 获取鉴权串
 * @author yinmin
 */
public class AuthString
{
    
    public static byte[] getAuthString(String userName, String password, String timestamp)
    {
        
        if (userName == null || password == null || timestamp == null)
        {
            return null;
        }
        byte[] user = userName.getBytes();
        byte[] pass = password.getBytes();
        byte[] timest = timestamp.getBytes();
        
        byte[] str = new byte[9 + user.length + pass.length + timest.length];
        System.arraycopy(user, 0, str, 0, user.length);
        System.arraycopy(pass, 0, str, 9 + user.length, pass.length);
        System.arraycopy(timest, 0, str, 9 + user.length + pass.length, timest.length);
        return str;
    }
}
