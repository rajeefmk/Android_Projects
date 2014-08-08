package us.jaaga.demovote;

import java.util.ArrayList;
import java.util.List;

import us.jaaga.demovote.adapter.StudentListAdapter;
import us.jaaga.demovote.helper.AsyncData;
import us.jaaga.demovote.models.ProjectListData;
import us.jaaga.demovote.models.StudentListData;
import android.app.ListActivity;
import android.content.Intent;
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
		
		AsyncData mAsyncData = new AsyncData(this);
		mAsyncData.execute();
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//super.onListItemClick(l, v, position, id);
		
		Intent mIntent = new Intent(StudentListActivity.this, ProjectList.class);
		StudentListData mStudentListData = (StudentListData) getListAdapter().getItem(position);
		
		projectListData = mStudentListData.getDelivList();
		
		Bundle mBundle = new Bundle();
		mBundle.putSerializable("project", projectListData);
		//mIntent.putParcelableList("key", projectListData);
		mIntent.putExtra("Deliv", mBundle);
		startActivity(mIntent);
		
	}
	
	public void dataDisplay(ArrayList<StudentListData> data){
		
		mStudentListAdapter = new StudentListAdapter(this, data);
		setListAdapter(mStudentListAdapter);
		
	}
}
