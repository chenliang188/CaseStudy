/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-30��yinmin ������ 
 */
package com.ym.sm.link;

/**
 * ���ӵ����ص�������Ϣ
 * 
 * @author yinmin
 */
public class Config
{
    
    /** sp_id */
    private String userName;
    
    /** shared_secret */
    private String password;
    
    /** IP��ַ */
    private String ip;
    
    /** �˿� */
    private int port;
    
    /** ����ʱ�� */
    private int shakeHandTime;
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getIp()
    {
        return ip;
    }
    
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public void setPort(int port)
    {
        this.port = port;
    }
    
    public int getShakeHandTime()
    {
        return shakeHandTime;
    }
    
    public void setShakeHandTime(int shakeHandTime)
    {
        this.shakeHandTime = shakeHandTime;
    }
}
