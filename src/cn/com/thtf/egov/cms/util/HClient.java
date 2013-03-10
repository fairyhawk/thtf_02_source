/**
 * ClassName  HttpClient
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-10-27
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Http访问Util
 * 
 * @author Shiy
 */

public class HClient {

    private static final int PARAM_MAXTOTALCONN = 500;

    private static final int PARAM_MAXPERROUTECONN = 500;

    private static final int PARAM_MGRTIMEOUT = 10000;

    private static final int PARAM_CONNTIMEOUT = 10000;

    private static final int PARAM_SOCONNTIMEOUT = 10000;

    private ClientConnectionManager cm;

    private HttpParams params;

    private static HClient me;

    private HClient() {
        params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, PARAM_MAXTOTALCONN);
        ConnPerRoute connPerRoute = new ConnPerRouteBean(PARAM_MAXPERROUTECONN);
        ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
        ConnManagerParams.setTimeout(params, PARAM_MGRTIMEOUT);

        HttpConnectionParams.setConnectionTimeout(params, PARAM_CONNTIMEOUT);
        HttpConnectionParams.setSoTimeout(params, PARAM_SOCONNTIMEOUT);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(),
                80));

        cm = new ThreadSafeClientConnManager(params, schemeRegistry);
    }

    /**
     * 取得HClient instance 单态
     * 
     * @return
     */
    public static HClient getInstance() {
        if (me == null) {
            me = new HClient();
        }
        return me;
    }

    /**
     * 想指定uri发送参数为map的http请求，返回String结果
     * 
     * @param uri
     * @param map
     *            key为参数名，value为参数值
     * @return http请求的返回结果
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String send(String uri, Map<String, String> map)
            throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient(cm, params);

        HttpPost httppost = new HttpPost(uri);

        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>(set.size());
        while (iterator.hasNext()) {
            String key = iterator.next();
            qparams.add(new BasicNameValuePair(key, map.get(key)));
        }
        httppost.setEntity(new UrlEncodedFormEntity(qparams, "UTF-8"));

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = client.execute(httppost, responseHandler);
        return responseBody;

    }
}
