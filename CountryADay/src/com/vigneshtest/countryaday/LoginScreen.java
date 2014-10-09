package com.vigneshtest.countryaday;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity{
	
	Button loginButton;
	EditText userName;
	EditText mPassword;
	String finalUname;
	String finalPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		
		SharedPreferences mSP = getSharedPreferences("login", MODE_PRIVATE);
		String username = mSP.getString("Uname", null);
		String password = mSP.getString("Password",null);
		
		if(username!= null & password!= null){
			
			runIntent();
			finish();
		}
		
		
		loginButton = (Button) findViewById(R.id.btn_login);
		userName = (EditText) findViewById(R.id.fld_username);
		mPassword = (EditText) findViewById(R.id.fld_pwd);
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finalUname = userName.getText().toString();
				finalPassword = mPassword.getText().toString();
				
				if (finalUname != "admin")  {
					
					Toast.makeText(getApplicationContext(), "Username/Password did not match", Toast.LENGTH_LONG).show();
					
				}
				else{
					
					SharedPreferences mSP = getSharedPreferences("login", MODE_PRIVATE);
					Editor mEditor = mSP.edit();
					mEditor.putString("Uname", finalUname);
					mEditor.putString("Password", finalPassword);
					mEditor.commit();
					
					Intent mIntent = new Intent(getApplicationContext(), CountryList.class);
					startActivity(mIntent);
				}
				
				
				
			}

			
		});
		
	}
	
	public void runIntent() {
		
		Intent mIntent = new Intent(this, CountryList.class);
		startActivity(mIntent);
		
	}
}
