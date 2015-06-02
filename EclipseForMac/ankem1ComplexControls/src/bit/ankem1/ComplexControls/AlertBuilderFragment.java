package bit.ankem1.ComplexControls;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertBuilderFragment extends DialogFragment 
{
	
	// Default constructor
	public AlertBuilderFragment() {
		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		// Create the builder
		Builder builder = new AlertDialog.Builder(getActivity());
		// Set the icon
		builder.setIcon(R.drawable.ic_launcher);
		// Set the title
		builder.setTitle("Do you really want to enrol?");
		// Set positive and negative buttons
		builder.setPositiveButton("Yes", new YesButtonHandler());
		builder.setNegativeButton("No", new NoButtonHandler());
		
		// Create the dialog and return it
		Dialog dialog = builder.create();
		
		return dialog;
	}
	
	// inner classes for button event handling
	public class YesButtonHandler implements DialogInterface.OnClickListener
	{

		@Override
		public void onClick(DialogInterface dialog, int whichButton) 
		{
			ComplexControls main = (ComplexControls)getActivity();
			main.setEnrolConfirmation(true);
			
		}	
	}

	// inner classes for button event handling
		public class NoButtonHandler implements DialogInterface.OnClickListener
		{

			@Override
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				ComplexControls main = (ComplexControls)getActivity();
				main.setEnrolConfirmation(false);
				
			}	
		}
}
