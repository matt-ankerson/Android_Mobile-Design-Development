package bit.ankem1.RippleEffect;

import android.support.v7.app.ActionBarActivity;
import com.skyfishjy.library.RippleBackground;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class RippleActivity extends ActionBarActivity
{
    ImageView ivDuck;
    RippleBackground rippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        ivDuck = (ImageView)findViewById(R.id.centerImage);

        rippleBackground = (RippleBackground)findViewById(R.id.content);


        ivDuck.setOnClickListener(new DuckClickHandler());
    }

    // click handler class for center image
    public class DuckClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            rippleBackground.startRippleAnimation();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ripple, menu);
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
