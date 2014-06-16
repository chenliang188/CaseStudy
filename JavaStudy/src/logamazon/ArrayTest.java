/*
 * 文 件 名:  ArrayTest.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2010-8-31
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package logamazon;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2010-8-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArrayTest
{
    
    /** <一句话功能简述>
     * <功能详细描述>
     * @param args [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        //int twoD[][] = new int[][4];
                
        String str = "aa";
        try
        {
            str = "bb";
            //int b = 1/0;
            System.out.println(str);
            return;
        }
        catch (Exception e)
        { 
            str   =   "cc "; 
            System.out.println(str);
        } 
        finally 
        { 
            str   =   "dd "; 
            System.out.println(str);
        }
    }
}
