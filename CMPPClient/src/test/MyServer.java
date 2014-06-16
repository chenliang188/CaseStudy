package test;

import java.io.*;
import java.net.*;

public class MyServer
{
    
    public static void main(String[] args)
        throws IOException
    {
        
        ServerSocket server = new ServerSocket(7890);
        Socket client = server.accept();
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        while (true)
        {
            int b;
            boolean ok = false;
            while ((b = in.read()) != -1)
            {
                System.out.println(b);
                ok = true;
                out.write("asdfweroquweoruqwieoruoqweuroiqwuerqwrwqerqwer".getBytes());
                out.flush();
            }
            
        }
        
        //		BufferedReader in = new BufferedReader(new InputStreamReader(client
        //				.getInputStream()));
        //		PrintWriter out = new PrintWriter(client.getOutputStream());
        //		while (true) {
        //			String str = in.readLine();
        //			System.out.println(str);
        //			out.println("has receive...." + str);
        //			out.flush();
        //			if (str.equals("end"))
        //				break;
        //		}
        //		client.close();
    }
}