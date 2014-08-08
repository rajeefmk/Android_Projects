package us.jaaga.demovote;

import java.util.ArrayList;

import us.jaaga.demovote.adapter.StudentListAdapter;
import us.jaaga.demovote.helper.AsyncData;
import us.jaaga.demovote.models.ProjectListData;
import us.jaaga.demovote.models.StudentListData;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class StudentListActivity extends ListActivity {

	ArrayList<StudentListData> studentListData = new ArrayList<StudentListData>();
	ArrayList<ProjectListData> projectListData = new ArrayList<ProjectListData>();
	StudentListAdapter mStudentListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.students_list);
		
		SharedPreferences mSharedPreferences = getSharedPreferences("demo_vote", MODE_PRIVATE);
		String test_token = mSharedPreferences.getString("token", null);
		
		AsyncData mAsyncData = new AsyncData(this, test_token);
		mAsyncData.execute();
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//super.onListItemClick(l, v, position, id);
		
		Intent mIntent = new Intent(StudentListActivity.this, DeliverableList.class);
		StudentListData mStudentListData = (StudentListData) getListAdapter().getItem(position);
		
		String user_id = mStudentListData.getId();
		
		mIntent.putExtra("user_id", user_id);
		
		///projectListData = mStudentListData.getDelivList();
		
		//Bundle mBundle = new Bundle();
		//mBundle.putSerializable("project", projectListData);
		//mIntent.putParcelableList("key", projectListData);
		
		//mIntent.putExtra("key", (Serializable)projectListData);
		
		/*SharedPreferences mSharedPreference = getSharedPreferences("delivdata", MODE_PRIVATE);
		Editor mEditor = mSharedPreference.edit();
			
		//mEditor.putString("deliv_data", ObjectSe)
*/		
		
		startActivity(mIntent);
		
	}
	
	public void dataDisplay(ArrayList<StudentListData> data){
		
		mStudentListAdapter = new StudentListAdapter(this, data);
		setListAdapter(mStudentListAdapter);
		
	}
}
