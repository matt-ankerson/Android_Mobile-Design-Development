package bit.ankem1.ActivitiesAndIntentsOne;

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
	Button btnSettings;
	TextView txtUsername;

	// On Create event handler
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		txtUsername = (TextView)findViewById(R.id.txtUsername);
		btnSettings = (Button)findViewById(R.id.btnSettings);
		
		
		SettingsButtonHandler handler = new SettingsButtonHandler();
		btnSettings.setOnClickListener(handler);	
		
		// Get the username from the intent
		String username = getIntent().getStringExtra("username");
		
		if ((username == null)||(username == ""))
		{
			txtUsername.setText("No username");
		}
		else
		{
			txtUsername.setText(username);
		}
	}
	
	// Inner class for button events
	public class SettingsButtonHandler implements OnClickListener
	{

		// Event handler for Button click events
		@Override
		public void onClick(View arg0) 
		{
			// Open the settings Activity.
			Intent intent = new Intent(MainScreen.this, Settings.class);
			startActivity(intent);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
