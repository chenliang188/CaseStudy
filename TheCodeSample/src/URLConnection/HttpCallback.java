/**
 * Created on 2007-4-9.
 */
package URLConnection;

import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @Company: 99Bill Corporation
 * @author pony
 * @version 1.0
 */
public interface HttpCallback {
	/**
	 * 回调函数，用于在发送http请求前设置http头的属性.
	 * @param httpConn HttpURLConnection
	 */
	void setHttpHeadProperties(final PostMethod postMethod);
}
