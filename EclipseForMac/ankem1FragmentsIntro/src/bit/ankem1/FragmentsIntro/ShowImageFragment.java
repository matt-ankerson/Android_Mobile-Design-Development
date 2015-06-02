package bit.ankem1.FragmentsIntro;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShowImageFragment extends Fragment
{

	public ShowImageFragment() 
	{
		// TODO Auto-generated constructor stub
	}
	
	// On Create View
	@Override
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflator.inflate(R.layout.show_image_fragment, container, false);
		
		// Do any required setup for the fragment here
		
		return v;
	}

}
