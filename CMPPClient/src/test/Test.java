/*
 * ��Ȩ���� (C) 2001-2009 ym ��������Ȩ����
 * �汾��V1.0
 * �޸ļ�¼��
 *		1��2009-7-30��yinmin ������ 
 */
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ym.sm.link.ClientConnection;
import com.ym.sm.link.Config;
import com.ym.sm.message.BaseMessage;
import com.ym.sm.message.Cmpp_Submit;

/**
 * @author yinmin
 */
public class Test
{
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        try
        {
            BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
            Config conf = new Config();
            conf.setIp("127.0.0.1");
            conf.setUserName("13900000001");
            conf.setPassword("abc");
            conf.setPort(5016);
            conf.setShakeHandTime(10);
            
            // conf.setIp("211.140.2.202");
            // conf.setUserName("411267");
            // conf.setPassword("411267");
            // conf.setPort(8855);
            ClientConnection con = new ClientConnection(conf);
            while (true)
            {
                String str = wt.readLine();
                if (str.equals("connect"))
                {// ��������
                    con.connect();
                }
                else if (str.equals("bind"))
                {// �󶨣���ʼ����
                    con.bind();
                    con.start();
                }
                else if (str.startsWith("submit"))
                {// ����submit
                
                    con.submit(getSubmit(str.substring(7)));
                }
                else if (str.equals("unbind"))
                {
                    con.setReconnectValid(false);
                    con.terminate();
                    break;
                }
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * ȡ��submit��Ϣ
     * 
     * @return
     */
    private static BaseMessage getSubmit(String content)
    {
        // ����submit
        Cmpp_Submit msg = new Cmpp_Submit();
        msg.setAt_time("2009-07-31 16:00:00");
        msg.setDest_terminal_id("13433333333");
        msg.setDestusr_tl(1);
        msg.setFee_termianl_Id("13433333333");
        msg.setFee_Type("01");
        msg.setFee_userType(0);
        msg.setFeeCode("000000");
        msg.setMsg_content(content.getBytes());
        msg.setMsg_fmt(15);
        msg.setMsg_id(0L);
        msg.setMsg_length(msg.getMsg_content().length);
        msg.setMsg_level(1);
        msg.setMsg_src("123123");
        msg.setRegistered_delivery(0);
        msg.setService_id("123123");
        msg.setSrc_id("10658278");
        msg.setTp_pid(0);
        msg.setTp_udhi(0);
        msg.setValid_time("2009-07-31 16:00:00");
        
        return msg;
    }
    
    /**
     * ȡ��submit��Ϣ
     * 
     * @return
     */
    private static BaseMessage getSubmit2(String content)
    {
        // ����submit
        Cmpp_Submit msg = new Cmpp_Submit();
        msg.setAt_time("2009-08-11 16:00:00");
        msg.setDest_terminal_id("15895831659");
        msg.setDestusr_tl(1);
        msg.setFee_termianl_Id("15895831659");
        msg.setFee_Type("01");
        msg.setFee_userType(0);
        msg.setFeeCode("000000");
        msg.setMsg_content(content.getBytes());
        msg.setMsg_fmt(15);
        msg.setMsg_id(0L);
        msg.setMsg_length(msg.getMsg_content().length);
        msg.setMsg_level(1);
        msg.setMsg_src("411267");
        msg.setRegistered_delivery(0);
        msg.setService_id("1");
        msg.setSrc_id("10658517");
        msg.setTp_pid(0);
        msg.setTp_udhi(0);
        msg.setValid_time("2009-08-11 16:00:00");
        
        return msg;
    }
    
}
