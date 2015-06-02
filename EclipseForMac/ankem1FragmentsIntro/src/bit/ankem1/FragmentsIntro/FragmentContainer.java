package bit.ankem1.FragmentsIntro;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentContainer extends Activity 
{
	Button btnShowImageFragment;
	Button btnShowListFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_container_large);
		
		btnShowImageFragment = (Button)findViewById(R.id.btnShowImageFragment);
		btnShowListFragment = (Button)findViewById(R.id.btnShowListFragment);
		
		
		BtnShowImageHandler showImageHandler = new BtnShowImageHandler();
		btnShowImageFragment.setOnClickListener(showImageHandler);
		
		BtnShowListHandler showListHandler = new BtnShowListHandler();
		btnShowListFragment.setOnClickListener(showListHandler);
		
	}
	
	// Inner class for show image button event handler
	public class BtnShowImageHandler implements OnClickListener
	{

		@Override
		public void onClick(View arg0) 
		{
			// Create the Fragment and show it within this activity
			
			// Instantiate our fragment
			Fragment dynamicFragment = new ShowImageFragment();
			// Get the fragment manager
			FragmentManager fm = getFragmentManager();
			// Begin the transaction
			FragmentTransaction ft = fm.beginTransaction();
			
			// Make all fragment changes here
			ft.replace(R.id.fragmentImageContainer, dynamicFragment);
			
			// Commit the transaction
			ft.commit();
		}
		
	}
	
	// Inner class for show list view button event handler
		public class BtnShowListHandler implements OnClickListener
		{

			@Override
			public void onClick(View arg0) 
			{
				// Create the Fragment and show it within this activity
				
				// Instantiate our fragment
				Fragment dynamicFragment = new ShowListViewFragment();
				// Get the fragment manager
				FragmentManager fm = getFragmentManager();
				// Begin the transaction
				FragmentTransaction ft = fm.beginTransaction();
				
				// Make all fragment changes here
				ft.replace(R.id.fragmentListContainer, dynamicFragment);
				
				// Commit the transaction
				ft.commit();
			}
			
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_container, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
