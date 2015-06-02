package bit.ankem1.firstAndoidApp;

import java.util.Random;

import android.R.string;
import android.app.Activity;
import android.content.res.Resources;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class FirstActivity extends Activity {

	// This event is fired when the app is opened
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        
        // Change the contents of a TextView via it's setText(String s) method
        
        // Get the dog breed
        String dogBreed = selectRandomString();
        // Get the TextView by calling findViewById
        TextView txtDisplaySubHeading = (TextView)findViewById(R.id.txtDisplaySubHeading);
        // Use setText to set the text of our TextView
        txtDisplaySubHeading.setText(dogBreed);
        
        // Modify the title TextDisplay to show FebFridays
        
        // Get the reference to the TextView via its id stored in R
        TextView txtDisplayTitle = (TextView)findViewById(R.id.txtDisplayTitle);
        
        // Create a Resources object
        Resources resourceRevolver = getResources();
        // Pass the resource id of the TextView (stored in R) to the Resources object, to get the actual value
        int datesArray[] = resourceRevolver.getIntArray(R.array.FebFridays);
        
        // Add the dates to the TextView
        String fridayString = "February Fridays on: ";
        
        for(int i = 0; i < datesArray.length; i = i + 1)
        {
        	fridayString+= Integer.toString(datesArray[i]) + " ";
        }
        
        txtDisplayTitle.setText(fridayString);
    }
    
    // Returns a random string
    public String selectRandomString()
    {
    	Random rGen = new Random();
    	int dogValue = rGen.nextInt(4);
    	String dogBreed = "";
    	
    	// Switch on dogValue
    	switch(dogValue)
    	{
    	case 0:
    		dogBreed = "Collie";
    		break;
    	case 1:
    		dogBreed = "Poodle";
    		break;
    	case 2:
    		dogBreed = "Huntaway";
    		break;
    	case 3:
    		dogBreed = "Pig";
    		break;
    	}
    	
    	return dogBreed;
    }
}
