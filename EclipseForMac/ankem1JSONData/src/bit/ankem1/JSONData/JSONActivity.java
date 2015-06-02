// Author: Matt Ankerson
// Date: 1 April 2015
// This program deals with a JSON text file and displays its contents in a ListView

package bit.ankem1.JSONData;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class JSONActivity extends Activity 
{
	ListView lvEvents;
	ArrayList<String> eventList;
	ArrayList<String> eventDescriptions;
	JSONArray rawEventArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
		
		lvEvents = (ListView)findViewById(R.id.lvNavigation);
		eventList = new ArrayList<String>();
		eventDescriptions = new ArrayList<String>();
		
		// Get our JSONObject
		JSONObject dunedinData = getJSONObject("dunedin_events.json");
		
		// Populate the listview
		populateListsFromJSON(dunedinData);
		
		// Bind the array of event titles to our listview.
		ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventList);
		lvEvents.setAdapter(listViewAdapter);
		
		// Wire up the event handler for our list view.
		ListViewClickHandler handler = new ListViewClickHandler();
		lvEvents.setOnItemClickListener(handler);
			
	}
	
	// Inner class to handle the selection of a list item
	public class ListViewClickHandler implements OnItemClickListener
	{
		// AdapterView is the parent class of ListView. The passed in argument is the actual listView that was clicked on
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
		{
			// Decide which description to pull from the ArrayList based on the selected index in the ListView.
			String description = eventDescriptions.get(position);
					
			// Create an intent
			Intent goToDescription = new Intent(JSONActivity.this, EventDescActivity.class);
			
			// Load the intent with the required data
			goToDescription.putExtra("description", description);
			startActivity(goToDescription);
			
		}
		
	}
	
	// Populate a given ListView from the "events" JSON Object
	// This method also populates the eventDescriptions ArrayList which is bad.
	// 	>> this should be in a separate method.
	public void populateListsFromJSON(JSONObject jo)
	{
		try
		{
			eventList.clear();
			eventDescriptions.clear();
			
			// Get the value part of the Key-Value pair
			JSONObject events = jo.getJSONObject("events");
			
			// Get the event array
			rawEventArray = events.getJSONArray("event");
			
			// Loop over our Array of raw JSON event information to get the titles.
			for(int i = 0; i < rawEventArray.length(); i++)
			{
				// Get the raw event object
				JSONObject rawEvent = rawEventArray.getJSONObject(i);	
				
				// Get the event title
				String eventTitle = rawEvent.getString("title");
				
				// Get the event description
				String eventDescription = rawEvent.getString("description");
				
				eventList.add(eventTitle);
				eventDescriptions.add(eventDescription);
			}
			
		}
		catch (Exception e)
		{
			// Something went wrong
			Toast.makeText(this, "didn't work", Toast.LENGTH_LONG).show();
		}
	}
	
	// Get a JSONObject using the JSON file.
	public JSONObject getJSONObject(String assetFileName)
	{
		
		JSONObject dunedinData;
		
		try
		{	
			// Get an asset manager and create an input stream from the JSON file.
			AssetManager am = getAssets();
			InputStream inputStream = am.open(assetFileName);
			
			// Determine the number of bytes in the file and prepare the byte buffer array for read
			int fileSize = inputStream.available();
			byte[] JSONBuffer = new byte[fileSize];
			
			// Read the stream into the buffer then close it.
			inputStream.read(JSONBuffer);
			inputStream.close();
			
			// Convert the byte[] to a string
			String JSONText = new String(JSONBuffer);
			
			// Create the JSONObject using the complete string.
			dunedinData = new JSONObject(JSONText);
			
		}
		catch (Exception e)
		{
			dunedinData = null;
		}
		
		
		return dunedinData;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.json, menu);
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
