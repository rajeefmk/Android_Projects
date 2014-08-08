package us.jaaga.demovote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProjectDetail extends Activity{

	//TextView delivName, delivDescrip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.project_detail);
		
		TextView mTextView1 = (TextView) findViewById(R.id.delivName);
		TextView mTextView2 = (TextView) findViewById(R.id.delivNumbers);
		TextView mTextView3 = (TextView) findViewById(R.id.upvote);
		TextView mTextView4 = (TextView) findViewById(R.id.downvote);
		
		Button upVote = (Button) findViewById(R.id.upvoteButton);
		Button downVote = (Button) findViewById(R.id.downvoteButton);
		
		mTextView1.setText( getIntent().getStringExtra("name") );
		mTextView2.setText( getIntent().getStringExtra("description") );
		
		
	}
}
