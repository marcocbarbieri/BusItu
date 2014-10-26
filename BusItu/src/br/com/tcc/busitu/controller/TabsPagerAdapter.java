package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.EditText;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		switch(index){
		case 0:
			return new NavegarActivity();
		case 1:
			return new LinhasActivity();
		case 2:
			return new PontoActivity();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
