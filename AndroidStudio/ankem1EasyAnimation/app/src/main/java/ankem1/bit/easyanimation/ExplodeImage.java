package ankem1.bit.easyanimation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.ExplodeAnimation;


public class ExplodeImage extends ActionBarActivity
{
    ImageView ivGoose;
    Button btnAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explode_image);

        ivGoose = (ImageView)findViewById(R.id.ivGoose);
        btnAnimate = (Button)findViewById(R.id.btnAnimate);
        btnAnimate.setOnClickListener(new AnimateButtonHandler());
    }

    // handler for the animate button
    public class AnimateButtonHandler implements View.OnClickListener
    {
        // when the button is clicked
        @Override
        public void onClick(View v)
        {
            ExplodeAnimation animation = new ExplodeAnimation(ivGoose);

            animation.setExplodeMatrix(ExplodeAnimation.MATRIX_2X2);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(500);
            animation.setListener(new AnimationListener() {
                        @Override
                        public void onAnimationEnd(Animation animation)
                        {
                            // perform actions when animations finishes
                        }
                    });
            animation.animate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_explode_image, menu);
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
