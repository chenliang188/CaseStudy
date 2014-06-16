/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-30，yinmin 创建。 
 */
package com.ym.sm.link;

/**
 * 连接到网关的配置信息
 * 
 * @author yinmin
 */
public class Config
{
    
    /** sp_id */
    private String userName;
    
    /** shared_secret */
    private String password;
    
    /** IP地址 */
    private String ip;
    
    /** 端口 */
    private int port;
    
    /** 握手时间 */
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
