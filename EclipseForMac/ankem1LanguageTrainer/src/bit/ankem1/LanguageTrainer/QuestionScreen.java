package bit.ankem1.LanguageTrainer;

import java.util.ArrayList;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionScreen extends Activity 
{
	// Private data members
	ArrayList<Question> questions;
	ArrayList<Integer> correctAnswers;
	int questionNo;
	// Controls
	TextView txtFeedback;
	RadioGroup rbgOptions;
	Button btnConfirm;
	Button btnNext;

	// On Create
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_screen);
		
		// Set up the controls
		txtFeedback = (TextView)findViewById(R.id.txtFeedback);
		rbgOptions = (RadioGroup)findViewById(R.id.rbgOptions);
		btnConfirm = (Button)findViewById(R.id.btnConfirm);
		btnNext = (Button)findViewById(R.id.btnNext);
		
		
		questions = new ArrayList<Question>();
		correctAnswers = new ArrayList<Integer>();
		questionNo = 0;
		
		
		// Populate the questions ArrayList
		questions.add(new Question(R.drawable.das_auto, "das"));
		questions.add(new Question(R.drawable.das_haus, "das"));
		questions.add(new Question(R.drawable.das_schaf, "das"));
		questions.add(new Question(R.drawable.der_apfel, "der"));
		questions.add(new Question(R.drawable.der_baum, "der"));
		questions.add(new Question(R.drawable.der_stuhl, "der"));
		questions.add(new Question(R.drawable.die_ente, "die"));
		questions.add(new Question(R.drawable.die_hexe, "die"));
		questions.add(new Question(R.drawable.die_kuh, "die"));
		questions.add(new Question(R.drawable.die_milch, "die"));
		questions.add(new Question(R.drawable.die_strasse, "die"));
		
		
		shuffleList();
		
		// Set the first image up
		setImage(questionNo);
		
		// Wire up our button event handlers
		
		// Create an instance of the class that implements the required interface
		NextButtonHandler nextButtonHandler = new NextButtonHandler();
		ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler();
		
		// Bind the event to the button, passing in our class instance
		btnConfirm.setOnClickListener(confirmButtonHandler);
		btnNext.setOnClickListener(nextButtonHandler);
		
		btnConfirm.setEnabled(true);
		btnNext.setEnabled(false);
		
	}
	
	// Assign an image to the ImageView 
	// Accept the index needed to select the right Image name
	public void setImage(int index)
	{
		Resources resources = getResources();
		
		String name = resources.getResourceEntryName(questions.get(index).getImageID());
		int resourceId = resources.getIdentifier(name, "drawable", getPackageName());
		
		ImageView imgNoun = (ImageView)findViewById(R.id.imgNoun);
		imgNoun.setImageResource(resourceId);

	}
	
	// Shuffle the ArrayList
	public void shuffleList()
	{
		Random r = new Random();
		
		// Iterate 10 times, so the list is sufficiently random
		for(int i = 0; i < 10; i++)
		{
			// Randomly select two elements in the list
			int item1 = r.nextInt(questions.size());
			int item2 = r.nextInt(questions.size());
			
			// Swap the two elements
			Question temp = questions.get(item1);
			questions.set(item1, questions.get(item2));
			questions.set(item2, temp);
			
		}
	}
	
	// Inner class for handling 'confirm' button click related events
	public class ConfirmButtonHandler implements OnClickListener
	{
		// Event handling code goes here.
		@Override
		public void onClick(View arg0) 
		{
			// Separate functionality between the two buttons
					
			// Get the RadioGroup's selected option		
			RadioButton rbSelection = (RadioButton)findViewById(rbgOptions.getCheckedRadioButtonId());
			String selection = rbSelection.getText().toString();
						
			// Decide if the answer is correct.
			if(selection.equals(questions.get(questionNo).getGender()))
			{
				correctAnswers.add(1);
				txtFeedback.setText("Correct!");
			}
			else
			{
				correctAnswers.add(0);
				txtFeedback.setText("Incorrect.");
			}
			
			btnConfirm.setEnabled(false);
			btnNext.setEnabled(true);
		}		
	}
	
	// Inner class for handling 'next' button click related events
	public class NextButtonHandler implements OnClickListener
	{
		// Event handling code goes here.
		@Override
		public void onClick(View arg0) 
		{
			// Remove the feedback from the previous question.
			txtFeedback.setText("");
			
			// Providing we haven't run out of questions...
			if(questionNo < (questions.size() - 1))
			{
				// Advance to the next question...
				questionNo++;
				
				// Set up the next image
				if(questionNo < questions.size())
					setImage(questionNo);
		
			}
			else
			{
				// the game is over, move to the finish screen and print results from the correctAnswers ArrayList.
				
				int numberCorrect = 0;
				
				// Determine the user's performance
				for(int i = 0; i < correctAnswers.size(); i++)
				{
					if (correctAnswers.get(i) == 1)
						numberCorrect++;
				}
				
				Intent goToFinish = new Intent(QuestionScreen.this, FinishScreen.class);	
				
				// Put Extras: the number correct, and the total number of questions.
				goToFinish.putExtra("numberCorrect", numberCorrect);
				goToFinish.putExtra("totalQuestions", questions.size());
				
				startActivity(goToFinish);
			}
			
			btnNext.setEnabled(false);
			btnConfirm.setEnabled(true);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_screen, menu);
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
