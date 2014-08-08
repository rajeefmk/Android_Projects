package us.jaaga.demovote.helper;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.jaaga.demovote.StudentListActivity;
import us.jaaga.demovote.models.ProjectListData;
import us.jaaga.demovote.models.StudentListData;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AsyncData extends AsyncTask<Void, Void, ArrayList<StudentListData>>{
	
	ProgressDialog pDialog;
	StudentListActivity mStudentList;
	ArrayList<StudentListData> studentData = new ArrayList<StudentListData>();
	ArrayList<ProjectListData> delivData = new ArrayList<ProjectListData>();
	String mToken;
	
	public AsyncData(StudentListActivity activity, String token){
		
		mStudentList = activity;
		mToken = token;
	}
	
	

	private static String url = "https://jaagademovote.herokuapp.com/api/v1/family?populate=deliverables";
	
	// JSON Node names for Main Data
	private static final String TAG_STUDENT_ID = "_id";
	private static final String TAG_STUDENT_NAME = "name";
	
	// JSON Node names for  Google Data
	private static final String TAG_GOOGLE = "google";
	private static final String TAG_PICTURE_URL = "picture";
	
	private static final String TAG_DELIVERABLES = "deliverables";
	
	//JSON Node names for Deliverables Data
	private static final String TAG_DELIVERABLES_ID = "_id";	
	private static final String TAG_DELIVERABLES_TITLE = "title";
	private static final String TAG_DELIVERED_STATUS = "delivered";
	//private static final String TAG_VOTINGOPEN = "votingopen";
	//private static final String TAG_DELIVERABLES_DESCRIPTION = "description";
	//private static final String TAG_TOTAL_VOTES = "votes";
	
	
	//Main Array
	JSONArray mJSONArrayMain;
	
		//Objects in Main Array
		JSONObject mJSONObjectMain;
		
			//Google Object for getting profile pic
			JSONObject mJSONObjectGoogle;
		
			//Deliverable Array
			JSONArray mJSONArrayDeliv;	
			
				//Objects in Deliverable Array
				JSONObject mJSONObjectDelivItem;
	
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(mStudentList);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();
	}	
			
				
	@Override
	protected ArrayList<StudentListData> doInBackground(Void... params) {
		
		ServiceHandler sh = new ServiceHandler(mToken);
		
		String jsonData = sh.makeServiceCall(url, ServiceHandler.GET);
		
		try{
			//Processing the received JSONArray
			mJSONArrayMain = new JSONArray(jsonData);
			//Getting all Objects from the Main Array
			for(int i=0; i<mJSONArrayMain.length(); i++){
				
				StudentListData student_Data = new StudentListData();
				
				mJSONObjectMain = mJSONArrayMain.getJSONObject(i);
				
				String student_id = mJSONObjectMain.getString(TAG_STUDENT_ID);
				student_Data.setId(student_id);
				
				String student_name = mJSONObjectMain.getString(TAG_STUDENT_NAME);
				student_Data.setName(student_name);
				
				//Getting Google Object from the Main Object
				mJSONObjectGoogle = mJSONObjectMain.getJSONObject(TAG_GOOGLE);
					//Getting JSON nodes from the Google Object
					String picture_url = mJSONObjectGoogle.getString(TAG_PICTURE_URL);
					student_Data.setPictureUrl(picture_url);
				
				//Getting Total Deliverable Array from Main Object
				mJSONArrayDeliv = mJSONObjectMain.getJSONArray(TAG_DELIVERABLES);
					if(mJSONArrayDeliv.length() != 0){
						
						//Getting Deliverable Objects from Deliverable Array
						for(int j=0; j<mJSONArrayDeliv.length(); j++){
							
							ProjectListData project_Data = new ProjectListData(); 
							
							mJSONObjectDelivItem = mJSONArrayDeliv.getJSONObject(j);
							
							String deliverable_id = mJSONObjectDelivItem.getString(TAG_DELIVERABLES_ID);
							project_Data.setDeliverableId(deliverable_id);
							
							String deliverable_name = mJSONObjectDelivItem.getString(TAG_DELIVERABLES_TITLE);
							project_Data.setDeliverableName(deliverable_name);
							
							Boolean delivered_status = mJSONObjectDelivItem.getBoolean(TAG_DELIVERED_STATUS);
							project_Data.setDeliverableStatus(delivered_status);
							
							delivData.add(project_Data);
						
						}
						
					}else{
						ProjectListData project_Data = new ProjectListData();
						project_Data.setNoDeliverableMessage("No Deliverables history. Please vacate ASAP");
					}
					
				student_Data.setDelivList(delivData);
				studentData.add(student_Data);
			}
		
		}catch(JSONException e){
			
			e.printStackTrace();
		}
		return studentData;
	}

	@Override
	protected void onPostExecute(ArrayList<StudentListData> result) {
		super.onPostExecute(result);
		if (pDialog.isShowing())
			pDialog.dismiss();
		
		mStudentList.dataDisplay(result);
	}
}
