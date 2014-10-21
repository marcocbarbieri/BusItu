package br.com.tcc.busitu.controller;

import com.google.android.gms.maps.model.LatLng;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.LinhaDAO;
import br.com.tcc.busitu.model.PontoDAO;

public class PontoFragment extends ListFragment {
	
	Cursor mCursor;
	PontoDAO pontoDAO;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PontoDAO pontoDAO = new PontoDAO(getActivity().getApplicationContext());
		mCursor = pontoDAO.buscarPontos();
				
		
		
		setListAdapter(new SimpleCursorAdapter(getActivity(),
				R.layout.linha_listitem, mCursor, new String[] {
						"_id", "endereco" }, new int[] {
						R.id.cardNumero, R.id.cardLinha }, 0));	
		
	}
	
	@Override
	public void onListItemClick(android.widget.ListView l, View v, int position, long id) {
	
		Intent i = new Intent();
		Bundle b = new Bundle();
		
		
		LinhaDAO linhaDAO = new LinhaDAO(getActivity().getApplicationContext());
		LinhaBean linhaBean = new LinhaBean();
		linhaBean = linhaDAO.buscarLinhaPorPonto(id);
		
	    Cursor cursor = ((SimpleCursorAdapter)l.getAdapter()).getCursor();
	    cursor.moveToPosition(position);

	    String nome = cursor.getString(cursor.getColumnIndex("endereco"));
	    double latitude = cursor.getDouble(cursor.getColumnIndex("lat"));
	    double longitude = cursor.getDouble(cursor.getColumnIndex("long"));
	    
	    LatLng mLocation = new LatLng(latitude, longitude); 
	    
		
		if(linhaBean.getResultado() != null && linhaBean.getResultado().moveToFirst()){
			b.putSerializable("Linhas", linhaBean);
			i.putExtras(b);
			i.putExtra("paradaNome", nome);
			i.putExtra("pontoLocation", mLocation);
			i.setClass(getActivity(), PontoDetail.class);
			startActivity(i);
		
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_paradas, container, false);
		return rootView;
	}
	

}
