/*
 * 版权所有 (C) 2001-2005 深圳市艾派应用系统有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2005-10-10，zouchenggang创建。 
 */
package com.cl.rermote.util;

import java.io.UnsupportedEncodingException;

/**
 * 网络字节的字节组装类，重要字节在前的顺序
 */
public class Util
{
    private static String encoding = "GBK";
    
    /*
     * Methods for unpacking primitive values from byte arrays starting at given
     * offsets.
     */

    /**
     * 获得字节数组的指定字节的布尔值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定字节的布尔值
     */
    public static boolean getBoolean(byte[] b, int off)
    {
        return b[off] != 0;
    }
    
    /**
     * 获得字节数组的指定字节的单字符值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定字节的单字符值
     */
    public static char getChar1(byte[] b, int off)
    {
        return (char)((b[off + 0] & 0xFF));
    }
    
    /**
     * 获得字节数组的指定位置开始的双字符值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定字节开始的双字符值
     */
    public static char getChar2(byte[] b, int off)
    {
        return (char)(((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
    }
    
    /**
     * 获得字节数组的指定位置开始的短字（双字节）整数值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定位置开始的短字（双字节）整数值
     */
    public static short getShort(byte[] b, int off)
    {
        return (short)(((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
    }
    
    /**
     * 获得字节数组的指定位置开始的四字节整数值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定位置开始的四字节整数值
     */
    public static int getInt(byte[] b, int off)
    {
        return ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8) + ((b[off + 1] & 0xFF) << 16)
            + ((b[off + 0] & 0xFF) << 24);
    }
    
    /**
     * 获得字节数组的指定位置开始的四字节float值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定位置开始的四字节float值
     */
    public static float getFloat(byte[] b, int off)
    {
        int i =
            ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8) + ((b[off + 1] & 0xFF) << 16)
                + ((b[off + 0] & 0xFF) << 24);
        return Float.intBitsToFloat(i);
    }
    
    /**
     * 获得字节数组的指定位置开始的八字节long整数值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定位置开始的八字节long整数值
     */
    public static long getLong(byte[] b, int off)
    {
        return ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8) + ((b[off + 5] & 0xFFL) << 16)
            + ((b[off + 4] & 0xFFL) << 24) + ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
            + ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
    }
    
    /**
     * 获得字节数组的指定位置开始的八字节double值
     * @param b 字节数组
     * @param off 数组索引
     * @return 指定位置开始的八字节double值
     */
    public static double getDouble(byte[] b, int off)
    {
        long j =
            ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8) + ((b[off + 5] & 0xFFL) << 16)
                + ((b[off + 4] & 0xFFL) << 24) + ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
                + ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
        return Double.longBitsToDouble(j);
    }
    
    /**
     * 返回从数组的指定位置开始，到指定值结尾的字符串
     * @param b 字节数组
     * @param off 偏移值
     * @param value 结束字节
     * @return 从数组的指定位置off开始，到指定值结尾value的字符串
     */
    public static String getString(byte[] b, int off, byte value)
    {
        int i = off;
        int allLen = b.length;
        while (i < allLen)
        {
            if (b[i] == value)
                break;
            
            i++;
        }
        
        byte[] tmp = new byte[i - off];
        System.arraycopy(b, off, tmp, 0, i - off);
        String str = new String(tmp);
        
        return str;
    }
    
    /**
     * 返回从数组的指定位置开始，指定长度的字符串
     * @param b 字节数组
     * @param off 数组偏移值
     * @param len 指定长度
     * @return 从数组的指定位置off开始，指定长度len的字符串
     */
    public static String getString(byte[] b, int off, int len)
    {
        int i = 0;
        while (i < len)
        {
            if (off + i >= b.length)
                break;
            if (0 == b[off + i])
                break;
            i++;
        }
        
        byte[] tmp = new byte[i];
        System.arraycopy(b, off, tmp, 0, i);
        
        String str = null;
        try
        {
            str = new String(tmp, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            str = new String();
        }
        
        return str;
    }
    
    /**
     * 返回字节数组指定偏移的字节
     * @param b 字节数组
     * @param off 偏移索引
     * @return 字节数组指定偏移的字节
     */
    public static byte getByte(byte[] b, int off)
    {
        return b[off];
    }
    
    /**
     * 返回字节数组指定偏移开始的指定长度的字节数组
     * @param b 原字节数组
     * @param off 指定偏移
     * @param len 指定长度
     * @return 字节数组指定偏移开始的指定长度的字节数组
     */
    public static byte[] getBytes(byte[] b, int off, int len)
    {
        byte[] bytes = new byte[len];
        if (len + off <= b.length)
            System.arraycopy(b, off, bytes, 0, len);
        else
            System.arraycopy(b, off, bytes, 0, b.length - off);
        
        return bytes;
    }
    
    /**
     * 返回字节数组指定偏移开始的字节数组
     * @param b 字节数组
     * @param off 指定偏移
     * @return 字节数组指定偏移开始的字节数组
     */
    public static byte[] getBytes(byte[] b, int off)
    {
        byte[] bytes = new byte[b.length - off];
        System.arraycopy(b, off, bytes, 0, b.length - off);
        
        return bytes;
    }
    
    /*
     * Methods for packing primitive values into byte arrays starting at given
     * offsets.
     */

    /**
     * 设置字节数组的指定位置为指定的布尔值
     * @param b 字节数组
     * @param off 偏移
     * @param val 设置的布尔值
     * @return 设置后的字节偏移值
     */
    public static int putBoolean(byte[] b, int off, boolean val)
    {
        b[off] = (byte)(val ? 1 : 0);
        
        return off + 1;
    }
    
    /**
     * 设置字节数组的指定位置为指定的字符值
     * @param b 字节数组
     * @param off 偏移
     * @param val 设置的char值
     * @return 设置后的数组偏移值
     */
    public static int putChar1(byte[] b, int off, char val)
    {
        b[off + 0] = (byte)(val & 0xFF);
        
        return off + 1;
    }
    
    /**
     * 设置字节数组的指定位置为指定的双字节字符值
     * @param b 字节数组
     * @param off 偏移
     * @param val 设置的双字节char值
     * @return 设置后的数组偏移值
     */
    public static int putChar2(byte[] b, int off, char val)
    {
        b[off + 1] = (byte)(val >>> 0);
        b[off + 0] = (byte)(val >>> 8);
        
        return off + 2;
    }
    
    /**
     * 设置字节数组的指定位置为指定的双字节整数值
     * @param b 字节数组
     * @param off 偏移
     * @param val 设置的双字节整数值
     * @return 设置后的数组偏移值
     */
    public static int putShort(byte[] b, int off, short val)
    {
        b[off + 1] = (byte)(val >>> 0);
        b[off + 0] = (byte)(val >>> 8);
        
        return off + 2;
    }
    
    /**
     * 设置字节数组的指定位置为指定的四字节整数值
     * @param b 字节数组
     * @param off 偏移
     * @param val 设置的四字节整数值
     * @return 设置后的数组偏移值
     */
    public static int putInt(byte[] b, int off, int val)
    {
        b[off + 3] = (byte)(val >>> 0);
        b[off + 2] = (byte)(val >>> 8);
        b[off + 1] = (byte)(val >>> 16);
        b[off + 0] = (byte)(val >>> 24);
        
        return off + 4;
    }
    
    /**
     * 转成指定字节长
     * @param abyte0
     * @param i
     * @param j
     * @param k
     */
    public static final void putIntToBytes(byte[] b, int off, int num, int len)
    {
        byte[] b1 = new byte[len];
        int sw = ((len - 1) * 8);
        int mask = (0xff << sw);
        
        for (int l = 0; l < len; l++)
        {
            b1[l] = (byte)((num & mask) >>> sw);
            sw -= 8;
            mask >>>= 8;
        }
        
        System.arraycopy(b1, 0, b, off, len);
    }
    
    /**
     * 拷贝定长字符串到字节数组中，不足右补零
     * @param s
     * @param b
     * @param offset
     * @param len
     */
    public static final void putFixedStringToBytes(byte[] b, int offset, String s, int len)
    {
        if (s == null)
            s = "";
        
        byte data[] = new byte[len];
        byte[] tmp = new byte[len];
        data = s.getBytes();
        if (null == data)
            data = new byte[0];
        
        if (data.length >= len)
        {
            System.arraycopy(data, 0, tmp, 0, len);
        }
        else
        {
            System.arraycopy(data, 0, tmp, 0, data.length);
            for (int i = data.length; i < len; i++)
                tmp[i] = 0;
        }
        System.arraycopy(tmp, 0, b, offset, tmp.length);
    }
    
    /**
     * 设置字节数组的指定位置为指定的四字节float值
     * @param b 字节数组
     * @param off 偏移
     * @param val 设置的四字节float
     * @return 设置后的数组偏移值
     */
    public static int putFloat(byte[] b, int off, float val)
    {
        int i = Float.floatToIntBits(val);
        b[off + 3] = (byte)(i >>> 0);
        b[off + 2] = (byte)(i >>> 8);
        b[off + 1] = (byte)(i >>> 16);
        b[off + 0] = (byte)(i >>> 24);
        
        return off + 4;
    }
    
    /**
     * 设置字节数组的指定位置为指定的八字节整数值
     * @param b 字节数组
     * @param off 偏移
     * @param val 设置的八字节整数值
     * @return 设置后的数组偏移值
     */
    public static int putLong(byte[] b, int off, long val)
    {
        b[off + 7] = (byte)(val >>> 0);
        b[off + 6] = (byte)(val >>> 8);
        b[off + 5] = (byte)(val >>> 16);
        b[off + 4] = (byte)(val >>> 24);
        b[off + 3] = (byte)(val >>> 32);
        b[off + 2] = (byte)(val >>> 40);
        b[off + 1] = (byte)(val >>> 48);
        b[off + 0] = (byte)(val >>> 56);
        
        return off + 8;
    }
    
    /**
     * 设置字节数组的指定位置为指定的八字节double
     * @param b 字节数组
     * @param off 偏移
     * @param val 八字节double
     * @return 设置后的数组偏移值
     */
    public static int putDouble(byte[] b, int off, double val)
    {
        long j = Double.doubleToLongBits(val);
        b[off + 7] = (byte)(j >>> 0);
        b[off + 6] = (byte)(j >>> 8);
        b[off + 5] = (byte)(j >>> 16);
        b[off + 4] = (byte)(j >>> 24);
        b[off + 3] = (byte)(j >>> 32);
        b[off + 2] = (byte)(j >>> 40);
        b[off + 1] = (byte)(j >>> 48);
        b[off + 0] = (byte)(j >>> 56);
        
        return off + 8;
    }
    
    /**
     * 设置字节数组的指定位置为指定byte
     * @param b 字节数组
     * @param off 偏移
     * @param val byte值
     * @return 设置后的数组偏移值
     */
    public static int putByte(byte[] b, int off, byte val)
    {
        b[off] = val;
        
        return off + 1;
    }
    
    /**
     * 设置字节数组的指定位置为指定字节数组
     * @param b 目的字节数组
     * @param off 偏移
     * @param src 指定的字节数组
     * @return 设置后的数组偏移值
     */
    public static int putBytes(byte[] b, int off, byte[] src)
    {
        System.arraycopy(src, 0, b, off, src.length);
        
        return off + src.length;
    }
    
    /**
     * 设置字节数组的指定位置为指定字节数组
     * @param b 目的字节数组
     * @param off 偏移
     * @param src 源字节数组
     * @param len 源字节数组长度
     * @return 设置后的数组偏移值
     */
    public static int putBytes(byte[] b, int off, byte[] src, int len)
    {
        System.arraycopy(src, 0, b, off, len);
        
        return off + len;
    }
    
    /**
     * 设置字节数组的指定位置为指定的String
     * @param b 目的字节数组
     * @param off 偏移
     * @param str 指定的String
     * @return 设置后的数组偏移值
     */
    public static int putString(byte[] b, int off, String str)
    {
        if (str == null)
            str = "";
        
        byte[] ret = str.getBytes();
        System.arraycopy(ret, 0, b, off, ret.length);
        
        return off + ret.length;
    }
    
    /**
     * 设置字节数组的指定位置为指定长度的String
     * @param b 目的字节数组
     * @param off 偏移
     * @param str 指定的String
     * @param len 指定长度
     * @return 设置后的数组偏移值
     */
    public static int putString(byte[] b, int off, String str, int len)
    {
        if (str == null)
            str = "";
        
        byte[] ret = null;
        try
        {
            ret = str.getBytes(encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            ret = str.getBytes();
        }
        
        if (ret.length > len)
            System.arraycopy(ret, 0, b, off, len);
        else
            System.arraycopy(ret, 0, b, off, ret.length);
        
        return off + len;
    }
    
    /**********************************************************/
    //以下方法为String,int等与byte[]之间的转换
    /**********************************************************/
    
    /**
     * 转换String为字节数组
     * @param str 指定的String
     * @return 转换后的字节数组
     */
    public static byte[] toBytes(String str)
    {
        if (str == null)
            return new byte[0];
        
        try
        {
            return str.getBytes(encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("encoding error");
        }
    }
    
    /**
     * 转换String为指定长度的字节数组
     * @param str 指定的String
     * @param len 最大字节数组长度
     * @return 转换后的字节数组
     */
    public static byte[] toBytes(String str, int len)
    {
        if (str == null)
            return new byte[0];
        
        byte[] buf = null;
        
        try
        {
            buf = str.getBytes(encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("encoding error");
        }
        
        byte[] bytes = new byte[len];
        if (buf.length > len)
            System.arraycopy(buf, 0, bytes, 0, len);
        else
            System.arraycopy(buf, 0, bytes, 0, buf.length);
        
        return bytes;
    }
    
    /**
     * 转换String为字节数组
     * @param str 指定的String
     * @param aEncoding 编码方案
     * @return 转换后的字节数组
     */
    public static byte[] toBytes(String str, String aEncoding)
    {
        if (str == null)
            return new byte[0];
        
        try
        {
            return str.getBytes(aEncoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("encoding error");
        }
    }
    
    /**
     * 转换String为指定长度的字节数组
     * @param str 指定的String
     * @param len 指定的长度
     * @param aEncoding aEncoding 编码方案
     * @return 转换后的字节数组
     */
    public static byte[] toBytes(String str, int len, String aEncoding)
    {
        if (str == null)
            return new byte[0];
        
        try
        {
            byte[] buf = str.getBytes(aEncoding);
            byte[] bytes = new byte[len];
            if (buf.length > len)
                System.arraycopy(buf, 0, bytes, 0, len);
            else
                System.arraycopy(buf, 0, bytes, 0, buf.length);
            
            return bytes;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("encoding error");
        }
    }
    
    /**
     * 转换字节数组为大写的十六进制字符串，中间空格格开
     * @param b
     * @return 转换后的大写十六进制字符串，中间空格格开
     */
    public static String toHEXString(byte[] b)
    {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer s = new StringBuffer();
        char[] ob = new char[2];
        for (int i = 0; i < b.length; i++)
        {
            byte ib = b[i];
            ob[0] = Digit[(ib >>> 4) & 0X0F];
            ob[1] = Digit[ib & 0X0F];
            s.append(new String(ob) + " ");
        }
        return s.toString();
    }
    
    /**
     * 转换字节数组为大写的十六进制字符串
     * @param b
     * @return 转换后的大写十六进制字符串
     */
    public static String toHEXStringNoSpace(byte[] b)
    {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer s = new StringBuffer();
        char[] ob = new char[2];
        for (int i = b.length - 1; i >= 0; i--)
        {
            byte ib = b[i];
            ob[0] = Digit[(ib >>> 4) & 0X0F];
            ob[1] = Digit[ib & 0X0F];
            s.append(new String(ob));
        }
        return s.toString();
    }
}