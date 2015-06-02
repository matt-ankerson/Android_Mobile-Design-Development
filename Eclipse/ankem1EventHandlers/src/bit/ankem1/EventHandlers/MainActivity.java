package bit.ankem1.EventHandlers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Get the btnToast button
		Button btnToast = (Button)findViewById(R.id.btnToast);
		
		// Create an instance of the class that implements the appropriate interface
		ClickHandlerWithToast btnToastHandler = new ClickHandlerWithToast();
		
		// Call setOnClickListener, passing in the object that implements the interface
		btnToast.setOnClickListener(btnToastHandler);
		
		
	}
	
	// Inner class for button click event handler
	public class ClickHandlerWithToast implements OnClickListener
	{
		// onClick event handler for button
		@Override
		public void onClick(View v) 
		{
			// Show a Toast notification
			Toast.makeText(MainActivity.this, "You clicked the button!", Toast.LENGTH_LONG).show();		
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
