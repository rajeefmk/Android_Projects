package us.jaaga.demovote.helper;

import java.io.IOException;

import us.jaaga.demovote.LoginActivity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;

public class AsyncToken extends AsyncTask<Void, Void, String>{
	
	LoginActivity mActivity;
	//String mScope;
	String mEmail;
	String mToken;
	private static final String SCOPE = "audience:server:client_id:1093155095616-b90oicomnsltkfgc72na1l80nfv890td.apps.googleusercontent.com";
	private static final String TAG = "AsyncTaskToken";
	//url for sending token to th backend for verification
	private static final String url = "http://192.168.0.6:3000/api/android/authorize";
	static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1001;
	//static String REQUEST_CODE_403;
	static String REQUEST_CODE_401;
	static String sendbackToken;
	int count = 0;
	
	
	public String ResponseData;
	
	public AsyncToken(LoginActivity activity, String Email){
		
		Log.i(TAG,"Local variables are set from received arguments");
		
		this.mActivity = activity;
		//this.mScope = Scope;
		this.mEmail = Email;
	}
	
	@Override
	protected String doInBackground(Void... arg0) {
		
			try{
				
				Log.i(TAG,"fetchToken is called");
				String token = fetchToken();
				
				if(token != null){
					
					//Stuff to do with the token comes here - (Consider sending it to the backend;
					mToken = token;
					Log.i(TAG,"Token is stored in Global Variable");
					
					ServiceHandler sh = new ServiceHandler();
					
					String response = sh.tokenAuthenticate(mToken, url, ServiceHandler.GET);
					
					if(response == REQUEST_CODE_401 ){
							
						invalidate();
						sendbackToken = "";
						// onActivityResult need to start !!
						
					}else{
						// TODO Process response - JSONData and check Expiry !! 
						//TODO if not expired  sendback token, if expired - fetchtoken
						sendbackToken = mToken;
						
					}
				}//TODO Define else if condition of token!=null
			
				
			}catch(IOException e){
				e.printStackTrace();
			}
		
		
		return sendbackToken;
	}
	
	@Override
	protected void onPostExecute(String result) {
		//super.onPostExecute(result);
		
		mActivity.setToken(result);
		
	}
	private String fetchToken() throws IOException {
		
		 try {
	          return GoogleAuthUtil.getToken(mActivity, mEmail, SCOPE);
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
	}
	
	private void invalidate() throws IOException{
		
		try{
			
			GoogleAuthUtil.invalidateToken(mActivity, mToken);
		} catch(RuntimeException e){
			
			e.printStackTrace();
		}
	}
	
	/*public void handleException(final Exception e) {
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
	                        mActivity,
	                        REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
	                dialog.show();
	            } else if (e instanceof UserRecoverableAuthException) {
	                // Unable to authenticate, such as when the user has not yet granted
	                // the app access to the account, but the user can fix this.
	                // Forward the user to an activity in Google Play services.
	                Intent intent = ((UserRecoverableAuthException)e).getIntent();
	                mActivity.startActivityForResult(intent,
	                        REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
	            }
	        }
	    });
	}
*/
	
}
