package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;
import br.com.tcc.busitu.R.layout;
import br.com.tcc.busitu.model.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	

	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private LocationManager locationManager;
	private TextView txtDestino;
	String locationProvider;
	
	
	
	//Título das tabs
	private String[] tabs = {"Navegar", "Linhas", "Favoritos"};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		
		switch (item.getItemId()){
		
		case R.id.action_map:
			
			Toast.makeText(this, "definindo a localização", Toast.LENGTH_LONG).show();
			Location localLocation = locationManager.getLastKnownLocation(locationProvider);
			Log.i("AndroidRuntime", "latitude: " + localLocation.getLatitude() + " Longitude: " + localLocation.getLongitude());
			
			
			
			
			return true;
		case R.id.action_preferences:
			return true;
		case R.id.action_search:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		
		}
		
		};
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	//	Context context = this.getApplicationContext();
		
		locationManager = (LocationManager) this.getSystemService(getApplicationContext().LOCATION_SERVICE);
		locationProvider = LocationManager.GPS_PROVIDER;
		
/*		
		LocationListener locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				location.getLatitude();
				location.getLongitude();
				
				
			}
		};
		
		locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);*/
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		
		txtDestino = (TextView) findViewById(R.id.textFim);
		

		
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		for(String tab_name : tabs){
			actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
		}
		
		
		 /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
	}
	

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	
	



   
}