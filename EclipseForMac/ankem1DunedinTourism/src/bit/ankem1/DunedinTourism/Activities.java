package bit.ankem1.DunedinTourism;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Activities extends Activity 
{
	TextView txtTitle;
	ImageView imgContent;

	// On Create event handler
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities);
		
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("Fun Things to do");
		
		imgContent = (ImageView)findViewById(R.id.imgContent);
		
		// Set the image
		Resources resources = getResources();	
		String name = resources.getResourceEntryName(R.drawable.surf);
		int resourceId = resources.getIdentifier(name, "drawable", getPackageName());
		
		imgContent.setImageResource(resourceId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activities, menu);
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
