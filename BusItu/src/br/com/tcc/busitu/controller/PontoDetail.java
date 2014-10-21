package br.com.tcc.busitu.controller;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.PercursoBean;
import br.com.tcc.busitu.model.PercursoDAO;
import br.com.tcc.busitu.util.GPSTracker;

public class PontoDetail extends FragmentActivity {
	
	private static LatLng latlng;
	private static String nome;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ponto_detail);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ponto_detail, menu);
		return true;
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends ListFragment {
		


		public PlaceholderFragment() {
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			

			
			Intent i = getActivity().getIntent();
			nome = i.getStringExtra("paradaNome");
			Bundle b = i.getExtras();
			
			LinhaBean linhaBean = (LinhaBean) b.getSerializable("Linhas");
			latlng = (LatLng) b.get("pontoLocation");
			
			
			Cursor mCursor = linhaBean.getResultado();
			
			setListAdapter(new SimpleCursorAdapter(getActivity(),
					R.layout.linha_listitem, mCursor, new String[] {
							LinhaBean.COL_NUMERO_ONIBUS, LinhaBean.COL_NOME }, new int[] {
							R.id.cardNumero, R.id.cardLinha }, 0));
			
			
		};
		
		
		
		@Override
		public void onListItemClick(android.widget.ListView l, View v, int position, long id) {
			
			Intent i = new Intent();
			Bundle b = new Bundle();
			
			PercursoDAO percursoDAO = new PercursoDAO(getActivity().getApplicationContext());
			PercursoBean percursoBean = new PercursoBean();
			percursoBean = percursoDAO.buscarPercursoPorID(id);
			
			if(percursoBean.getResultado() != null && percursoBean.getResultado().moveToFirst()){
				b.putSerializable("PercursoBean", percursoBean);
				i.putExtras(b);
				i.setClass(getActivity(), LinhasDetail.class);
				startActivity(i);
			}
		};

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_ponto_detail,
					container, false);
			
			TextView txtNome;
			ImageButton btnNavegar;
			
			txtNome = (TextView) rootView.findViewById(R.id.txtNomePonto);
			btnNavegar = (ImageButton) rootView.findViewById(R.id.btnLocalizacao);
			
			txtNome.setText(nome);
			
			
			btnNavegar.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					GPSTracker gps = new GPSTracker(getActivity());

					 
		            // check if GPS enabled     
		            if(gps.canGetLocation()){
		                 
		                double latitude = latlng.latitude;
		                double longitude = latlng.longitude;
		                Location mCurrentLocation = new Location("");
		                mCurrentLocation.setLatitude(latitude);
		                mCurrentLocation.setLongitude(longitude);
		                 
		                // \n is for new line
		                Log.d("gps",latitude + " " + longitude );
//		                Toast.makeText(getActivity().getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
						Intent it = new Intent(getActivity(), MapActivity.class);
						it.putExtra("currentLocation", mCurrentLocation);
						it.putExtra("fromPontoDetail", true);
						startActivity(it);
		            }
		            else {
		                // can't get location
		                // GPS or Network is not enabled
		                // Ask user to enable GPS/network in settings
		                gps.showSettingsAlert();
		            }
					
	
					
					
				}
			});
			
			
			return rootView;
		}
	}
}
