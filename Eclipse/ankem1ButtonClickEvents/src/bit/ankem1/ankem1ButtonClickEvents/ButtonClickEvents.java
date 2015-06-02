package bit.ankem1.ankem1ButtonClickEvents;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ButtonClickEvents extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button_click_events);
		
		// Get the button by it's id
		Button btnToast = (Button)findViewById(R.id.btnToast);
		
		// Create an instance of the class that implements the appropriate interface/s
		ToastButtonClickHandler btnToastHandler = new ToastButtonClickHandler();
		
		// Call setOnClickListener and setOnLongClickListener on the Button, passing in the object that implements
		// our needed interfaces. This binds the event handler to the button.
		btnToast.setOnClickListener(btnToastHandler);
		btnToast.setOnLongClickListener(btnToastHandler);
	}
	
	// Inner class for ButtonClick event handling
	public class ToastButtonClickHandler implements OnClickListener, OnLongClickListener
	{
		// Event handler for a 'long' press of the button
		@Override
		public boolean onLongClick(View v) 
		{
			// Show a Toast notification
			Toast.makeText(ButtonClickEvents.this, "You held the button for ages!", Toast.LENGTH_LONG).show();
			return false;
		}

		// Event handler for an ordinary press of the button
		@Override
		public void onClick(View v) 
		{
			// Show a Toast notification
			Toast.makeText(ButtonClickEvents.this, "You clicked the button!", Toast.LENGTH_LONG).show();
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.button_click_events, menu);
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
