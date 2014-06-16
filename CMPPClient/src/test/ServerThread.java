package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread
{
    private int port;
    
    public ServerThread(int port)
    {
        this.port = port;
    }
    
    public void run()
    {
        // TODO Auto-generated method stub
        //开启一个服务端socket，按指定端口侦听
        try
        {
            System.out.println("start listener...");
            ServerSocket serverSocket = new ServerSocket(port);
            Socket client = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());
            while (true)
            {
                String str = reader.readLine();
                System.out.println();
                out.write("收到消息：" + str);
                out.flush();
                if (null != str && str.equals("end"))
                {
                    
                    break;
                }
                
            }
            serverSocket.close();
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    
    public static void main(String args[])
    {
        //		new ServerThread(7890).start();
        
        ServerSocket server;
        try
        {
            server = new ServerSocket(7890);
            
            Socket client = server.accept();
            InputStream input = client.getInputStream();
            int b = 0;
            while ((b = input.read()) != -1)
            {
                System.out.println(b);
            }
            OutputStream output = client.getOutputStream();
            output.write("has asdfaserqwerwqerqwer receive....".getBytes());
            output.flush();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());
            while (true)
            {
                String str = in.readLine();
                System.out.println(str);
                
                if (str.equals("end"))
                    break;
            }
            client.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
