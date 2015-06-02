package bit.ankem1.KeyEventsOnEditText;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class KeyEventsOnEditText extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_key_events_on_edit_text);
		
		// Get the EditText
		EditText editTextKeyEvents = (EditText)findViewById(R.id.editTextKeyEvents);
		
		// Create an instance of the class that implements the required interface/s
		EditTextKeyEventsHandler handler = new EditTextKeyEventsHandler();
		
		// Bind the event handler to the EditText control
		editTextKeyEvents.setOnKeyListener(handler);
	}
	
	// An inner class that implements the required interfaces for KeyEvent handling
	public class EditTextKeyEventsHandler implements OnKeyListener
	{
		// Event handler for editTextKeyEvents
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
			// Get a reference to the EditText
			EditText editText = (EditText)findViewById(v.getId());
			
			// Decide which Toast message to show
			if(editText.getText().toString().contains("@"))
			{
				Toast.makeText(KeyEventsOnEditText.this, "Don't type @", Toast.LENGTH_LONG).show();
			}
			
			return false;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.key_events_on_edit_text, menu);
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
