package com.vigneshtest.countryaday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		Thread background = new Thread(){
			
			public void run() {
				
				try{
					
					sleep(5*1000);
					Intent mIntent = new Intent(getBaseContext(), LoginScreen.class );
					startActivity(mIntent);
					
				}catch(Exception e){
				
				}
			}
		};
		background.start();
	}
}
		
		
	
		
		
		
	


