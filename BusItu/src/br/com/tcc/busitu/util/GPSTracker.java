package br.com.tcc.busitu.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.tcc.busitu.model.PontoBean;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener{
	
	private final Context mContext;
	 
    // flag for GPS status
    boolean isGPSEnabled = false;
 
    // flag for network status
    boolean isNetworkEnabled = false;
 
    // flag for GPS status
    boolean canGetLocation = false;
 
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
 
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
 
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
 
    // Declaring a Location Manager
    protected LocationManager locationManager;
 
    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }
    
	public ArrayList<PontoBean> getLocationList(Cursor pontoLocations) {
		// Se a lista de pontos do banco não for nula
		if (pontoLocations.moveToFirst()) {
			Location minhaLocalizacao = getLocation();
			ArrayList<PontoBean> pontoLocationList = new ArrayList<PontoBean>();
			do {
				// Recupere os dados o banco
				double pontoLat = pontoLocations.getDouble(pontoLocations.getColumnIndex((PontoBean.COL_LAT)));
				double pontoLng = pontoLocations.getDouble(pontoLocations.getColumnIndex((PontoBean.COL_LONG)));
				String pontoEnd = pontoLocations.getString(pontoLocations.getColumnIndex((PontoBean.COL_ENDERECO)));
				int pontoID = pontoLocations.getInt(pontoLocations.getColumnIndex(PontoBean.COL_ID));

				// Crie um novo objeto localizacao para armzenar meu ponto a ser comparado
				Location pontoLocation = new Location("");
				pontoLocation.setLongitude(pontoLng);
				pontoLocation.setLatitude(pontoLat);

				// Se a localizacao do ponto for menor ou igual que 500 metros, armazene no ArrayList
				if ((int) pontoLocation.distanceTo(minhaLocalizacao) <= 500) {
					PontoBean pontoBean = new PontoBean();
					pontoBean.setLat(pontoLat);
					pontoBean.setLng(pontoLng);
					pontoBean.setPontoLocation(pontoLocation);
					pontoBean.setEndereco(pontoEnd);
					pontoBean.set_id(pontoID);
					pontoLocationList.add(pontoBean);

				}
			} while (pontoLocations.moveToNext());
			return pontoLocationList;
		}
		return new ArrayList<PontoBean>();
	}
 
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
 
            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
 
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
 
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 

        
        return location;
    }
     
    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }       
    }
     
    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }
         
        // return latitude
        return latitude;
    }
     
    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
         
        // return longitude
        return longitude;
    }
     
    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }
     
    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     * */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
      
        // Setting Dialog Title
        alertDialog.setTitle("Configuração de GPS");
  
        // Setting Dialog Message
        alertDialog.setMessage("O GPS não está habilitado. Você gostaria de ir para o menu de configurações?");
  
        // On pressing Settings button
        alertDialog.setPositiveButton("Configurações", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
  
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
  
        // Showing Alert Message
        alertDialog.show();
    }
 
    @Override
    public void onLocationChanged(Location location) {
    }
 
    @Override
    public void onProviderDisabled(String provider) {
    }
 
    @Override
    public void onProviderEnabled(String provider) {
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
 
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
