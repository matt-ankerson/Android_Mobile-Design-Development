// Author: Matt Ankerson
// Date: 21 April 2015

package teleporter.ankem1.bit.ankem1teleporter;

import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;


public class TeleportGame extends ActionBarActivity
{
    private final long INTERVAL = 500;
    private final float DISTANCE = 1;

    Button btnTeleport;
    TextView txtLongitude;
    TextView txtLatitude;
    TextView txtClosestCity;
    ImageView imgWorldMap;

    double latitude;
    double longitude;

    // The LocationManager
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleport_game);

        // Get references to all our controls.
        btnTeleport = (Button)findViewById(R.id.btnTeleport);
        txtLongitude = (TextView)findViewById(R.id.txtLongitude);
        txtLatitude = (TextView)findViewById(R.id.txtLatitude);
        txtClosestCity = (TextView)findViewById(R.id.txtClosestCity);
        imgWorldMap = (ImageView)findViewById(R.id.ivWorldMap);

        // Wire up a handler for our Teleport button
        TeleportButtonHandler handler = new TeleportButtonHandler();
        btnTeleport.setOnClickListener(handler);

        // Get the map image
        Drawable worldMap = getResources().getDrawable(R.drawable.worldmap);

        imgWorldMap.setImageDrawable(worldMap);

        // Get the LocationManager and set up automatic polling for current location
        // A location manager gives us access to system location services
        locationManager = (LocationManager)getSystemService(TeleportGame.LOCATION_SERVICE);

        // Create a Criteria instance to indicate the application criteria for selecting a location provider
        Criteria defaultCriteria = new Criteria();

        // Get the provider we will use
        String providerName = locationManager.getBestProvider(defaultCriteria, false);

        // Request updates
        locationManager.requestLocationUpdates(providerName, INTERVAL, DISTANCE, new CustomLocationListener());
    }

    // Get the last known location. Return a location object.
    public Location getLastKnownLocation()
    {
        // Create a Criteria instance to indicate the application criteria for selecting a location provider
        Criteria defaultCriteria = new Criteria();

        // Get the provider we will use
        String providerName = locationManager.getBestProvider(defaultCriteria, false);

        // Read the last known location
        Location currentLocation = locationManager.getLastKnownLocation(providerName);

        return currentLocation;
    }

    private class CustomLocationListener implements LocationListener
    {
        // Called when our location changes
        @Override
        public void onLocationChanged(Location location)
        {
            DecimalFormat threePlaces = new DecimalFormat("0.000");

            longitude = location.getLongitude();
            latitude = location.getLatitude();

            // Make an instance of the JSONFetcher class and call .execute
            JSONFetcher fetcher = new JSONFetcher();
            fetcher.execute();

            // Set the text properties of our text views
            txtLongitude.setText("Longitude: " + threePlaces.format(longitude));
            txtLatitude.setText("Latitude: " + threePlaces.format(latitude));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    // Extend AsyncTask in order to fetch JSON
    public class JSONFetcher extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... args)
        {
            // Declare our JSON text
            String JSONString = "";
            int responseCode = 0;

            // Any HTTP connection related code must be in a try/catch
            try
            {
                String urlString = "http://geoplugin.net/extras/location.gp?lat=" + latitude + "&long=" + longitude + "&format=json";

                // Convert the URL string to URLObject
                URL urlObject = new URL(urlString);

                // Create an HttpURLConnection object using urlObject's openConnection command.
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

                // Send the URL
                connection.connect();

                // Get the response code, proceed only if it's 200
                responseCode = connection.getResponseCode();

                if(responseCode == 200)
                {
                    // Get an InputStream from our HttpURLConnection object and set up a BufferedReader
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    // Read the entire input
                    String responseString;
                    StringBuilder stringBuilder = new StringBuilder();

                    // Loop until the end of the input
                    while ((responseString = bufferedReader.readLine()) != null)
                    {
                        // Append the line
                        stringBuilder = stringBuilder.append(responseString);
                    }

                    // Get the string from the stringBuilder
                    JSONString = stringBuilder.toString();

                }
                else
                {
                    // Something went wrong
                    Toast.makeText(TeleportGame.this, "Error! Response code: " + responseCode, Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return JSONString;
        }

        // Accepts the fetched string of data
        // Parse the string and extract the data we want.
        @Override
        protected void onPostExecute(String fetchedString)
        {
            try
            {
                // Get the root JSON object
                JSONObject locationData = new JSONObject(fetchedString);

                // Get the location name
                String location = locationData.getString("geoplugin_place");

                // Set the text property of txtClosestCity
                txtClosestCity.setText(location);
            }
            catch (Exception e)
            {
                try
                {
                    JSONArray emptyArray = new JSONArray(fetchedString);

                    JSONArray innerArray = emptyArray.getJSONArray(0);

                    if(innerArray.length() == 0)
                    {
                        // Set the text property of txtClosestCity
                        txtClosestCity.setText("Nothing near there, sorry.");
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Inner class to handle button clicking
    public class TeleportButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            DecimalFormat threePlaces = new DecimalFormat("0.000");

            // Get latitude and longitude values
            Location loc = getLastKnownLocation();

            longitude = loc.getLongitude();
            latitude = loc.getLatitude();

            // Make an instance of the JSONFetcher class and call .execute
            JSONFetcher fetcher = new JSONFetcher();
            fetcher.execute();

            // Set the text properties of our text views
            txtLongitude.setText("Longitude: " + threePlaces.format(longitude));
            txtLatitude.setText("Latitude: " + threePlaces.format(latitude));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teleport_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
