package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;

public class MapActivity extends Activity {
	
	  static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	  private GoogleMap map;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_map);
	    Location mCurrentLocation;
	    Bundle extras = getIntent().getExtras();
	    mCurrentLocation = (Location) extras.get("currentLocation");
	    double latitude =  mCurrentLocation.getLatitude();
	    double longitude = mCurrentLocation.getLongitude();
	    LatLng mLocation = new LatLng(latitude, longitude);
	    
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();
	    Marker markerMyLocation = map.addMarker(new MarkerOptions().position(mLocation)
	        .title("Minha Localização"));
	    
	    Marker kiel = map.addMarker(new MarkerOptions()
	        .position(KIEL)
	        .title("Kiel")
	        .snippet("Kiel is cool")
	        .icon(BitmapDescriptorFactory
	            .fromResource(R.drawable.ic_launcher)));
	    
	   
	    

	    // Move the camera instantly to hamburg with a zoom of 15.
	    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
	    map.animateCamera(CameraUpdateFactory.newLatLngZoom(mLocation, 15));

	    // Zoom in, animating the camera.
//	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	  }

	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    //getMenuInflater().inflate(R.menu.fragment_map, menu);
	    return true;
	  }

}
