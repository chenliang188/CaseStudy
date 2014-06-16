package URLConnection;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetInternet
{
    public static void main(String[] args) throws Exception
    {
        String currentUrl = "http://www.mmkbw.cn/vod-play-id-6198-sid-0-pid-30.html";
        URL url = new URL(currentUrl);
        
        HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
        //下面的设置对应HTTP请求中的消息报头
        httpurlconnection.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpurlconnection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
        httpurlconnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
        httpurlconnection.setRequestProperty("Cache-Control", "max-age=0");
        httpurlconnection.setRequestProperty("Connection", "keep-alive");
        httpurlconnection.setRequestProperty("User-Agent",
                " Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.11 Safari/537.36");
        httpurlconnection.setRequestProperty("Host", "www.mmkbw.cn");
        
        httpurlconnection.connect();
        
        InputStream is = httpurlconnection.getInputStream();
        
        long contentLength = httpurlconnection.getContentLength();
        
        ByteArrayOutputStream outstream = new ByteArrayOutputStream((int) contentLength);
        byte buffer[] = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > 0)
            outstream.write(buffer, 0, len);
        outstream.close();
        byte[] response = outstream.toByteArray();
        
        
        String returnMsg = new String(response,"UTF-8");
        
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(is),150000);  
//        String response = "";  
//        String readLine = null;  
//        while((readLine =br.readLine()) != null){  
//            response = response + readLine;
//        }  
//        is.close();  
//        br.close();  
        
        if (httpurlconnection != null)
        {
            httpurlconnection.disconnect();
        }
        //System.out.println(response);
        
/*        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept-Encoding", "gzip,deflate,sdch");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        headers.put("Cache-Control", "max-age=0");
        headers.put("Connection", "keep-alive");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.11 Safari/537.36");
        headers.put("Host", "www.mmkbw.cn");

        String returnMsg = null;


            returnMsg = HttpUtil.postHttpPostRequest("http://www.mmkbw.cn/vod-play-id-6198-sid-0-pid-30.html",
                    headers, null,"GBK");*/
        System.out.println(returnMsg);
        Document doc = Jsoup.parse(returnMsg);
        
        //Document doc = Jsoup.parse(is, "GBK", "http://www.mmkbw.cn/");
        
        Elements elements = doc.select("embed");
        for (Element element : elements)
        {
            System.out.println(element.attr("url"));
        }
    }
}