import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitRepMessage;
import com.huawei.insa2.util.Args;
import com.huawei.smproxy.SMProxy;

public class Send
{
    private static SMProxy cmpp = null;
    
    public static void main(String[] args)
    {
        //System.setProperty("file.encoding","UTF_8");
        System.out.println(System.getProperty("file.encoding"));
        //Config.setPropFileName("temp.properties");
        byte[] b = null;
        try
        {
            //b = Config.getProperty("Content").getBytes("UTF-16BE");
            b = "����������л����񹲺͹�Ŷ����������Ǵ�ͻ��ɵ�������������ɶ�ʺ�������ɳ���Ͼͻ���밡�����ʶ�ͺ��˿����˿�".getBytes("UTF-16BE");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Send send = new Send();
        
        try
        {
            init();
            send.sendLongSM("106575550211","15195858360",b,8);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void sendLong(String b, int encode)
    {
        byte[] bytes = null;
        try
        {
            bytes = b.getBytes("UTF-16BE");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("Send long Message of length " + bytes.length);
        int offset = 0;
        int num = bytes.length / (140 - 6) + 1;      
        //byte[] tb = new byte[140];
        
        byte[] tp_udhiHead=new byte[6];
        tp_udhiHead[0]=0x05;
        tp_udhiHead[1]=0x00;
        tp_udhiHead[2]=0x03;
        tp_udhiHead[3]=0x0A;
        tp_udhiHead[4]=(byte)num;
        tp_udhiHead[5]=0x01;//Ĭ��Ϊ��һ��
        
        for (int i = 0; i < num; i++)
        {
            tp_udhiHead[5]=(byte)(i+1);
            
            System.out.println("Send Long txtSMS No." + i + " of " + num);
            
            int len = getDevidedLen(bytes, offset, 134, encode);
            
            byte[] tb = new byte[len+6];
            
            System.arraycopy(tp_udhiHead, 0, tb, 0, 6);
            System.arraycopy(bytes, offset, tb, 6, len);
            System.out.println(tb.length);
            
            System.out.println(bytesToHexStr(tb));
            
            offset += len;
        }
    }
    
    public int sendLongSM(String from, String toSend, byte[] bytes, int encode)throws IOException
    {
        byte[] tp_udhiHead=new byte[6];
        tp_udhiHead[0]=0x05;
        tp_udhiHead[1]=0x00;
        tp_udhiHead[2]=0x03;
        tp_udhiHead[3]=0x0A;
       
        String to = toSend;
        if (to.startsWith("+"))
        {
            to = to.substring(1);
        }
        
        System.out.println("Send long Message of length " + bytes.length);
        int offset = 0;
        int num = bytes.length / (140 - 6) + 1;
        
        tp_udhiHead[4]=(byte)num;
        tp_udhiHead[5]=0x01;//Ĭ��Ϊ��һ��
     
        boolean firstOk = false;
        int ret = -1;
        for (int i = 0; i < num; i++)
        {
            tp_udhiHead[5]=(byte)(i+1);
            
            System.out.println("Send Long txtSMS No." + i + " of " + num);
            
            int len = getDevidedLen(bytes, offset, 134, encode);
            
            byte[] tb = new byte[len+6];
            
            System.arraycopy(tp_udhiHead, 0, tb, 0, 6);
            System.arraycopy(bytes, offset, tb, 6, len);
            System.out.println(tb.length);
            
            System.out.println(bytesToHexStr(tb));
            
            offset += len;
            
            if (null == cmpp)
            {
                System.out.println("No binded smsc!");
                return -1;
            }
            
            CMPPSubmitMessage req = new CMPPSubmitMessage(1, 1, 0, 2, "1",
                    2, "1", 0, 1, (byte)8,
                    "419001", "01", "0", null, null,
                    from, new String[] {to}, tb, "");
          
            CMPPSubmitRepMessage response = null;
            
            try
            {
                response = (CMPPSubmitRepMessage)cmpp.send(req);
            }
            catch (Exception e)
            {
                System.out.println("Exception when sending smsc.");
                ret = -2;
            }

            if (null == response)
            {
                System.out.println("Response from smsc is null");
                ret = -3;
            }
            else
            {
                System.out.println("Get response: " + response);
                ret = response.getResult();
            }

            if ((0 == i) && (0 == ret))
            {
                firstOk = true;
            }

            if (0 != ret)
            {
                System.out.println("sendSM failed on No." + i + " of " + num);
                
                if (firstOk)
                {
                    ret = -1;
                    
                    if (ret != 0)
                    {
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
            
        }
        return ret;
    }
    
    private static void init()
    {
        Args cmppArgs = new Args();
        cmppArgs.set("host", "211.139.144.201");
        cmppArgs.set("port", 7890);
        //cmppArgs.set("host", "192.168.0.191");
        //cmppArgs.set("port", 7890);
        cmppArgs.set("heartbeat-interval", "30");
        cmppArgs.set("reconnect-interval", "30");
        cmppArgs.set("heartbeat-noresponseout", 30 * 30);
        cmppArgs.set("shared-secret", "lNpBfB");
        cmppArgs.set("source-addr", "K50211");
        cmppArgs.set("transaction-timeout", "30");
        cmppArgs.set("version", 0);
        cmppArgs.set("debug", false);
        ///String localHost = "127.0.0.1";
        //cmppArgs.set("local-host", localHost);
        //cmppArgs.set("local-port", 0);
        
        cmppArgs.set("read-timeout", "30");
        
        System.out.println("Init CMPP2.0, parameters: " + cmppArgs.toString());
        try
        {
            cmpp = new SMProxy(cmppArgs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Ϊ��String��ֵ���Ҫ,��������ĵĻ�,���뱣֤��ֺ������ġ�
     *
     * @param b �ֽ�����
     * @param offset ��ʾ��b�еĵ�offset���ֽڿ�ʼ����
     * @param fixedLen �̶�����
     * @param type ����
     * @return ��ò��ض����ĵĳ���
     */
    public static int getDevidedLen(byte[] b, int offset, int fixedLen, int type)
    {
        
        if (null == b)
        {
            return 0;
        }
        if (b.length <= (fixedLen + offset))
        {
            return b.length - offset;
        }
        
        int len = 0;
        ;
        switch (type)
        {
            case (byte)0x0F:
                len = getGBKDevidedLen(b, offset, fixedLen);
                break;
            case (byte)0x08:
            case (byte)0x00:
            default:
                len = fixedLen;
        }
        return len;
    }

    /**
     * Ϊ��String��ֵ���Ҫ,��������ĵĻ�,���뱣֤��ֺ�������(GBK��ʽ���)
     *
     * @param b �ֽ�����
     * @param offset ��ʾ��b�еĵ�offset���ֽڿ�ʼ����
     * @param fixedLen �̶�����
     * @return ��ò��ض����ĵĳ���
     */
    public static int getGBKDevidedLen(byte[] b, int offset, int fixedLen)
    {
        int len = fixedLen;
        int index = 0;
        int min = Math.min(offset + fixedLen - 1, b.length - 1);
        for (int i = min; i >= offset; i--)
        {
            if (b[i] > 0)
            {
                break;
            }
            index++;
        }
        if ((index % (3 - 1)) != 0)
        {
            len--;
        }
        return len;
    }
    
    
    /**
     * ���ܣ�
     *     ���ֽ�ת��Ϊ16�����루�ڴ�ֻ��Ϊ�˵���������˺���û��ʵ�����壩
     * @param b  
     * @return ת�����16������
     * @Author: eric(eric_cheung709@hotmail.com)
        * created in 2007/04/28 16:33:06
     */
    private static String bytesToHexStr(byte[] b) {
           if (b == null)
               return "";
           StringBuffer strBuffer = new StringBuffer(b.length * 3);
           for (int i = 0; i < b.length; i++ ) {
               strBuffer.append(Integer.toHexString(b[i] & 0xff));
               strBuffer.append(" ");
           }
           return strBuffer.toString();
       } 
}