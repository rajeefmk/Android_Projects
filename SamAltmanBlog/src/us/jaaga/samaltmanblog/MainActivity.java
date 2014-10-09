package us.jaaga.samaltmanblog;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity {
	
	WebView mWebView;
	WebSettings mWebSettings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		
	}
	
	/* - Back key History
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
			
			mWebView.goBack();
			return true;
		}
		
		else if((keyCode == KeyEvent.KEYCODE_FORWARD) && mWebView.canGoForward()){
			
			mWebView.goForward();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	} */

}
