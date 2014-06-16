/*
 * ��Ȩ���� (C) 2001-2005 �����а���Ӧ��ϵͳ���޹�˾����������Ȩ����
 * �汾��
 * �޸ļ�¼��
 *		1��2005-10-10��zouchenggang������ 
 */
package com.ym.sm.util;

import java.io.UnsupportedEncodingException;

import net.i314.util.OffSet;

/**
 * �����ֽڵ��ֽ���װ�࣬��Ҫ�ֽ���ǰ��˳��
 */
public class NetBits
{
    private static String encoding = "GBK";
    
    /**********************************************************/
    //���·���Ϊ�ڸ�����ƫ��λ,��ָ�����ֽ������ȡ���ݵķ���
    /**********************************************************/
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��byte<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��byte
     */
    public static byte getByte(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 1);
        
        return b[off];
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��boolean<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��boolean
     */
    public static boolean getBoolean(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 1);
        
        return b[off] != 0;
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ�� <b>���ֽ�</b> ��char<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��char
     */
    public static char getChar1(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 1);
        
        return (char)((b[off + 0] & 0xFF));
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ�� <b>˫�ֽ�</b> ��char<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��char
     */
    public static char getChar2(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 2);
        
        return (char)(((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��short<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��short
     */
    public static short getShort(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 2);
        
        return (short)(((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��int<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��int
     */
    public static int getInt(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 4);
        
        return ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8) + ((b[off + 1] & 0xFF) << 16)
            + ((b[off + 0] & 0xFF) << 24);
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��float<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��float
     */
    public static float getFloat(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 4);
        
        int i =
            ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8) + ((b[off + 1] & 0xFF) << 16)
                + ((b[off + 0] & 0xFF) << 24);
        return Float.intBitsToFloat(i);
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��long<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��long
     */
    public static long getLong(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 8);
        
        return ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8) + ((b[off + 5] & 0xFFL) << 16)
            + ((b[off + 4] & 0xFFL) << 24) + ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
            + ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��double<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��double
     */
    public static double getDouble(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        offset.setOff(off + 8);
        
        long j =
            ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8) + ((b[off + 5] & 0xFFL) << 16)
                + ((b[off + 4] & 0xFFL) << 24) + ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
                + ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
        return Double.longBitsToDouble(j);
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ������Ϊlen��byte[]<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param len ����ĳ���
     * @return byte ����һ��byte[]
     */
    public static byte[] getBytes(byte[] b, OffSet offset, int len)
    {
        int off = offset.getOff();
        
        byte[] bytes = new byte[len];
        if (len + off <= b.length)
            System.arraycopy(b, off, bytes, 0, len);
        else
            System.arraycopy(b, off, bytes, 0, b.length - off);
        
        offset.setOff(off + len);
        return bytes;
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ����bĩ�˵�byte[]<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @return byte ����һ��byte[]
     */
    public static byte[] getBytes(byte[] b, OffSet offset)
    {
        int off = offset.getOff();
        byte[] bytes = new byte[b.length - off];
        System.arraycopy(b, off, bytes, 0, b.length - off);
        
        offset.setOff(off + bytes.length);
        return bytes;
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ����������Ϊvalue��String<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param value ������
     * @return byte ����һ��String
     */
    public static String getString(byte[] b, OffSet offset, byte value)
    {
        int off = offset.getOff();
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
        String str = null;
        try
        {
            str = new String(tmp, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            str = new String(tmp);
        }
        
        offset.setOff(i + 1);
        return str;
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��������String<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param len ����
     * @return byte ����һ��String
     */
    public static String getString(byte[] b, OffSet offset, int len)
    {
        int off = offset.getOff();
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
            str = new String(tmp);
        }
        
        offset.setOff(off + len);
        return str;
    }
    
    /**
     * ���ֽ�����b�� ����ƫ����offset����һ��������String<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param len ����
     * @param hasEnd �н�����
     * @return byte ����һ��String
     */
    public static String getString(byte[] b, OffSet offset, int len, boolean hasEnd)
    {
        int off = offset.getOff();
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
            str = new String(tmp);
        }
        
        if (hasEnd)
            offset.setOff(off + len + 1);
        else
            offset.setOff(off + len);
        
        return str;
    }
    
    /**********************************************************/
    //���·���Ϊ�ڸ�����ƫ��λ,������ֵ,���뵽ָ�����ֽ�����ķ���
    /**********************************************************/
    
    /**
     * ����һ���ֽ�<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val
     */
    public static void putByte(byte[] b, OffSet offset, byte val)
    {
        int off = offset.getOff();
        b[off] = val;
        
        offset.setOff(off + 1);
    }
    
    /**
     * ����һ��boolean<br>
     * 
     * @param b �ֽ�����
     * @param offset
     * @param val
     */
    public static void putBoolean(byte[] b, OffSet offset, boolean val)
    {
        int off = offset.getOff();
        b[off] = (byte)(val ? 1 : 0);
        
        offset.setOff(off + 1);
    }
    
    /**
     * ����һ�� <b>���ֽ�</b> ��char<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val ���ֽ�char
     */
    public static void putChar1(byte[] b, OffSet offset, char val)
    {
        int off = offset.getOff();
        b[off + 0] = (byte)(val & 0xFF);
        
        offset.setOff(off + 1);
    }
    
    /**
     * ����һ�� <b>˫�ֽ�</b> ��char<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val char ˫�ֽ�char
     */
    public static void putChar2(byte[] b, OffSet offset, char val)
    {
        int off = offset.getOff();
        b[off + 1] = (byte)(val >>> 0);
        b[off + 0] = (byte)(val >>> 8);
        
        offset.setOff(off + 2);
    }
    
    /**
     * ����һ��short<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val short
     */
    public static void putShort(byte[] b, OffSet offset, short val)
    {
        int off = offset.getOff();
        b[off + 1] = (byte)(val >>> 0);
        b[off + 0] = (byte)(val >>> 8);
        
        offset.setOff(off + 2);
    }
    
    /**
     * ����һ��int<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val int
     */
    public static void putInt(byte[] b, OffSet offset, int val)
    {
        int off = offset.getOff();
        b[off + 3] = (byte)(val >>> 0);
        b[off + 2] = (byte)(val >>> 8);
        b[off + 1] = (byte)(val >>> 16);
        b[off + 0] = (byte)(val >>> 24);
        
        offset.setOff(off + 4);
    }
    
    /**
     * ����һ��float<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val float
     */
    public static void putFloat(byte[] b, OffSet offset, float val)
    {
        int off = offset.getOff();
        int i = Float.floatToIntBits(val);
        b[off + 3] = (byte)(i >>> 0);
        b[off + 2] = (byte)(i >>> 8);
        b[off + 1] = (byte)(i >>> 16);
        b[off + 0] = (byte)(i >>> 24);
        
        offset.setOff(off + 4);
    }
    
    /**
     * ����һ��long<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val long
     */
    public static void putLong(byte[] b, OffSet offset, long val)
    {
        int off = offset.getOff();
        b[off + 7] = (byte)(val >>> 0);
        b[off + 6] = (byte)(val >>> 8);
        b[off + 5] = (byte)(val >>> 16);
        b[off + 4] = (byte)(val >>> 24);
        b[off + 3] = (byte)(val >>> 32);
        b[off + 2] = (byte)(val >>> 40);
        b[off + 1] = (byte)(val >>> 48);
        b[off + 0] = (byte)(val >>> 56);
        
        offset.setOff(off + 8);
    }
    
    /**
     * ����һ��double<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param val double
     */
    public static void putDouble(byte[] b, OffSet offset, double val)
    {
        int off = offset.getOff();
        long j = Double.doubleToLongBits(val);
        b[off + 7] = (byte)(j >>> 0);
        b[off + 6] = (byte)(j >>> 8);
        b[off + 5] = (byte)(j >>> 16);
        b[off + 4] = (byte)(j >>> 24);
        b[off + 3] = (byte)(j >>> 32);
        b[off + 2] = (byte)(j >>> 40);
        b[off + 1] = (byte)(j >>> 48);
        b[off + 0] = (byte)(j >>> 56);
        
        offset.setOff(off + 8);
    }
    
    /**
     * ����һ���������ֽ�����<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param src Ҫ������ַ�����
     */
    public static void putBytes(byte[] b, OffSet offset, byte[] src)
    {
        int off = offset.getOff();
        System.arraycopy(src, 0, b, off, src.length);
        
        offset.setOff(off + src.length);
    }
    
    /**
     * ����һ�������ֽ�����<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param src Ҫ������ַ�����
     * @param len ����
     */
    public static void putBytes(byte[] b, OffSet offset, byte[] src, int len)
    {
        int off = offset.getOff();
        System.arraycopy(src, 0, b, off, len);
        
        offset.setOff(off + len);
    }
    
    /**
     * ����һ����������String,��str.getBytes().length������,�޽�����<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param str �ֽڴ�
     */
    public static void putString(byte[] b, OffSet offset, String str)
    {
        int off = offset.getOff();
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
        
        System.arraycopy(ret, 0, b, off, ret.length);
        
        offset.setOff(off + ret.length);
    }
    
    /**
     * ����һ��������String,��str.getBytes().length������,��endValue����<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param str �ֽڴ�
     * @param endValue ������
     */
    public static void putString(byte[] b, OffSet offset, String str, byte endValue)
    {
        int off = offset.getOff();
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
        
        System.arraycopy(ret, 0, b, off, ret.length);
        b[off + ret.length] = endValue;
        
        offset.setOff(off + ret.length + 1);
    }
    
    /**
     * ����һ��������String,�޽�����<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param str �ֽڴ�
     * @param len ����
     */
    public static void putString(byte[] b, OffSet offset, String str, int len)
    {
        int off = offset.getOff();
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
        
        offset.setOff(off + len);
    }
    
    /**
     * ����һ��������String,����һ��byte����<br>
     * 
     * @param b �ֽ�����
     * @param offset ƫ����
     * @param str �ֽڴ�
     * @param len ����
     * @param endValue ������
     */
    public static void putString(byte[] b, OffSet offset, String str, int len, byte endValue)
    {
        int off = offset.getOff();
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
        
        b[off + ret.length] = endValue;
        offset.setOff(off + len + 1);
    }
    
    /******************************************/
    //���·���Ϊ�ڸ�����int��ƫ��λ,������ֵ,���뵽ָ�����ֽ�����ķ���
    /******************************************/
    
    /*
     * Methods for unpacking primitive values from byte arrays starting at given
     * offsets.
     */

    /**
     * ����ֽ������ָ���ֽڵĲ���ֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ���ֽڵĲ���ֵ
     */
    public static boolean getBoolean(byte[] b, int off)
    {
        return b[off] != 0;
    }
    
    /**
     * ����ֽ������ָ���ֽڵĵ��ַ�ֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ���ֽڵĵ��ַ�ֵ
     */
    public static char getChar1(byte[] b, int off)
    {
        return (char)((b[off + 0] & 0xFF));
    }
    
    /**
     * ����ֽ������ָ��λ�ÿ�ʼ��˫�ַ�ֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ���ֽڿ�ʼ��˫�ַ�ֵ
     */
    public static char getChar2(byte[] b, int off)
    {
        return (char)(((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
    }
    
    /**
     * ����ֽ������ָ��λ�ÿ�ʼ�Ķ��֣�˫�ֽڣ�����ֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ��λ�ÿ�ʼ�Ķ��֣�˫�ֽڣ�����ֵ
     */
    public static short getShort(byte[] b, int off)
    {
        return (short)(((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
    }
    
    /**
     * ����ֽ������ָ��λ�ÿ�ʼ�����ֽ�����ֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ��λ�ÿ�ʼ�����ֽ�����ֵ
     */
    public static int getInt(byte[] b, int off)
    {
        return ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8) + ((b[off + 1] & 0xFF) << 16)
            + ((b[off + 0] & 0xFF) << 24);
    }
    
    /**
     * ����ֽ������ָ��λ�ÿ�ʼ�����ֽ�floatֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ��λ�ÿ�ʼ�����ֽ�floatֵ
     */
    public static float getFloat(byte[] b, int off)
    {
        int i =
            ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8) + ((b[off + 1] & 0xFF) << 16)
                + ((b[off + 0] & 0xFF) << 24);
        return Float.intBitsToFloat(i);
    }
    
    /**
     * ����ֽ������ָ��λ�ÿ�ʼ�İ��ֽ�long����ֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ��λ�ÿ�ʼ�İ��ֽ�long����ֵ
     */
    public static long getLong(byte[] b, int off)
    {
        return ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8) + ((b[off + 5] & 0xFFL) << 16)
            + ((b[off + 4] & 0xFFL) << 24) + ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
            + ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
    }
    
    /**
     * ����ֽ������ָ��λ�ÿ�ʼ�İ��ֽ�doubleֵ
     * @param b �ֽ�����
     * @param off ��������
     * @return ָ��λ�ÿ�ʼ�İ��ֽ�doubleֵ
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
     * ���ش������ָ��λ�ÿ�ʼ����ָ��ֵ��β���ַ���
     * @param b �ֽ�����
     * @param off ƫ��ֵ
     * @param value �����ֽ�
     * @return �������ָ��λ��off��ʼ����ָ��ֵ��βvalue���ַ���
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
     * ���ش������ָ��λ�ÿ�ʼ��ָ�����ȵ��ַ���
     * @param b �ֽ�����
     * @param off ����ƫ��ֵ
     * @param len ָ������
     * @return �������ָ��λ��off��ʼ��ָ������len���ַ���
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
     * �����ֽ�����ָ��ƫ�Ƶ��ֽ�
     * @param b �ֽ�����
     * @param off ƫ������
     * @return �ֽ�����ָ��ƫ�Ƶ��ֽ�
     */
    public static byte getByte(byte[] b, int off)
    {
        return b[off];
    }
    
    /**
     * �����ֽ�����ָ��ƫ�ƿ�ʼ��ָ�����ȵ��ֽ�����
     * @param b ԭ�ֽ�����
     * @param off ָ��ƫ��
     * @param len ָ������
     * @return �ֽ�����ָ��ƫ�ƿ�ʼ��ָ�����ȵ��ֽ�����
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
     * �����ֽ�����ָ��ƫ�ƿ�ʼ���ֽ�����
     * @param b �ֽ�����
     * @param off ָ��ƫ��
     * @return �ֽ�����ָ��ƫ�ƿ�ʼ���ֽ�����
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
     * �����ֽ������ָ��λ��Ϊָ���Ĳ���ֵ
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���õĲ���ֵ
     * @return ���ú���ֽ�ƫ��ֵ
     */
    public static int putBoolean(byte[] b, int off, boolean val)
    {
        b[off] = (byte)(val ? 1 : 0);
        
        return off + 1;
    }
    
    /**
     * �����ֽ������ָ��λ��Ϊָ�����ַ�ֵ
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���õ�charֵ
     * @return ���ú������ƫ��ֵ
     */
    public static int putChar1(byte[] b, int off, char val)
    {
        b[off + 0] = (byte)(val & 0xFF);
        
        return off + 1;
    }
    
    /**
     * �����ֽ������ָ��λ��Ϊָ����˫�ֽ��ַ�ֵ
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���õ�˫�ֽ�charֵ
     * @return ���ú������ƫ��ֵ
     */
    public static int putChar2(byte[] b, int off, char val)
    {
        b[off + 1] = (byte)(val >>> 0);
        b[off + 0] = (byte)(val >>> 8);
        
        return off + 2;
    }
    
    /**
     * �����ֽ������ָ��λ��Ϊָ����˫�ֽ�����ֵ
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���õ�˫�ֽ�����ֵ
     * @return ���ú������ƫ��ֵ
     */
    public static int putShort(byte[] b, int off, short val)
    {
        b[off + 1] = (byte)(val >>> 0);
        b[off + 0] = (byte)(val >>> 8);
        
        return off + 2;
    }
    
    /**
     * �����ֽ������ָ��λ��Ϊָ�������ֽ�����ֵ
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���õ����ֽ�����ֵ
     * @return ���ú������ƫ��ֵ
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
     * ת��ָ���ֽڳ�
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
     * ���������ַ������ֽ������У������Ҳ���
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
     * �����ֽ������ָ��λ��Ϊָ�������ֽ�floatֵ
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���õ����ֽ�float
     * @return ���ú������ƫ��ֵ
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
     * �����ֽ������ָ��λ��Ϊָ���İ��ֽ�����ֵ
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���õİ��ֽ�����ֵ
     * @return ���ú������ƫ��ֵ
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
     * �����ֽ������ָ��λ��Ϊָ���İ��ֽ�double
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val ���ֽ�double
     * @return ���ú������ƫ��ֵ
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
     * �����ֽ������ָ��λ��Ϊָ��byte
     * @param b �ֽ�����
     * @param off ƫ��
     * @param val byteֵ
     * @return ���ú������ƫ��ֵ
     */
    public static int putByte(byte[] b, int off, byte val)
    {
        b[off] = val;
        
        return off + 1;
    }
    
    /**
     * �����ֽ������ָ��λ��Ϊָ���ֽ�����
     * @param b Ŀ���ֽ�����
     * @param off ƫ��
     * @param src ָ�����ֽ�����
     * @return ���ú������ƫ��ֵ
     */
    public static int putBytes(byte[] b, int off, byte[] src)
    {
        System.arraycopy(src, 0, b, off, src.length);
        
        return off + src.length;
    }
    
    /**
     * �����ֽ������ָ��λ��Ϊָ���ֽ�����
     * @param b Ŀ���ֽ�����
     * @param off ƫ��
     * @param src Դ�ֽ�����
     * @param len Դ�ֽ����鳤��
     * @return ���ú������ƫ��ֵ
     */
    public static int putBytes(byte[] b, int off, byte[] src, int len)
    {
        System.arraycopy(src, 0, b, off, len);
        
        return off + len;
    }
    
    /**
     * �����ֽ������ָ��λ��Ϊָ����String
     * @param b Ŀ���ֽ�����
     * @param off ƫ��
     * @param str ָ����String
     * @return ���ú������ƫ��ֵ
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
     * �����ֽ������ָ��λ��Ϊָ�����ȵ�String
     * @param b Ŀ���ֽ�����
     * @param off ƫ��
     * @param str ָ����String
     * @param len ָ������
     * @return ���ú������ƫ��ֵ
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
    //���·���ΪString,int����byte[]֮���ת��
    /**********************************************************/
    
    /**
     * ת��StringΪ�ֽ�����
     * @param str ָ����String
     * @return ת������ֽ�����
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
     * ת��StringΪָ�����ȵ��ֽ�����
     * @param str ָ����String
     * @param len ����ֽ����鳤��
     * @return ת������ֽ�����
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
     * ת��StringΪ�ֽ�����
     * @param str ָ����String
     * @param aEncoding ���뷽��
     * @return ת������ֽ�����
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
     * ת��StringΪָ�����ȵ��ֽ�����
     * @param str ָ����String
     * @param len ָ���ĳ���
     * @param aEncoding aEncoding ���뷽��
     * @return ת������ֽ�����
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
     * ת���ֽ�����Ϊ��д��ʮ�������ַ������м�ո��
     * @param b
     * @return ת����Ĵ�дʮ�������ַ������м�ո��
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
     * ת���ֽ�����Ϊ��д��ʮ�������ַ���
     * @param b
     * @return ת����Ĵ�дʮ�������ַ���
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