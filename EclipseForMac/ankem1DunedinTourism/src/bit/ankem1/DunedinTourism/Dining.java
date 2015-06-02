package bit.ankem1.DunedinTourism;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Dining extends Activity 
{
	ImageView imgContent;
	TextView txtTitle;

	// On Create event handlers
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities);
		
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		imgContent = (ImageView)findViewById(R.id.imgContent);
		
		txtTitle.setText("Wining / Dining");

		// Set the image
		Resources resources = getResources();	
		String name = resources.getResourceEntryName(R.drawable.ale);
		int resourceId = resources.getIdentifier(name, "drawable", getPackageName());
		
		imgContent.setImageResource(resourceId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dining, menu);
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
