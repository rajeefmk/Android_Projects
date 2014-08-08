package us.jaaga.demovote.adapter;

import java.util.ArrayList;

import us.jaaga.demovote.R;
import us.jaaga.demovote.models.ProjectListData;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class DeliverableListAdapter extends ArrayAdapter<ProjectListData>  {
	
	private ArrayList<ProjectListData> projectList;
	private LayoutInflater mLayoutInflater;

	public DeliverableListAdapter(Activity activity, ArrayList<ProjectListData> data) {
		super(activity,R.layout.deliverable_row, data);
		
		mLayoutInflater = activity.getWindow().getLayoutInflater();
		projectList = data;
	}

	public DeliverableListAdapter (Activity activity, String[] str){
		super(activity, R.layout.deliverable_row);
		
		mLayoutInflater = activity.getWindow().getLayoutInflater();
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = mLayoutInflater.inflate(R.layout.deliverable_row, parent, false);
		ProjectListData currentProject = projectList.get(position);
		
		//Name of Deliverable
		TextView delivName = (TextView) row.findViewById(R.id.delivName);
		
		String delivTitle = currentProject.getDeliverableTitle();
		
		
		delivName.setText("Name:"+delivTitle);
		
		//Number of Projects
		TextView totalNum = (TextView) row.findViewById(R.id.delivNumbers);
		
		//TODO Get this data from previous call.
		
		return row;
	}

}
