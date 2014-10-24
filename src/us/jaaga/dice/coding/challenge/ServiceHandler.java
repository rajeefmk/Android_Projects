package us.jaaga.dice.coding.challenge;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ServiceHandler {

	static String response = null;
	public final static int GET = 1;
	public final static int POST = 2;
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	String authData;
	
	public ServiceHandler(String Key, String Value, String authData){
		
		params.add(new BasicNameValuePair(Key,Value));
		this.authData = authData;
		
	}
	

	public ServiceHandler() {

	}
	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * */
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, params);
	}

	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * @params - http request params
	 * */
	public String makeServiceCall(String url, int method,
			List<NameValuePair> params) {
		try {
			// http client
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			
			// Checking http request method type
			if (method == POST) {
				// adding post params
				HttpPost httpPost = new HttpPost(url);
				
				if (params != null) {
					httpPost.setHeader("Application type","x-www-form-urlencoded");
					httpPost.setHeader("Authorization", authData);
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				}
				
				httpResponse = httpClient.execute(httpPost);

			} 
			else if (method == GET) {
				// appending params to url
				/*if (params != null) {
					String paramString = URLEncodedUtils
							.format(params, "utf-8");
					url += "?" + paramString;
				}*/
				
				HttpGet httpGet = new HttpGet(url);
				httpResponse = httpClient.execute(httpGet);

			}
			
			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;

	}
}
