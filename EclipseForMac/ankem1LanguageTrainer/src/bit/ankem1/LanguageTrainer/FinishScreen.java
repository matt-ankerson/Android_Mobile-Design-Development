package bit.ankem1.LanguageTrainer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FinishScreen extends Activity 
{

	// On Create
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish_screen);

		// Get the intent
		Intent intent = getIntent();	
		// Get the integer value from the intent
		int correctAnswers = intent.getIntExtra("numberCorrect", 0);
		int totalQuestions = intent.getIntExtra("totalQuestions", 11);
		// Get the total number of questions from the intent
		
		// Write the results to our text view.
		TextView txtScore = (TextView)findViewById(R.id.txtScore);
		txtScore.setText(correctAnswers + " out of " + totalQuestions);

		
		// Wire up the button handlers
		ButtonHandler handler = new ButtonHandler();
		
		Button btnPlayAgain = (Button)findViewById(R.id.btnPlayAgain);
		Button btnExit = (Button)findViewById(R.id.btnExit);
		
		btnPlayAgain.setOnClickListener(handler);
		btnExit.setOnClickListener(handler);
	}
	
	// Inner class for handling button events
	public class ButtonHandler implements OnClickListener
	{

		// Event handler for button_click
		@Override
		public void onClick(View v) 
		{
			// If we pressed the Play Again button
			if(v.getId() == R.id.btnPlayAgain)
			{
				// Go to the splash screen.
				Intent goToSplashScreen = new Intent(FinishScreen.this, SplashScreen.class);
				startActivity(goToSplashScreen);
			}
			// If the exit button was pressed
			if(v.getId() == R.id.btnExit)
			{
				// Exit the application by returning the user to the home screen
				Intent close = new Intent(Intent.ACTION_MAIN);
		        close.addCategory(Intent.CATEGORY_HOME);
		        close.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        startActivity(close);
			}					
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish_screen, menu);
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
