package us.jaaga.demovote.helper;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import us.jaaga.demovote.DeliverableList;
import us.jaaga.demovote.models.ProjectListData;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AsyncDeliv extends AsyncTask<Void, Void, ArrayList<ProjectListData>> {

	private static String url = "https://jaagademovote.herokuapp.com/api/v1/deliverables?user=";
	DeliverableList mDeliverableList;
	ProgressDialog pDialog;
	
	private static String user_id;
	ArrayList<ProjectListData> delivData = new ArrayList<ProjectListData>();
	
	//JSON Node names for Deliverables Data
			
			private static final String TAG_DELIVERABLES_ID = "_id";	
			private static final String TAG_DELIVERABLES_TITLE = "title";
			private static final String TAG_DELIVERED_STATUS = "delivered";
			private static final String TAG_VOTINGOPEN = "votingopen";
			private static final String TAG_DELIVERABLES_DESCRIPTION = "description";
			private static final String TAG_TOTAL_VOTES = "votes";
			
			String mToken;
	
	public AsyncDeliv(DeliverableList activity, String userid, String token){
		
		mDeliverableList = activity;
		user_id = userid;
		mToken = token;
	}	
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(mDeliverableList);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();
	}
	
	@Override
	protected ArrayList<ProjectListData> doInBackground(Void... params) {
		
		String final_url = url+user_id ;
		ServiceHandler sh = new ServiceHandler();
		String jsonData = sh.makeServiceCall(final_url, ServiceHandler.GET);
		
		try{
			JSONArray mainArray = new JSONArray(jsonData);
			
			if(mainArray.length() != 0 ){
				
			
				for(int i=0; i<mainArray.length(); i++){
					
					ProjectListData newData = new ProjectListData();
					
					JSONObject delivObjects = mainArray.getJSONObject(i);
					
						String deliv_id = delivObjects.getString(TAG_DELIVERABLES_ID);
						newData.setDeliverableId(deliv_id);
						
						String deliv_title = delivObjects.getString(TAG_DELIVERABLES_TITLE);
						newData.setDeliverableName(deliv_title);
						
						String deliv_descr = delivObjects.getString(TAG_DELIVERABLES_DESCRIPTION);
						newData.setDeliverablesDescription(deliv_descr);
						
						boolean deliv_status = delivObjects.getBoolean(TAG_DELIVERED_STATUS);
						newData.setDeliverableStatus(deliv_status);
						
						boolean voting_status = delivObjects.getBoolean(TAG_VOTINGOPEN);
						newData.setVotingStatus(voting_status);
						
						/*JSONArray votingObjects = delivObjects.getJSONArray(TAG_TOTAL_VOTES);
						int totalVotes = votingObjects.length();
						newData.setTotalVotes(totalVotes);*/
						
						delivData.add(newData);
				}
				
			}else{
					
					ProjectListData newData = new ProjectListData();
					newData.setNoDeliverableMessage("You have no deliverables. You better vacate ASAP");
					delivData.add(newData);
				}
					
			
		}catch(JSONException e){
			
			e.printStackTrace();
		}catch(RuntimeException e){
			
			e.printStackTrace();
		}
		
		return delivData;
	}
	
	@Override
	protected void onPostExecute(ArrayList<ProjectListData> result) {
		super.onPostExecute(result);
		if (pDialog.isShowing())
			pDialog.dismiss();
		
		mDeliverableList.dataDisplay(result);
	}

}
