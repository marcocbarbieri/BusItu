package br.com.tcc.busitu.controller;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

import br.com.tcc.busitu.R;
import br.com.tcc.busitu.R.layout;
import br.com.tcc.busitu.model.BusituDatabaseHelper;
import br.com.tcc.busitu.util.GPSTracker;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	GPSTracker gps;

	// 
	

	// Título das tabs
	private String[] tabs = { "Navegar", "Linhas", "Paradas" };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.action_map:

			// Toast.makeText(this, "definindo a localização",
			// Toast.LENGTH_LONG).show();
//			if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//					|| locationManager
//							.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//
//				mCurrentLocation = mLocationClient.getLastLocation();
//
//				Toast.makeText(
//						this,
//						"lat: " + mCurrentLocation.getLatitude() + " long: "
//								+ mCurrentLocation.getLongitude(),
//						Toast.LENGTH_LONG).show();
//				Log.i("AndroidRuntime",
//						"latitude: " + mCurrentLocation.getLatitude()
//								+ " Longitude: "
//								+ mCurrentLocation.getLongitude());
//				// Location localLocation =
//				// locationManager.getLastKnownLocation(locationProvider);
//				// Log.i("AndroidRuntime", "latitude: " +
//				// localLocation.getLatitude() + " Longitude: " +
//				// localLocation.getLongitude());
//				
//				Intent it = new Intent(this, MapActivity.class);
//				it.putExtra("currentLocation", mCurrentLocation);
//				startActivity(it);
//
//				return true;
//
//			}
			
// ############			
			gps = new GPSTracker(MainActivity.this);
			 
            // check if GPS enabled     
            if(gps.canGetLocation()){
                 
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                Location mCurrentLocation = new Location(gps.getLocation());
                 
                // \n is for new line
                Log.d("gps",latitude + " " + longitude );
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
				Intent it = new Intent(this, MapActivity.class);
				it.putExtra("currentLocation", mCurrentLocation);
				it.putExtra("fromPontoDetail", false);
				startActivity(it);
            }
            else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }
			
// ########################
			
			
//	        Location destino = new Location(LocationManager.PASSIVE_PROVIDER);
//	        destino.setLatitude(-23.283348);
//	        destino.setLongitude(-47.300993);
//	        
//	        Location partida = gps.getLocation();
//	        Toast.makeText(getApplicationContext(), "Distancia em m:" + partida.distanceTo(destino), Toast.LENGTH_LONG).show();
//	        Log.d("busitu.gps", ": " + partida.distanceTo(destino));
			
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
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());


		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tab_name : tabs) {
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

	protected void onStop() {
		super.onStop();
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