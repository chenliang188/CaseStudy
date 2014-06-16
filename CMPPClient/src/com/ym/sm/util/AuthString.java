/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-30��yinmin ������ 
 */
package com.ym.sm.util;

/**
 * ��ȡ��Ȩ��
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
