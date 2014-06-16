/*
 * 文 件 名:  DP3Message.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright 2008-2009,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  f00150373
 * 修改时间:  2010-3-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cl.rermote.message;

/**
 * 远程管理消息
 * 
 * @author  f00150373
 * @version  [版本号, 2010-3-25]
 * @see  [相关类/方法]
 * @since  DPFV100R001C01B010
 */
public abstract class DP3Message implements DP3Constants
{
    // 连接会话号
    private String sessionID;
    
    // 流水号
    private short messageSerial;
    
    /**
     * 得到消息类型。
     * @return msgType 消息类型
     */
    public abstract byte getMessageType();
    
    /**
     * 增加这个方法是为了让Decoder能够正常工作。
     * @param messageType messageType
     * @see [类、类#方法、类#成员]
     */
    public void setMessageType(byte messageType)
    {
        // this.messageType = messageType;
    }
    
    public String getSessionID()
    {
        return this.sessionID;
    }
    
    public void setSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }
    
    public short getMessageSerial()
    {
        return this.messageSerial;
    }
    
    public void setMessageSerial(short messageSerial)
    {
        this.messageSerial = messageSerial;
    }
    
    /**
     * 大小
     * @return 长度
     */
    public int getAboutSize()
    {
        return 256;
    }
    
    /**
     * 编码
     * @return byte[]
     * @see [类、类#方法、类#成员]
     */
    /*public byte[] encode()
    {
        
        EncodeData data = new EncodeData(this.getAboutSize());
        Encoder encoder = new Encoder(data);
        
        data.write(RemoteConstants.RESV1); // Magic Number 1
        data.write(RemoteConstants.RESV2); // Magic Number 2
        data.write(RemoteConstants.RESV3); // Magic Number 3
        data.write(RemoteConstants.RESV4); // Tag of Message-Length
        encoder.encode(this, false);
        
        return data.getValidData();
    }*/
    
}
