
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLSpirit
{
    static ArrayList<String> list = new ArrayList<String>();
    
    public static String delHTMLTag(String htmlStr)
    {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        //String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        //String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
        
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签 
        
        /*Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签 
        
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签 
        */
        return htmlStr.trim(); //返回文本字符串 
    }
    
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName)
    {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try
        {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                sb.append(tempString + "\r\n");
                //line++;
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e1)
                {
                }
            }
        }
        return sb.toString();
    }
    
    public static void WriteStringToFile(String filePath, String html)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(html.getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
    }
    
    public static void treeName(String pathName)
    {
        //解析路径
        File file = new File(pathName);
        //判断是否为文件夹
        if (file.isDirectory() == false)
        {
            System.out.println("请输入正确路径！");
            return;
        }
        //递归查找当前路径下所有的子目录
        File[] childFiles = file.listFiles();
        for (int i = 0; childFiles != null && i < childFiles.length; i++)
        {
            //System.out.println(tab + "┝" + childFiles[i].getName());
            if (childFiles[i].getName().endsWith(".htm"))
            {
                list.add(childFiles[i].getAbsolutePath());
            }
            if (childFiles[i].isDirectory())
            {
                treeName(childFiles[i].getPath());
            }
        }
    }
    
    public static void main(String[] args)
    {
        HTMLSpirit.treeName("C:\\Users\\陈亮\\Desktop\\JVM实用参数系列");
        System.out.println(list.toString());
        System.out.println(list.size());
        
        for (String path : list)
        {
            HTMLSpirit.WriteStringToFile(path,HTMLSpirit.delHTMLTag(HTMLSpirit.readFileByLines(path)));            
        }
        

        /*        File dir = new File("C:\\Users\\陈亮\\Desktop\\Download\\ifeve.com");
                File[] lrcFiles = dir.listFiles(new FilenameFilter()
                {
                    public boolean accept(File dir, String name)
                    {
                        if (name.endsWith(".htm"))
                        {
                            return true;
                        }
                        return false;
                    }
                });
                
                //System.out.println("lrc file number : " + lrcFiles.length);
                for (int i = 0; i < lrcFiles.length; i++)
                {
                    System.out.println("htm file path : " + lrcFiles[i].getAbsolutePath());
                }*/
    }
}
