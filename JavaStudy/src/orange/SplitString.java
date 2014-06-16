package orange;

import java.io.UnsupportedEncodingException;

class SplitString
{
    String SplitStr;
    
    int SplitByte;
    
    public SplitString(String str, int bytes)
    {
        SplitStr = str;
        SplitByte = bytes;
    }
    
    public void SplitIt()
    {
        int loopCount;
        loopCount = (SplitStr.length() % SplitByte == 0) ? (SplitStr.length() / SplitByte) : (SplitStr.length() / SplitByte + 1);
        System.out.println("Will Split into " + loopCount);
        for (int i = 1; i <= loopCount; i++)
        {
            if (i == loopCount)
            {
                System.out.println(SplitStr.substring((i - 1) * SplitByte, SplitStr.length()));
            }
            else
            {
                System.out.println(SplitStr.substring((i - 1) * SplitByte, (i * SplitByte)));
            }
        }
    }
    
    public String subString(String str, int subBytes)
    {
        int bytes = 0; // 用来存储字符串的总字节数
        for (int i = 0; i < str.length(); i++)
        {
            if (bytes == subBytes)
            {
                return str.substring(0, i);
            }
            char c = str.charAt(i);
            if (c < 256)
            {
                bytes += 1; // 英文字符的字节数看作1
            }
            else
            {
                bytes += 2; // 中文字符的字节数看作2
                if (bytes - subBytes == 1)
                {
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }
    
    public String split (String str, int num) throws UnsupportedEncodingException
    {
        byte array[] = str.getBytes();
        
        int cnNum = 0;
        
        for (int i = 0; i< num ; i++)
        {
            if (array[i] <0)
            {
                ++cnNum;
            }
        }
        
        int result = (cnNum % 2 == 0)? num : num-1;
        System.out.println(cnNum+"s       "+result);
        return new String(array,0,result,"GBK");
    }
    
    public static void main(String[] args)
    {
        SplitString ss = new SplitString("vghjghjgj", 7);
        try
        {
            System.out.println(ss.split("�ҵ�rew���3����",7));
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

