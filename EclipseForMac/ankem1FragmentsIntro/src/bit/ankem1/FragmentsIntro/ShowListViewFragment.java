package bit.ankem1.FragmentsIntro;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowListViewFragment extends Fragment
{
	ListView lvItems;

	public ShowListViewFragment() 
	{
		
	}
	
	// On Create View
	@Override
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflator.inflate(R.layout.show_listview_fragment, container, false);
		
		// Populate the listview
		lvItems = (ListView)v.findViewById(R.id.lvItems);
		
		Resources resources = getResources();
		String[] items = resources.getStringArray(R.array.Items);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
		
		lvItems.setAdapter(adapter);
		
		return v;
	}

}
