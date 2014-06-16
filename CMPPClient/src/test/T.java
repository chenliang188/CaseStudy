/*
 * 版权所有 (C) 2001-2009 ym 保留所有权利。
 * 版本：V1.0
 * 修改记录：
 *		1、2009-7-31，yinmin 创建。 
 */
package test;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author yinmin
 */
public class T
{
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        System.out.println(getCmppDateStr("2008-03-02 11:00:00"));
    }
    
    public static String getCmppDateStr(String s)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        char c = '+';
        TimeZone timezone = null;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try
        {
            date = formater.parse(s);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        i = calendar.get(1) - 2000;
        j = calendar.get(2) + 1;
        k = calendar.get(5);
        l = calendar.get(11);
        i1 = calendar.get(12);
        j1 = calendar.get(13);
        k1 = calendar.get(14) / 100;
        timezone = calendar.getTimeZone();
        c = '+';
        long l2 = timezone.getRawOffset();
        if (l2 < 0L)
            c = '-';
        l1 = (int)Math.abs(l2) / 0xdbba0;
        Object aobj[] =
            {new Integer(i), new Integer(j), new Integer(k), new Integer(l), new Integer(i1), new Integer(j1),
                new Integer(k1), new Integer(l1), new Character(c)};
        return MessageFormat.format("{0,number,00}{1,number,00}{2,number,00}{3,number,00}{4,number,00}{5,number,00}{6,number,0}{7,number,00}{8}",
            aobj);
    }
    
}
