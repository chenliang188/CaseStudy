/*
 * 文 件 名:  test.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  陈亮
 * 修改时间:  2011-7-31
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  陈亮
 * @version  [版本号, 2011-7-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class test
{    
    static Socket server;
    
    /** <一句话功能简述>
     * <功能详细描述>
     * @param args [参数说明]
     * 
     * @return void [返回类型说明]
     * @throws IOException 
     * @throws UnknownHostException 
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args) throws UnknownHostException, IOException
    {
        // TODO Auto-generated method stub
        server = new Socket(InetAddress.getLocalHost(), 5784);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String str = wt.readLine();
            out.println(str);
            out.flush();
            if (str.equals("end"))
            {
                break;
            }
            System.out.println(in.readLine());
        }
        server.close();
    }
    
}
