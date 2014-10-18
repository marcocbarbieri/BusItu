package br.com.tcc.busitu.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
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
//	
//		Intent i = new Intent();
//		Bundle b = new Bundle();
//		
//		
//		LinhaDAO linhaDAO = new LinhaDAO(getActivity().getApplicationContext());
//		LinhaBean linhaBean = new LinhaBean();
//		linhaBean = linhaDAO.buscarLinhaPorPonto(id);
//		
//		if(linhaBean.getResultado() != null && linhaBean.getResultado().moveToFirst()){
//			b.putSerializable("PercursoBean", linhaBean);
//			i.putExtras(b);
//			i.setClass(getActivity(), LinhasDetail.class);
//			startActivity(i);
//		
//		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_paradas, container, false);
		return rootView;
	}
	

}
