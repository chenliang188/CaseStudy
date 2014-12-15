/**
 * Created on 2007-4-9.
 */
package URLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Company: 99Bill Corporation
 * @author gli
 * @version 1.0
 */
public final class HttpUtil {
	/**
	 * 日志.
	 */
	private static Log logger = LogFactory.getLog(HttpUtil.class);

	//private static final String ENCODING = "UTF-8";

	private static final String METHOD_POST = "POST";

	/**
	 * 以get的方式提交请求到指定的url地址.
	 * 
	 * @param url
	 *            String
	 * @param parameters
	 *            Map
	 * @return String
	 */
	public static String postHttpGetRequest(final String url,
			final Map parameters) {
		return null;
	}

	/**
	 * 以post的方式提交请求到指定的url地址.
	 * ICBC
	 * @param url
	 *            String
	 * @param parameters
	 *            Map
	 * @return String
	 */
	public static String postHttpPostRequest(final String url,
			final Map parameters, final HttpCallback callback, final String encoding) {
		HttpClient client = new HttpClient();
        client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 30000);
        client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,30000);
        
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new RttpHttpMethodRetryHandler());
		
		Iterator it = parameters.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) parameters.get(key);
			NameValuePair pair = new NameValuePair(key, value);
			method.addParameter(pair);
		}
		if (null != callback) {
			callback.setHttpHeadProperties(method);
		}
		
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.info("Method failed: " + method.getStatusLine());
			}
			byte[] response = method.getResponseBody();

			logger.info("the method.getResponseBody is:"
					+ ((null == response) ? "Empty" : response));
			if (null == response) {
				return null;
			}

			String result = new String(response,encoding);
			return result;
		} catch (HttpException e) {

			logger.error(e);
		} catch (IOException e) {

			logger.error(e);
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * 不重试.主要是效率问题,靠后期补单做.
	 * 
	 * @Company: 99Bill Corporation
	 * @author gli
	 * @version 1.0
	 */
	static class RttpHttpMethodRetryHandler implements HttpMethodRetryHandler {

		public boolean retryMethod(HttpMethod arg0, IOException arg1, int arg2) {
			return false;
		}
	}

	/**
	 * 以post的方式提交请求到指定的url地址.
	 * CMB
	 * @param url
	 *            String
	 * @param parameters
	 *            Map
	 * @return String
	 */
	public static String postHttpPostRequest(final String url,
			final String data, final Map httpHeadProperties, final String encoding) {
		URL httpurl = null;
		HttpURLConnection httpConn = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			httpurl = new URL(url);
			httpConn = (HttpURLConnection) httpurl.openConnection();
			Iterator it = httpHeadProperties.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = (String) httpHeadProperties.get(key);
				httpConn.addRequestProperty(key, value);
			}
			httpConn.setRequestMethod(METHOD_POST);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			outputStream = httpConn.getOutputStream();
			outputStream.write(data.getBytes());
			outputStream.flush();
			inputStream = (InputStream) httpConn.getContent();
			String resultStr = inputStream2String(inputStream,encoding);
			resultStr = resultStr.substring(resultStr.indexOf("<"));
			return resultStr;
		} catch (Exception e) {
			logger.error(e);
		} finally {
			boolean errorFlag = false;
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error(e);
					errorFlag = true;
				}
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(e);
					errorFlag = true;

				}
			if (httpConn != null)
				httpConn.disconnect();
			if (errorFlag == true)
				return null;
		}

		return null;
	}
	
	 /**
     * 以post的方式提交请求到指定的url地址.
     * 交行 
     * @param url String
     * @param parameters Map
     * @return String
     */
    public static String postHttpPostRequest(
            final String url, 
            final String data, 
            final String encoding,final HttpCallback callback) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(
                HttpMethodParams.RETRY_HANDLER, 
                new RttpHttpMethodRetryHandler());
        if (null != callback) {
            callback.setHttpHeadProperties(method);
        }
        RequestEntity entity = null;
        try {
            entity = new StringRequestEntity(data, "text/html", encoding);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        method.setRequestEntity(entity);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,30000);  
        try {
            client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
            client.executeMethod(method);
            byte[] response = method.getResponseBody();
            if(null==response){
                return null;
            }
            return new String(response,encoding);
            
        } catch (HttpException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
        }  finally {
            method.releaseConnection();
        }
        return null;
    }

	private static String inputStream2String(InputStream is,String encoding) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is,encoding));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try {
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} finally {
			in.close();
		}
		return buffer.toString();
	}
	

}
