package bit.ankem1.LanguageTrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashScreen extends Activity 
{
	// On Create
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		// Wire up the button handler
		Button btnBegin = (Button)findViewById(R.id.btnBegin);
		ButtonHandler handler = new ButtonHandler();
		btnBegin.setOnClickListener(handler);
	}
	
	// Inner class for button event handling
	public class ButtonHandler implements OnClickListener
	{

		// Event handler
		@Override
		public void onClick(View arg0) 
		{
			// Create an intent and pass control to the QuestionScreen
			Intent goToQuestions = new Intent(SplashScreen.this, QuestionScreen.class);
			startActivity(goToQuestions);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
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
