package bit.ankem1.ActivitiesAndIntentsTwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Settings extends Activity 
{
	
	TextView txtSettingsContent;
	
	// On Create event handler
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		txtSettingsContent = (TextView)findViewById(R.id.txtSettingsContent);
		int colour = txtSettingsContent.getCurrentTextColor();
		
		// Return a result to the calling activity
		
		// Create a new Intent
		Intent returnIntent = new Intent();
		
		// Load up the return value. (The colour of the text in this activity)
		returnIntent.putExtra("colour", colour);
		
		// Set the termination code flag
		setResult(Activity.RESULT_OK, returnIntent);
		
		// Pop this activity off the activity stack so we return to the calling activity.
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
