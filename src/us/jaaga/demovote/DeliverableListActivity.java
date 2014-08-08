package us.jaaga.demovote;

import java.util.ArrayList;

import us.jaaga.demovote.adapter.DeliverableListAdapter;
import us.jaaga.demovote.helper.AsyncDeliv;
import us.jaaga.demovote.models.ProjectListData;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class DeliverableListActivity extends ListActivity {

	//ArrayList<StudentListData> studentListData = new ArrayList<StudentListData>();
	ArrayList<ProjectListData> projectListData = new ArrayList<ProjectListData>();
	DeliverableListAdapter mDeliverableListAdapter;
	String user_id;
	Intent mIntent;
	
	private static final String TAG = "DeliverableListActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.deliverable_list);
		mIntent = getIntent();
		
		if(mIntent.getExtras().getBoolean("Network_key")){
			
			SharedPreferences mSharedPreferences = getSharedPreferences("demo_vote", MODE_PRIVATE);
			String test_token = mSharedPreferences.getString("token", null);
			Log.i(TAG, "Token is loaded");
			
			//Intent mIntent = getIntent();
			
			user_id = getIntent().getStringExtra("user_id");
			Log.i(TAG, "unique user obtained from intent");
			
			AsyncDeliv mAsyncDeliv = new AsyncDeliv(this,user_id,test_token );
			Log.i(TAG, "Context,user id and token is sent to AsyncDeliv");
			
			mAsyncDeliv.execute();
			Log.i(TAG, "AsyncDeliv is executed");
			
		}else{
			
			Toast.makeText(getApplicationContext(), "O Boy !! There ain't no D-E-L-I-V-E-R-A-B-L-E", Toast.LENGTH_LONG).show();
			
		}
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//super.onListItemClick(l, v, position, id);
		
		ProjectListData mProjectListData = (ProjectListData) getListAdapter().getItem(position);
		
		String project_name = mProjectListData.getDeliverableTitle();
		String project_description = mProjectListData.getDeliverablesDescription();
		
		Intent mIntent = new Intent(DeliverableListActivity.this, ProjectDetail.class);
		mIntent.putExtra("name", project_name);
		mIntent.putExtra("description", project_description);
		
		startActivity(mIntent);
		
		//StudentListData mStudentListData = (StudentListData) getListAdapter().getItem(position);
		//projectListData = mStudentListData.getDelivList();
		
		//Bundle mBundle = new Bundle();
		//mBundle.putSerializable("project", projectListData);
		//mIntent.putParcelableList("key", projectListData);
		
		//mIntent.putExtra("key", (Serializable)projectListData);
		
		/*SharedPreferences mSharedPreference = getSharedPreferences("delivdata", MODE_PRIVATE);
		Editor mEditor = mSharedPreference.edit();
			
		//mEditor.putString("deliv_data", ObjectSe)
*/		
		
		
		
	}
	
	public void dataDisplay(ArrayList<ProjectListData> data){
		
		mDeliverableListAdapter = new DeliverableListAdapter(this, data);
		setListAdapter(mDeliverableListAdapter);
		
	}
}
