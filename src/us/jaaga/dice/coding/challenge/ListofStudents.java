package us.jaaga.dice.coding.challenge;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.jaaga.mooctracker.R;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListofStudents extends ListActivity {
	
	
	TextView tvIsConnected;
	private ProgressDialog pDialog;
	private String authData;

	// URL to get contacts JSON
	
	private static String url = "https://secure.dice.com/oauth/token";

	// JSON Node names
	private static final String TAG_ACCESS_TOKEN = "access_token";
	private static final String TAG_TOKEN_TYPE = "token_type";
	private static final String TAG_EXPIRES_IN = "expires_in";
	private static final String TAG_SCOPE = "scope";
	private static final String TASK = "Check for: ";
	
	private static final String TAG_AUTHDATA = "authData";
	
	
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_PROJECT = "Project";
	private static final String TAG_COURSE = "Course";

	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> studentList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent mIntent = getIntent();
		authData = mIntent.getStringExtra(TAG_AUTHDATA);
		
		
		// Calling async task to get json
				new GetContacts().execute();
		
		
		studentList = new ArrayList<HashMap<String, String>>();

		final ListView lv = getListView();
		
		
		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name))
						.getText().toString();
				String Project = ((TextView) view.findViewById(R.id.project))
						.getText().toString();
				String course = ((TextView) view.findViewById(R.id.course))
						.getText().toString();
				

				// Starting single student activity
				Intent in = new Intent(getApplicationContext(),
						SingleStudentActivity.class);
				in.putExtra(TAG_NAME, name);
				Log.i(TASK, "TAG_NAME passed to intent");
				
				in.putExtra(TAG_PROJECT, Project);
				Log.i(TASK, "TAG_PROJECT passed to intent");
				
				in.putExtra(TAG_COURSE, course);
				Log.i(TASK, "TAG_COURSE passed to intent");
				
				startActivity(in);

			}
		});
		
		
		
	}

	//Check network Connection
	public boolean tvIsConnected(){
	
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(ListofStudents.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
				
			return true;
		else 
			return false;
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(ListofStudents.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}
																																																																																																																																																																																							
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler("grant_type","client_credentials",authData);

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST);

			Log.d("Response: ", "> " + jsonStr);

			
			if(jsonStr !=null){
				
				try{
					
					//Getting JSON Object
					JSONObject mJSONObject = new JSONObject(jsonStr);
					
					String token = mJSONObject.getString(TAG_ACCESS_TOKEN);
					String token_type = mJSONObject.getString(TAG_TOKEN_TYPE);
					String expires = mJSONObject.getString(TAG_EXPIRES_IN);
					String scope = mJSONObject.getString(TAG_SCOPE);
					
					// tmp hashmap for single student
					HashMap<String, String> data = new HashMap<String, String>();
					data.put(TAG_NAME,token );
					data.put(TAG_PROJECT,token_type);
					data.put(TAG_COURSE, expires);
					
					studentList.add(data);
					
				}catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			/*if (jsonStr != null) {
				try {
					
					// Getting JSON Array node
					
					JSONArray jsonarray = new JSONArray(jsonStr);

					// looping through All students
					for (int i = 0; i < jsonarray.length(); i++) {
						
						JSONObject c = jsonarray.getJSONObject(i);
						
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String Project = c.getString(TAG_PROJECT);
						String Course = c.getString(TAG_COURSE);

						// tmp hashmap for single student
						HashMap<String, String> student = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						student.put(TAG_ID, id);
						student.put(TAG_NAME, name);
						student.put(TAG_PROJECT, Project);
						student.put(TAG_COURSE, Course);

						// adding student to student list
						studentList.add(student);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}*/

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			
			
			
			ListAdapter adapter = new SimpleAdapter(
					ListofStudents.this, studentList,
					R.layout.list_item, new String[] {TAG_NAME, TAG_PROJECT, TAG_COURSE}, new int[] {R.id.name, R.id.project, R.id.course});
			
			setListAdapter(adapter);
			
			
		}

	}

}