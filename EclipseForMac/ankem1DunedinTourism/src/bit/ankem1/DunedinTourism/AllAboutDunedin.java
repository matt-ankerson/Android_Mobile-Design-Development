package bit.ankem1.DunedinTourism;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllAboutDunedin extends Activity 
{
	
	ListView lvNavigation;

	// On Create event handler
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_about_dunedin);
		
		lvNavigation = (ListView)findViewById(R.id.lvNavigation);
		
		setUpListViewNavigation();
		
		lvNavigation.setOnItemClickListener(new ListViewHandler());
	}
	
	// Pupulate the ListView for navigation.
	public void setUpListViewNavigation()
	{
		String[] items = {"Services", "Fun Things to do", "Wining / Dining", "Shopping"};
		
		// Bind to the control using an ArrayAdapter
		ArrayAdapter<String> listItemAdapter = new ArrayAdapter<String>(this, R.layout.dunedin_list_items, items);
		lvNavigation.setAdapter(listItemAdapter);
		
	}
	
	// Inner class for handling ListView events
	public class ListViewHandler implements OnItemClickListener
	{

		// Event handling code for selecting an item from the ListView
		@Override
		public void onItemClick(AdapterView<?> list, View itemView, int position, long id) 
		{
			// Employ parallel arrays.
			Activities activities = new Activities();
			Shopping shopping = new Shopping();
			Dining dining = new Dining();
			Services services = new Services();
			
			Object[] activityOptions = {services, activities, dining, shopping};
			
			Intent goToIntent;
			goToIntent = new Intent(AllAboutDunedin.this, activityOptions[position].getClass());
			
			startActivity(goToIntent);
				
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_about_dunedin, menu);
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
