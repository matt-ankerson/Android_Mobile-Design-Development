package bit.ankem1.ActivitiesAndIntentsOne;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends Activity 
{
	Button btnReturnToMain;
	EditText edtUsername;

	// On Create event handler
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		edtUsername = (EditText)findViewById(R.id.edtUsername);
		btnReturnToMain = (Button)findViewById(R.id.btnReturnToMain);
		
		ButtonHandler handler = new ButtonHandler();
		btnReturnToMain.setOnClickListener(handler);
	}
	
	// Inner class for button events
	public class ButtonHandler implements OnClickListener
	{

		// Event handling code
		@Override
		public void onClick(View v) 
		{
			String username = edtUsername.getText().toString();
			
			// Pass control back to the main screen
			Intent intent = new Intent(Settings.this, MainScreen.class);
			Bundle bundle = new Bundle();
			bundle.putString("username", username);
			intent.putExtras(bundle);
			startActivity(intent);
		}
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
