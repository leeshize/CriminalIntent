package com.example.crimeintent;



import java.util.ArrayList;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {
	private ArrayList<Crime> mCrimes;
	private static final int REQUSET_CRIME=1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crimes_title);
		mCrimes=CrimeLab.get(getActivity()).getCrimes();
/*		ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(this.getActivity(),
					layout.simple_list_item_1, 
					mCrimes);
		setListAdapter(adapter);*/
		CrimeAdapter adapter = new CrimeAdapter(mCrimes);
		setListAdapter(adapter);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Crime c=(Crime)(this.getListAdapter()).getItem(position);
		//start activity
		Intent intent=new Intent(getActivity(),CrimePagerActivity.class);
		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
		intent.putExtra(CrimePagerActivity.PAGER, position);
		//startActivity(intent);
		startActivityForResult(intent, REQUSET_CRIME);
	}
	
	private class CrimeAdapter extends ArrayAdapter<Crime>{

		public CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView==null){
				convertView=getActivity().getLayoutInflater().inflate(
						R.layout.list_item_crime, null);				
			}
			
			Crime c=getItem(position);
			TextView titleTextView;
			TextView dateTextView;
			CheckBox solvedCheckBox;
			
			solvedCheckBox=(CheckBox)convertView.findViewById(R.id.crime_list_item_checkbox);
			dateTextView=(TextView)convertView.findViewById(R.id.crime_list_title_dateview);
			titleTextView=(TextView)convertView.findViewById(R.id.crime_list_title_textview);
			
			titleTextView.setText(c.getTitle());
			dateTextView.setText(c.getDate().toString());
			solvedCheckBox.setChecked(c.isSolved());
			
			return convertView;
		}
		
	}
	@Override
	public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
		}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==REQUSET_CRIME){
			//
		}
	}
}