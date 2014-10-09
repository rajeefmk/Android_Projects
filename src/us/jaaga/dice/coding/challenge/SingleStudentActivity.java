package us.jaaga.dice.coding.challenge;


import us.jaaga.mooctracker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SingleStudentActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_NAME = "name";
	private static final String TAG_PROJECT ="Project";
	private static final String TAG_COURSE = "Course";
	private static final String TASK = "Check:";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
        Log.i(TASK, "Received TAG_NAME");
        
        String project = in.getStringExtra(TAG_PROJECT);
        Log.i(TASK, "Received TAG_PROJECT");
        	
        String course = in.getStringExtra(TAG_COURSE);
        Log.i(TASK, "Received TAG_COURSE");
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        Log.i(TASK, "Infalted Name");
        
        TextView lblProject = (TextView) findViewById(R.id.project_label);
        Log.i(TASK, "Infalted Lable");
        
        TextView lblCourse = (TextView) findViewById(R.id.course_label);
        Log.i(TASK, "Infalted Course");
        
        lblName.setText(name);
        Log.i(TASK, "setName");
        
        lblProject.setText(project);
        Log.i(TASK, "setProject");
        
        lblCourse.setText(course);
        Log.i(TASK, "setCourse");
    }
}
