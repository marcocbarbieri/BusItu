package br.com.tcc.busitu.controller;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.PontoBean;
import br.com.tcc.busitu.model.PontoDAO;

public class PontoFragment extends ListFragment {
	
	Cursor mCursor;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PontoDAO pontoDAO = new PontoDAO(getActivity().getApplicationContext());
		PontoBean pontoBean = new PontoBean();
		mCursor = pontoDAO.buscarPontos();
				
		setListAdapter(new SimpleCursorAdapter(getActivity(),
				R.layout.linha_listitem, mCursor, new String[] {
						"_id", "endereco" }, new int[] {
						R.id.cardNumero, R.id.cardLinha }, 0));	
		
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_paradas, container, false);
		return rootView;
	}

}
