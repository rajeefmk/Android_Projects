package us.jaaga.dicecodingcontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LandingPage extends Activity{


/*	TextView mSignUp;*/
	EditText mUserName,mPassword;
	Button btnSignIn,btnGplus,mSignUp;
	String b64Data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		
		mUserName = (EditText) findViewById(R.id.etUserName);
		mPassword = (EditText) findViewById(R.id.etPass);
		btnSignIn = (Button) findViewById(R.id.btnSignIn);
		
		
		
		btnSignIn.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				
				String devUserName = mUserName.getText().toString();
				String devPassword = mPassword.getText().toString();
				b64Data = getB64Auth(devUserName,devPassword);
				
				Intent mIntent = new Intent(getApplicationContext(),MainActivity.class);
				mIntent.putExtra("authData",b64Data);
				startActivity(mIntent);
			}
		});
	}

	private String getB64Auth (String mUserName2, String mPassword2) {
		   String source=mUserName2+":"+mPassword2;
		   String ret="Basic "+Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
		   return ret;
		 }
}
