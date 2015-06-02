package bit.ankem1.standup;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class StandUpActivity extends ActionBarActivity
{
    final int ANIMATION_DURATION = 700;
    ImageView ivSloth;
    Button btnAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_up);

        ivSloth = (ImageView)findViewById(R.id.ivSloth);
        btnAnimate = (Button)findViewById(R.id.btnAnimate);

        btnAnimate.setOnClickListener(new AnimateBtnHandler());
    }

    // inner class for button handler
    public class AnimateBtnHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            YoYo.with(Techniques.StandUp).duration(ANIMATION_DURATION).playOn(ivSloth);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stand_up, menu);
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
