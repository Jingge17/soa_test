package liul.cn.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiHttpService {
	@Autowired
	private RequestConfig requestConfig;
	@Autowired
	private CloseableHttpClient httpClient;

	/**
	 * GET请求地址，响应200，返回响应的内容，响应为404、500返回null
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String doGet(String url) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} finally {
			if (null != response) {
				response.close();
			}
		}
		return null;

	}

	/**
	 * 带有参数的GET请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String doGet(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder builder = new URIBuilder(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		return doGet(url);
	}

	/**
	 * 带有参数的POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String doPost(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost();
		httpPost.setConfig(requestConfig);
		if (null != params) {
			List<NameValuePair> parameters = new ArrayList<>(0);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
			httpPost.setEntity(formEntity);
		}
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} finally {
			if (null != response) {
				response.close();
			}
		}
		return null;
	}
	/**
	 * 带有json参数的POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ParseException    
	 * @throws IOException
	 */
	 public String doPostJson(String url,String json) throws ParseException, IOException {
     HttpPost httpPost=new HttpPost(url);
     httpPost.setConfig(requestConfig);
     if (null!=json) {
		StringEntity stringEntity=new StringEntity(json,ContentType.APPLICATION_JSON);
		httpPost.setEntity(stringEntity);
	}
     CloseableHttpResponse response=null;
     try {
		response=httpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode()==200) {
			return EntityUtils.toString(response.getEntity(),"utf-8");
		}
	} finally {
		if (null != response) {
			response.close();
		}
	}
	return null;
	 }

	/**
     * 没有参数的POST请求
     * 
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(String url) throws ClientProtocolException, IOException {
        return this.doPost(url, null);
    }
}
