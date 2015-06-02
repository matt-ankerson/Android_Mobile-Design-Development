// Author: Matt Ankerson
// Date: 23 March 2015

package cameraviaintent.ankem1.bit.ankem1cameraviaintent;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DealWithCamera extends ActionBarActivity
{
    private static final String FILE_PATH_KEY = "filePath";

    Button btnPhotoIntent;
    ImageView ivUL;
    ImageView ivUR;
    ImageView ivML;
    ImageView ivMR;

    // Additional class members
    String mPhotoFileName;
    File mPhotoFile;
    Uri mPhotoFileURI;
    String mPhotoFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_with_camera);

        btnPhotoIntent = (Button)findViewById(R.id.btnPhotoIntent);
        ivUL = (ImageView)findViewById(R.id.ivUL);
        ivUR = (ImageView)findViewById(R.id.ivUR);
        ivML = (ImageView)findViewById(R.id.ivML);
        ivMR = (ImageView)findViewById(R.id.ivMR);

        // Wire up the button click event
        CameraButtonHandler handler = new CameraButtonHandler();
        btnPhotoIntent.setOnClickListener(handler);
/*
        if(savedInstanceState != null)
        {
            mPhotoFilePath = savedInstanceState.getString(FILE_PATH_KEY);
            Toast.makeText(this, mPhotoFilePath, Toast.LENGTH_LONG).show();
        }
*/
    }

/*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        if(mPhotoFile != null)
        {
            savedInstanceState.putString(FILE_PATH_KEY, mPhotoFile.getPath());
            Log.w("file path:", mPhotoFile.getPath());
        }
    }
*/
    // Inner class for button handler
    public class CameraButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            // Create the uniquely named file to hold the image data
            mPhotoFile = createUniqueFile();

            // Generate URI from the File instance
            mPhotoFileURI = Uri.fromFile(mPhotoFile);

            // Create an intent for the image capture content provider
            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Attach our Uri to the Intent
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileURI);

            // Launch the Intent, wait for result.
            // When the user is finished, OnActivityResult will be raised.
            startActivityForResult(imageCaptureIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestId, int resultCode, Intent returnIntent)
    {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //---------------------------
        // Retrieve the requested data

        // Determine appropriate action based on the request ID
        if(requestId == 1)
        {
            // Did the activity return 'Okay'?
            if(resultCode == RESULT_OK)
            {
                // How do we get the file path since we closed the activity???

                // Get the data from the returnIntent, we need the file path for BitmapFactory
                String realFilePath = mPhotoFile.getPath();
                //String realFilePath = mPhotoFilePath;
                //Log.w("file path:", mPhotoFilePath);

                Bitmap userPhoto = BitmapFactory.decodeFile(realFilePath);

                // Assign the image to our ImageViews
                ivUL.setImageBitmap(userPhoto);
                ivUR.setImageBitmap(userPhoto);
                ivML.setImageBitmap(userPhoto);
                ivMR.setImageBitmap(userPhoto);
            }
            else
            {
                Toast.makeText(this, "No photo has been saved", Toast.LENGTH_LONG).show();
            }
        }
    }

    public File createUniqueFile()
    {
        // Get the system image gallery folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        // Make a sub-directory
        File imageStorageDir = new File(imageRootPath, "CameraDemo1");
        if(!imageStorageDir.exists())
        {
            imageStorageDir.mkdirs();
        }

        // Get a timestamp, this will give a guaranteed unique string.
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        // Make file name
        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        File photoFile = new File(imageStorageDir.getPath() + File.separator + mPhotoFileName);

        return photoFile;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deal_with_camera, menu);
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
