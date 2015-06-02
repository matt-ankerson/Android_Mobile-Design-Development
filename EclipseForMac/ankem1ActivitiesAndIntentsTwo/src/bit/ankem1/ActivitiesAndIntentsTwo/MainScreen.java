package bit.ankem1.ActivitiesAndIntentsTwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends Activity 
{
	Button btnColour;
	TextView txtContent;

	// On Create event handler
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		btnColour = (Button)findViewById(R.id.btnColour);
		txtContent = (TextView)findViewById(R.id.txtContent);
		
		ButtonHandler handler = new ButtonHandler();
		btnColour.setOnClickListener(handler);
	}
	
	@Override
	protected void onActivityResult(int requestId, int resultCode, Intent returnIntent)
	{
		//---------------------------
		// Retrieve the requested data
		
		// Determine appropriate action based on the request ID
		if(requestId == 0)
		{
			// Did the activity return 'Okay'?
			if(resultCode == Activity.RESULT_OK)
			{
				// Get the data from the returnIntent
				int returnedColour = returnIntent.getIntExtra("colour", 0);
				
				// Set the colour property of the content textview with our colour
				txtContent.setTextColor(returnedColour);
			}
		}
	}
	
	// Inner class for button event handling
	public class ButtonHandler implements OnClickListener
	{

		// Button click event handler
		@Override
		public void onClick(View arg0) 
		{
			// Get the text colour from the other activity
			// Call activity for result
			Intent intentForResult = new Intent(MainScreen.this, Settings.class);
			
			// The second parameter is an id to track the request.
			startActivityForResult(intentForResult, 0);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
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
