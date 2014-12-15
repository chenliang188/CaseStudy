package logamazon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  陈亮
 * @version  [版本号, 2010-7-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AllLog
{
    /**
     * 将日志中的信息读取出来，返回一个HashMap。
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return HashMap [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public HashMap<String, ArrayList<String>> getAllLog(String path)
    {
        BufferedReader br;
        HashMap<String, ArrayList<String>> log = new HashMap<String, ArrayList<String>>();
        String[] nameBalance = null;
        ArrayList<String> listlog = null;
        try
        {
            br = new BufferedReader(new FileReader(new File(path)));
            
            String line = null;
            while (true)
            {
                line = br.readLine();
                if (line == null)
                {
                    break;
                }
                
                nameBalance = line.split(" ");
                
                if (log.containsKey(nameBalance[0]))
                {
                    //listlog = log.get(nameBalance[0]);
                    //listlog.add(nameBalance[1]);
                    log.get(nameBalance[0]).add(nameBalance[1]);
                }
                else
                {
                    //ArrayList<String> logfirst = new ArrayList<String>();
                    listlog = new ArrayList<String>();
                    listlog.add(nameBalance[1]);
                    log.put(nameBalance[0], listlog);
                }
                
                //log.put(nameBalance[0], nameBalance[1]);
            }           
        }
        catch (FileNotFoundException e)
        {
            System.out.println("没有日志文件");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("读取文件内容失败");
            e.printStackTrace();
        }
        
        return log;
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param args [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        AllLog chen = new AllLog();
        
        HashMap<String, ArrayList<String>> liang = new HashMap<String, ArrayList<String>>();
        
        liang = chen.getAllLog("G:\\JavaStudy\\src\\logamazon\\test.txt");
        
        for(Entry<String, ArrayList<String>> entry : liang.entrySet())   
        {   
            System.out.println(entry.getKey()+": "+entry.getValue());   
        }
    }    
}
