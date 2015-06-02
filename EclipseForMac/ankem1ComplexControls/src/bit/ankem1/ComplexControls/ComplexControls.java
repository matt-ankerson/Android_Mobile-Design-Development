package bit.ankem1.ComplexControls;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ComplexControls extends Activity 
{
	AlertBuilderFragment confirmEnrol;
	TextView txtResult;

	// onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complex_controls);
		
		txtResult = (TextView)findViewById(R.id.txtResult);
		
		// Load up the spinner with months
		Spinner spinner = (Spinner)findViewById(R.id.spnMonths);
		
		// Create an ArrayAdapter using the string array and default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
		
		// Specify the layout to use 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Set the spinner's adaptor
		spinner.setAdapter(adapter);
		
		
		// Wire up the Button event handler
		// Get the Button
		Button btnConfirm = (Button)findViewById(R.id.btnConfirm);
		
		// Create an instance of the class that implements the required interface
		ButtonHandler buttonHandler = new ButtonHandler();
		
		// Bind the event to the button, passing in our class instance
		btnConfirm.setOnClickListener(buttonHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.complex_controls, menu);
		return true;
	}
	
	// Inner class for handling Button related events
	public class ButtonHandler implements OnClickListener
	{

		// Event handler for onClick
		@Override
		public void onClick(View arg0) 
		{
			// Create the dialog
			confirmEnrol = new AlertBuilderFragment();
			// Get the fragment manager
			FragmentManager fm = getFragmentManager();
			// Show the dialog
			confirmEnrol.show(fm, "confirm");
			
		}		
	}
	
	// Accept data from the Dialog fragment and take appropriate action.
	public void setEnrolConfirmation(boolean enrol)
	{
		// Close the dialog
		confirmEnrol.dismiss();
		
		if(enrol)
		{
			RadioGroup rbgInstruments = (RadioGroup)findViewById(R.id.rbgInstruments);
			RadioButton rbSelection = (RadioButton)findViewById(rbgInstruments.getCheckedRadioButtonId());
			String instrument = rbSelection.getText().toString();
			
			Spinner spinner = (Spinner)findViewById(R.id.spnMonths);
			String monthSelection = spinner.getSelectedItem().toString();
			
			TextView txtResult = (TextView)findViewById(R.id.txtResult);
			
			txtResult.setText("You have enrolled for " + instrument + " lessons, starting in " + monthSelection);
		}
		else
		{
			txtResult.setText("Oh...");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
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
