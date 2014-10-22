package br.com.tcc.busitu.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.PontoBean;
import br.com.tcc.busitu.model.PontoDAO;
import br.com.tcc.busitu.util.GPSTracker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



/**
 * @see http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/
 * 
 * @author marco
 *
 */
public class MapActivity extends Activity {
	
	  private GoogleMap map;
	  private GPSTracker gps;
	  private PontoDAO ponto;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_map);
	    
	    Bundle extras = getIntent().getExtras();
	    String nome="";
	    boolean isFromPontodetail = extras.getBoolean("fromPontoDetail");
	    Location mCurrentLocation = (Location) extras.get("currentLocation");
	    
	    double latitude =  mCurrentLocation.getLatitude();
	    double longitude = mCurrentLocation.getLongitude();
	    LatLng mLocation = new LatLng(latitude, longitude);
	    
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
		        .getMap();

	    
	    if(isFromPontodetail){
	    	
	    	nome = extras.getString("pontoNome");
	    	
	    	addBusMarker(mLocation, nome);
	    	
	    }else {
	    	
		    ponto = new PontoDAO(getApplicationContext());
		    gps = new GPSTracker(getApplicationContext());
		    
		    ArrayList<PontoBean> locationList = new ArrayList<PontoBean>();
		    
		    locationList = gps.getLocationList(ponto.buscarPontos());
		    
		    addMyLocationMarker(mLocation);
		    
		    for (PontoBean location : locationList) {
				double lat = location.getLat();
				double longi = location.getLng();
				nome = location.getEndereco();
				LatLng mLatLong = new LatLng(lat, longi);
				addBusMarker(mLatLong, nome);
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
	  
	  public void addBusMarker(LatLng latLong, String busStopName){
		  
			  MarkerOptions busStopMarkerOptions = new MarkerOptions();
			  busStopMarkerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_geolocation_busstop));
			  busStopMarkerOptions.position(latLong);
			  busStopMarkerOptions.title("Ponto de parada");
			  busStopMarkerOptions.snippet(busStopName);
			  map.addMarker(busStopMarkerOptions);
			  

	  }
	  
	  public void addMyLocationMarker(LatLng latlng){
		  
		  MarkerOptions myLocationMarkerOptions = new MarkerOptions();
		  myLocationMarkerOptions.position(latlng);
		  myLocationMarkerOptions.title("Você está aqui");
		  map.addMarker(myLocationMarkerOptions);
		  
	  }

	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    //getMenuInflater().inflate(R.menu.fragment_map, menu);
	    return true;
	  }

}
