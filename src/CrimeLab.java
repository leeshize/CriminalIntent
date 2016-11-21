package com.example.crimeintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {
	private static CrimeLab sCrimeLab;
	private ArrayList<Crime> mCrimes;
	private Context mContext;
	
	private CrimeLab(Context appContext){
		mContext=appContext;
		mCrimes=new ArrayList<Crime>();
		for(int i=0;i<100;i++){
			Crime c = new Crime();
			c.setTitle("Crime #"+i);
			c.setSolved(i%2==0);
			mCrimes.add(c);
		}
	}
	public static CrimeLab get(Context c){
		if(sCrimeLab==null){
			sCrimeLab=new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;		
	}

	public Crime getCrime(UUID id){
		for(Crime c:mCrimes){
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Crime> getCrimes(){
		return mCrimes;
	}
}

