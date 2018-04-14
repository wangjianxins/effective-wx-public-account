package com.effective.wechat.public_account.httpclient;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;

/**
 * post工具类
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**REST服务请求类型*/
    public static final String REST_META_JSON_TYPE = "application/json";
    public static final String REST_META_XML_TYPE = "application/xml";
    /**系统编码*/
    public static final String ENCODE = "utf-8";
    /**appId*/
    private String appId;
    /**appToken*/
    private String appToken;

    // 等待数据超时时间
    private static int timeout = 60000;

    /**
     * 请求url传递json数据
     * @param json
     * @param url
     * @return
     */
    public static String postToUrl (String json, String url) {

        logger.info("准备调用地址" + url + "发送数据" + json);
        String jsonResult = "";

        HttpClientParams params = new HttpClientParams();
        // 设置超时时间
        params.setSoTimeout(timeout);

        HttpClient client = new HttpClient(params, new SimpleHttpConnectionManager(true));

        PostMethod method = null;

        try {
            method = new PostMethod(url);

            // 设置发送的内容
            method.setRequestEntity(new StringRequestEntity(json, REST_META_JSON_TYPE, ENCODE));

            // 执行method
            client.executeMethod(method);

            // 获取执行结果
            byte[] rsbyte = method.getResponseBody();

            jsonResult = new String(rsbyte, ENCODE);

            logger.info("调用地址" + url + "返回的结果为" + jsonResult);
        } catch (Exception e) {
            logger.error("调用地址" + url + "出现异常", e);
        } finally {
            if (null != method) {
                method.releaseConnection();
            }
        }

        return jsonResult;
    }
    /**
     * 请求url传递json数据
     * @param json
     * @param url
     * @return
     * @throws Exception 
     */
    public static String postToUrlByJson (String json, String url,Map<String, String> headerMap) throws Exception {
    	return doPost(json, url, headerMap, REST_META_JSON_TYPE);
    }
    /**
     * 请求url传递xml数据
     * @param url
     * @return
     * @throws Exception 
     */
    public static String postToUrlByXml (String xml, String url,Map<String, String> headerMap) throws Exception {
    	return doPost(xml, url, headerMap, REST_META_XML_TYPE);
    }
    
    /**
     * 请求URL，带自定义格式的参数
     * @param param
     * @param url
     * @param headerMap
     * @param contentype
     * @return
     * @throws Exception 
     */
    public static String doPost(String param, String url,Map<String, String> headerMap,String contentype) throws Exception{

    	
    	logger.info("准备调用地址" + url + "发送数据" + param);
    	String jsonResult = "";
    	
    	HttpClientParams params = new HttpClientParams();
    	// 设置超时时间
    	params.setSoTimeout(timeout);
    	
    	HttpClient client = new HttpClient(params, new SimpleHttpConnectionManager(true));
    	
    	PostMethod method = null;
    	
    	try {
    		method = new PostMethod(url);
    		
    		
    		
    		for (Entry<String, String> entry : headerMap.entrySet()) {
    			method.addRequestHeader(entry.getKey(), entry.getValue());
    		}
    		// 设置发送的内容
    		method.setRequestEntity(new StringRequestEntity(param, contentype, ENCODE));
    		
    		// 执行method
    		client.executeMethod(method);
    		
    		// 获取执行结果
    		byte[] rsbyte = method.getResponseBody();
    		
    		jsonResult = new String(rsbyte, ENCODE);
    		
    		logger.info("调用地址" + url + "返回的结果为" + jsonResult);
    	} catch (Exception e) {
    		logger.error("调用地址" + url + "出现异常", e);
    		throw e;
    	} finally {
    		if (null != method) {
    			method.releaseConnection();
    		}
    	}
    	
    	return jsonResult;
    
    }

    /**
	 * 调用http接口
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> params,Map<String, String> headerMap) {
		String back = "";
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
		PostMethod method = new PostMethod(url);
		try {
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
			for (String key : params.keySet()) {
				method.setParameter(key, params.get(key));
			}
			for (Entry<String, String> entry : headerMap.entrySet()) {
    			method.addRequestHeader(entry.getKey(), entry.getValue());
    		}
			int status = client.executeMethod(method);
			if (status == HttpStatus.SC_OK) {
				back = method.getResponseBodyAsString();
			} else {
				logger.error("异常：" + status);
				//throw new Exception("异常：" + status);
			}
		} catch (Exception e) {
		    e.printStackTrace();
			logger.error("doPost:异常" + e.getLocalizedMessage(),e);
			//throw e;
		} finally {
			method.releaseConnection();
		}
		return back;
	}	

    /**
     * 请求url
     * @param url
     * @return
     */
    public static String getToUrl (String url) {

        logger.info("准备调用地址" + url);
        String jsonResult = "";

        HttpClientParams params = new HttpClientParams();
        // 设置超时时间
        params.setSoTimeout(timeout);

        HttpClient client = new HttpClient(params, new SimpleHttpConnectionManager(true));

        GetMethod method = null;

        try {
            method = new GetMethod(url);

            // 执行method
            client.executeMethod(method);

            // 获取执行结果
            byte[] rsbyte = method.getResponseBody();

            jsonResult = new String(rsbyte, ENCODE);

            logger.info("调用地址" + url + "返回的结果为" + jsonResult);
        } catch (Exception e) {
            logger.error("调用地址" + url + "出现异常", e);
        } finally {
            if (null != method) {
                method.releaseConnection();
            }
        }

        return jsonResult;
    }

}
