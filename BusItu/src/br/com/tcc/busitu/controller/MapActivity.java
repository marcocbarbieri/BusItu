package br.com.tcc.busitu.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.PontoDAO;
import br.com.tcc.busitu.util.GPSTracker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {
	
	  private GoogleMap map;
	  private GPSTracker gps;
	  private PontoDAO ponto;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_map);
	    
	    Bundle extras = getIntent().getExtras();
	    
	    boolean isFromPontodetail = extras.getBoolean("fromPontoDetail");
	    Location mCurrentLocation = (Location) extras.get("currentLocation");
	    
	    double latitude =  mCurrentLocation.getLatitude();
	    double longitude = mCurrentLocation.getLongitude();
	    LatLng mLocation = new LatLng(latitude, longitude);
	    
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
		        .getMap();

	    
	    if(isFromPontodetail){
	    	
	    	addMarker(mLocation);
	    	
	    }else {
	    	
		    ponto = new PontoDAO(getApplicationContext());
		    gps = new GPSTracker(getApplicationContext());
		    
		    ArrayList<Location> locationList = new ArrayList<Location>();
		    
		    locationList = gps.getLocationList(ponto.buscarPontos());
		    
		    Marker markerMyLocation = map.addMarker(new MarkerOptions().position(mLocation)
		        .title("Minha Localização"));
		    
		    for (Location location : locationList) {
				double lat = location.getLatitude();
				double longi = location.getLongitude();
				LatLng mLatLong = new LatLng(lat, longi);
				addMarker(mLatLong);
		    }
	    	
	    }
	    
	    
	    
	    
//	    Marker kiel = map.addMarker(new MarkerOptions()
//	        .position(KIEL)
//	        .title("Kiel")
//	        .snippet("Kiel is cool")
//	        .icon(BitmapDescriptorFactory
//	            .fromResource(R.drawable.ic_launcher)));
	    
	   
	    

	    // Move the camera instantly to hamburg with a zoom of 15.
	    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
	    map.animateCamera(CameraUpdateFactory.newLatLngZoom(mLocation, 15));

	    // Zoom in, animating the camera.
//	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	  }
	  
	  public void addMarker(LatLng latLong){
		  map.addMarker(new MarkerOptions().position(latLong)
				  .title("Ponto de parada"));
	  }

	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    //getMenuInflater().inflate(R.menu.fragment_map, menu);
	    return true;
	  }

}
