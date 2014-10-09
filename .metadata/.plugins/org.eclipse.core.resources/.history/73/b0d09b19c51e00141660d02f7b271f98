package us.jaaga.demovote.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ServiceHandler {

	static String response = null;
	static String tokenResponse = null;
	static int tokenResponseCode;
	public final static int GET = 1;
	public final static int POST = 2;
	//static String REQUEST_CODE_401;
	static String REQUEST_CODE_403;

	public ServiceHandler() {
		
	}
	
	
	
	/*
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * */
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, null);
	}

	
	
	//Service call for authetication
	
	public String tokenAuthenticate(String token, String url){
		
		try{
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity mHttpEntity = null;
			HttpResponse mHttpResponse = null;
			
			HttpPost mHttpPost = new HttpPost(url);
			mHttpPost.setHeader("Authorization",token);
			
			mHttpResponse = httpClient.execute(mHttpPost);
			
			
			mHttpEntity = mHttpResponse.getEntity();
			
			tokenResponseCode = mHttpResponse.getStatusLine().getStatusCode();
			
			if(tokenResponseCode == 200 ){
				
				tokenResponse = EntityUtils.toString(mHttpEntity);
				
			}
			else if(tokenResponseCode == 403){
				
				tokenResponse = REQUEST_CODE_403;
				
			}/*else if(tokenResponseCode == 403){
				
				tokenResponse = REQUEST_CODE_403;
			}*/
			
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tokenResponse;
		
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
				/*if (params != null) {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				}*/
				HttpPost httpPost = new HttpPost(url);
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
