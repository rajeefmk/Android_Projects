package us.jaaga.samaltmanblog;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	

	
	class MyPageAdapter extends FragmentPagerAdapter {
		
		private List<Fragment> mFragments;
		private List<String> titles;
		
		public MyPageAdapter(FragmentManager fm) {
			super(fm);
			this.mFragments = new ArrayList<Fragment>();
			this.titles = new ArrayList<String>();
		}
		
		public void addItem(String url, String title) {
			
			Fragment myFragment = new MyWebView();
			Bundle args = new Bundle();
			args.putString("url", url);
			myFragment.setArguments(args);
			this.mFragments.add(myFragment);
			this.titles.add(title);
		}
		
		@Override
		public Fragment getItem(int position) {
			
			
			return this.mFragments.get(position);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			return this.titles.get(position);
		}
		
		@Override
		public int getCount() {
			
			return this.mFragments.size();
		}
		
	}
	
	private MyPageAdapter pageAdapter = null;
	private ViewPager pager = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		pageAdapter = new MyPageAdapter(getSupportFragmentManager());
		
		//The number of items to fragment list is added here
		
		pageAdapter.addItem("http://paulgraham.com/articles.html", "Paul Graham");
		pageAdapter.addItem("http://blog.samaltman.com/","Sam Altman");
		
		pager = (ViewPager) findViewById(R.id.pager);
		
		// This gives the number of Fragments loaded outside the view.
		// Here set to the number of Fragments minus one, i.e., all Fragments loaded.
		// This might not be a good idea if there are many Fragments.
		
		pager.setOffscreenPageLimit(pageAdapter.getCount()-1);
		pager.setAdapter(pageAdapter);
		
		
	}
	
	
	
	/*@Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }*/
	
	//Back key History
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
			
			mWebView.goBack();
			return true;
		}
		
		else if((keyCode == KeyEvent.KEYCODE_FORWARD) && mWebView.canGoForward()){
			
			mWebView.goForward();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	} */

}
