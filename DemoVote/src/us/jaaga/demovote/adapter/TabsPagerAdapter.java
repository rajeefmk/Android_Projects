package us.jaaga.demovote.adapter;

import us.jaaga.demovote.fragments.PastProjects;
import us.jaaga.demovote.fragments.UpcomingProject;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	//ArrayList<ProjectListData> data = new ArrayList<ProjectListData>();

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		
		//ArrayList<ProjectListData> result
		//this.data = result;
	}
	
	/*public TabsPagerAdapter(){
		
		this.data = result;
	}*/

	@Override
	public Fragment getItem(int index) {
	
		switch(index){
		case 0:
			return new UpcomingProject();//data
		
		case 1:
			return new PastProjects();// data
		
		}
		
		return null;
		
		
	}

	@Override
	public int getCount() {
		return 2;
	}

}
