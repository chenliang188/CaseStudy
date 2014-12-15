/*
 * 文 件 名:  SubClass.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  陈亮
 * 修改时间:  2011-7-10
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package orange;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  陈亮
 * @version  [版本号, 2011-7-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SubClass extends Parent
{
    public static String s_Static = "c";
    
    public String s_Field = "d";
    
    public SubClass()
    {
        System.out.println("1=");
    }
    
    {
        System.out.println("2=");        
    }
    
    static 
    {
        System.out.println("3=");
    }    
    
    public static void main(String[] args)
    {
        new SubClass();
    }
}
