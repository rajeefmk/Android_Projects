package us.jaaga.samaltmanblog;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends Fragment{
	
	//private final String paulGraham = "http://paulgraham.com/articles.html";
	//private final String samAltman = "http://blog.samaltman.com";
	
	private WebView mWebView;
	private WebSettings mWebSettings;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//Getting the url to open
		Bundle args = getArguments();
		String url = args.getString("url");
		
		//Setting up the WebView
		mWebView = (WebView) getView().findViewById(R.id.webView);
		mWebView.setWebViewClient(new WebViewClient());
		mWebSettings = mWebView.getSettings();
		mWebSettings.setJavaScriptEnabled(true);
		
		
		//Extra Features
		//TODO Need to check n replace the below features
		mWebSettings.setLoadsImagesAutomatically(true);
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		
		//Cache Storage
		mWebSettings.setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
				
		//mWebSettings.setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
		mWebSettings.setAppCacheEnabled(true);
		mWebSettings.setAllowFileAccess(true);
		mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		
		if (!isNetworkAvailable()){
			mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
			
		}
		 
		//Loading the url
		mWebView.loadUrl(url);
		
		/*mWebView.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if( (keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
					mWebView.goBack();
				}
				return false;
			}
		});*/
		
		//
		
		
		
	}
	
	
	
	/*public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
			
			mWebView.goBack();
			return true;
		}
		
		else if((keyCode == KeyEvent.KEYCODE_FORWARD) && mWebView.canGoForward()){
			
			mWebView.goForward();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	} 
	*/
	private boolean isNetworkAvailable() {
		
			ConnectivityManager connMgr = (ConnectivityManager) 
					getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
			boolean isWifiConn = networkInfo.isConnected();
			networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			boolean isMobileConn = networkInfo.isConnected();
			Log.d("DEBUG_TAG", "Wifi connected: " + isWifiConn);
			Log.d("DEBUG_TAG", "Mobile connected: " + isMobileConn);
			
			boolean network;
			
			if(isWifiConn == true){
				
				network = true;
				Log.i("TAG","network is set true because of isWifiConn");
			}
			else if(isMobileConn == true){
				
				network = true;
				Log.i("TAG","network is set true because of isMobileConn");
			}
			else {
				 network = false;
				 Log.i("TAG","network is set false");
			}
			
			return network;
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.web_view,container,false);
		return view;
	}
	
	
	
	
	//TODO Replace .setWebViewClient arguments with below content
	
	/*private class MyBrowser extends WebViewClient {
	 * 
	 * @Override
	 * public boolean shouldOVerrideUrlLoading(WebView
	 *  	view, String url) {
	 * 
	 * view.loadUrl(url);
	 * return true;
	 * }
	 * }
	 */
	

}
