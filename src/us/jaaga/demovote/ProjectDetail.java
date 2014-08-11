package us.jaaga.demovote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProjectDetail extends Activity{

	Intent mIntent;
	TextView delivName, delivDescription;
	int upVoteCount, downVoteCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.project_detail);
		
		TextView delivName = (TextView) findViewById(R.id.delivName);
		TextView delivDescription = (TextView) findViewById(R.id.delivDescription);
		TextView delivUpVoteCount = (TextView) findViewById(R.id.upvote);
		TextView delivDownVoteCount = (TextView) findViewById(R.id.downvote);
		
		mIntent = getIntent();
		
		delivName.setText(mIntent.getStringExtra("name"));
		delivDescription.setText(mIntent.getStringExtra("description"));
		delivUpVoteCount.setText("Total Upvotes: " + mIntent.getIntExtra("upVoteCount", upVoteCount));
		delivDownVoteCount.setText("Total DownVotes: " + mIntent.getIntExtra("downVoteCount", downVoteCount));
	}
}
