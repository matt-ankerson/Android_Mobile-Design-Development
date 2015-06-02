package bit.ankem1.MultipleActivities;

import bit.ankem1.MultipleActivities.Activity_a.ButtonHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity_b extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_a);
		
		// Modify the TextView
		TextView txtIdentify = (TextView)findViewById(R.id.txtIdentify);
		txtIdentify.setText("You're on Activity B.");
		
		// Wire up the button event handler
		
		ButtonHandler handler = new ButtonHandler();
		Button btnChangeActivity = (Button)findViewById(R.id.btnChangeActivity);
		btnChangeActivity.setOnClickListener(handler);
	}
	
	// Inner class for the button handler
	public class ButtonHandler implements OnClickListener
	{

		// Event handler for button click
		@Override
		public void onClick(View arg0) 
		{
			Intent changeActivity = new Intent(Activity_b.this, Activity_c.class);
			startActivity(changeActivity);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_b, menu);
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
