// Author: Matt Ankerson
// Date: 1 April 2015

package bit.ankem1.JSONData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EventDescActivity extends Activity 
{
	TextView txtDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_desc);
		
		txtDescription = (TextView)findViewById(R.id.txtDescription);
		
		// Get the information we need from the intent
		
		// Get the intent
		Intent intent = getIntent();
		// Get the string value we need from the intent
		String description = intent.getStringExtra("description");
		
		txtDescription.setText(description);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_desc, menu);
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
