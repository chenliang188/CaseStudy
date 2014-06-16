/*
 * 文 件 名:  DP3Constants.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright 2008-2009,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  f00150373
 * 修改时间:  2010-3-26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cl.rermote.message;

/**
 * DP3Message
 * 
 * @author  f00150373
 * @version  [版本号, 2010-3-26]
 * @see  [相关类/方法]
 * @since  DPFV100R001C01B010
 */
public interface DP3Constants
{
    /**
     * DP3MessageType
     * @author  lhy55051
     * @version  [版本号, Apr 2, 2010]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    interface DP3MessageType
    {
        static final byte DP3_LOGIN_REQ = (byte)0x01;
        
        static final byte DP3_LOGIN_RES = (byte)0x02;
        
        static final byte DP3_HEART_BEAT_REQ = (byte)0x03;
        
        static final byte DP3_HEART_BEAT_RES = (byte)0x04;
        
        static final byte DP3_LOGOUT_REQ = (byte)0x05;
        
        static final byte DP3_LOGOUT_RES = (byte)0x06;
        
        static final byte DP3_ADD_ALBUM_REQ = (byte)0x11;
        
        static final byte DP3_ADD_ALBUM_RES = (byte)0x12;
        
        static final byte DP3_DELETE_ALBUM_REQ = (byte)0x13;
        
        static final byte DP3_DELETE_ALBUM_RES = (byte)0x14;
        
        static final byte DP3_MODIFY_ALBUM_REQ = (byte)0x15;
        
        static final byte DP3_MODIFY_ALBUM_RES = (byte)0x16;
        
        static final byte DP3_QUERY_ALBUM_REQ = (byte)0x17;
        
        static final byte DP3_QUERY_ALBUM_RES = (byte)0x18;
        
        static final byte DP3_DELETE_PICTURE_REQ = (byte)0x23;
        
        static final byte DP3_DELETE_PICTURE_RES = (byte)0x24;
        
        static final byte DP3_MODIFY_PICTURE_REQ = (byte)0x25;
        
        static final byte DP3_MODIFY_PICTURE_RES = (byte)0x26;
        
        static final byte DP3_QUERY_PICTURE_REQ = (byte)0x27;
        
        static final byte DP3_QUERY_PICTURE_RES = (byte)0x28;
        
        static final byte DP3_MOVE_PICTURE_REQ = (byte)0x29;
        
        static final byte DP3_MOVE_PICTURE_RES = (byte)0x2A;
        
        static final byte DP3_SYNC_BROWSE_PICTURE_REQ = (byte)0x2B;
        
        static final byte DP3_SYNC_BROWSE_PICTURE_RES = (byte)0x2C;
    }
}
