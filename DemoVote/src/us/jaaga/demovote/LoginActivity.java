package us.jaaga.demovote;

import java.io.IOException;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class LoginActivity extends Activity {
	
	static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
	static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1001;
	//private static final String SCOPE = "audience:server:client_id:325221067434-uh0hp886c498dnda8b9u2dep54gdmr1c.apps.googleusercontent.com";
	private static final String DEBUG_TAG = "NetworkStatusExample";
	private static final String TAG = "Task";
	//private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
	private static final String SCOPE = "audience:server:client_id:1093155095616-b90oicomnsltkfgc72na1l80nfv890td.apps.googleusercontent.com";
	
	String mEmail;
	String mToken;
	String mToken_encrypted;

	ProgressBar pb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login_activity);
		
		
		Button mButton = (Button) findViewById(R.id.button);
		
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getUsername();
				Log.i(TAG,"getUsername is called");
			}
		});
		
		if(mToken != null){
			
			Toast.makeText(LoginActivity.this, "token received", Toast.LENGTH_LONG).show();
		   
			
		}
		
		else if(mToken == null){
			
			Toast.makeText(LoginActivity.this, "token NOT received", Toast.LENGTH_LONG).show();
		}
		
		/*if(mToken_encrypted != null){
			
			
		}*/
		
	}
	
	private void getUsername(){
		
		if(mEmail == null){
			Log.i(TAG,"pickUserAccount is called form getUsername");
			pickUserAccount();
		}else {
			
			if(isDeviceOnline()){
				
				new GetUsernameTask(LoginActivity.this,mEmail,SCOPE).execute();
				Log.i(TAG,"GetUsernameTask Async task is passed arguments from getUsername");
			}
		}
	}
	
	private boolean isDeviceOnline() {
		
		ConnectivityManager connMgr = (ConnectivityManager) 
		        getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
		boolean isWifiConn = networkInfo.isConnected();
		networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isMobileConn = networkInfo.isConnected();
		Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
		Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);
		
		boolean network;
		
		if(isWifiConn == true){
			
			network = true;
			Log.i(TAG,"network is set true because of isWifiConn");
		}
		else if(isMobileConn == true){
			
			network = true;
			Log.i(TAG,"network is set true because of isMobileConn");
		}
		else {
			 network = false;
			 Log.i(TAG,"network is set false");
		}
		
		return network;
	}

	private void pickUserAccount() {
	    String[] accountTypes = new String[]{"com.google"};
	    Intent intent = AccountPicker.newChooseAccountIntent(null, null,
	            accountTypes, false, null, null, null, null);
	    startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQUEST_CODE_PICK_ACCOUNT){
			
			if(resultCode == RESULT_OK){
				
				mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
				
				getUsername();
			}
			
			else if(resultCode == RESULT_CANCELED){
				
				Toast.makeText(this, "Please pick an account", Toast.LENGTH_LONG).show();
			}
			
			
		} else if((requestCode == REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR) && resultCode == RESULT_OK) {
	      
			// Receiving a result that follows a GoogleAuthException, try auth again
	        getUsername();
		
		 }
		
	}

	
	public class GetUsernameTask extends AsyncTask<Void, Void, Void>{
		
		Activity mActivity;
		String mScope;
		String mEmail;
		
		int count = 0;
		
		GetUsernameTask(Activity activity, String Email, String Scope){
			
			Log.i(TAG,"Local variables are set from received arguments");
			
			this.mActivity = activity;
			this.mScope = Scope;
			this.mEmail = Email;
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			
		
			
				try{
					
					Log.i(TAG,"fetchToken is called");
					String token = fetchToken();
					
					if(token != null){
						
						mToken = token;
						Log.i(TAG,"Token is stored in Global Variable");
						
						mToken_encrypted = getB64Auth(mToken);
						Log.i(TAG,"Encrypted Token is stored in Global Variable");
						
						
					}
				
				
					//Stuff to do with the token comes here - (Consider sending it to the backend;
				}catch(IOException e){
				
				}
			
			
			return null;
		}
		
		
		private String fetchToken() throws IOException {
			
			 try {
		          return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
		      } catch (GooglePlayServicesAvailabilityException playEx) {
		          // GooglePlayServices.apk is either old, disabled, or not present.
		          playEx.getConnectionStatusCode();
		      } catch (UserRecoverableAuthException userRecoverableException) {
		          // Unable to authenticate, but the user can fix this.
		          // Forward the user to the appropriate activity.
		          mActivity.startActivityForResult(userRecoverableException.getIntent(), REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
		      } catch (GoogleAuthException fatalException) {
		          //onError("Unrecoverable error " + fatalException.getMessage(), fatalException);
		    	  fatalException.printStackTrace();
					Log.i(TAG,"fataException found");
		      } catch(RuntimeException e){
		    	  
		    	  e.printStackTrace();
		      } catch(IOException e){
		    	  
		    	  e.printStackTrace();
		      }
			
		      return null;
			
			/*try{
				
				Log.i(TAG,"attempts to getToken");
				
				return GoogleAuthUtil.getTokenWithNotification(mActivity, mEmail, mScope, null);
			} catch (UserRecoverableAuthException userRecoverableException){
				
				userRecoverableException.printStackTrace();
				Log.i(TAG,"recoverable Exception Found");
				//((MainActivity) mActivity).handleException(userRecoverableException);
				
			} catch (GoogleAuthException fatalException){
				
				fatalException.printStackTrace();
				Log.i(TAG,"fataException found");
				
			}catch (RuntimeException e){
		    	  
		    	  Log.i(TAG, "RuntimeException caught");
		    	  e.printStackTrace();
		    	  
		    }catch (IOException e){
		    	
		    	e.printStackTrace();
		    	
		    }
			
			return null; */
		}

    }

	public void handleException(final Exception e) {
	    // Because this call comes from the AsyncTask, we must ensure that the following
	    // code instead executes on the UI thread.
	    
		runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	            if (e instanceof GooglePlayServicesAvailabilityException) {
	                // The Google Play services APK is old, disabled, or not present.
	                // Show a dialog created by Google Play services that allows
	                // the user to update the APK
	                int statusCode = ((GooglePlayServicesAvailabilityException)e)
	                        .getConnectionStatusCode();
	                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(statusCode,
	                        LoginActivity.this,
	                        REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
	                dialog.show();
	            } else if (e instanceof UserRecoverableAuthException) {
	                // Unable to authenticate, such as when the user has not yet granted
	                // the app access to the account, but the user can fix this.
	                // Forward the user to an activity in Google Play services.
	                Intent intent = ((UserRecoverableAuthException)e).getIntent();
	                startActivityForResult(intent,
	                        REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
	            }
	        }
	    });
	}
	
	private String getB64Auth (String token) {
		
		 Log.i(TAG,"Base64 encryption is called");
		  String ret="Basic "+Base64.encodeToString(token.getBytes(), Base64.URL_SAFE|Base64.NO_WRAP);
		  return ret;
		}
	

}
