package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.LinhaDAO;
import br.com.tcc.busitu.model.PercursoBean;
import br.com.tcc.busitu.model.PercursoDAO;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LinhasActivity extends ListFragment {

	private Cursor mCursor;
	private PercursoDAO percursoDAO;

	public LinhasActivity() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		LinhaDAO linhaDAO = new LinhaDAO(getActivity().getApplicationContext());
		LinhaBean linhaBean = new LinhaBean();
		
		if(linhaDAO.buscaTodasLinhas()){
			linhaBean.setResultado(linhaDAO.getResultado());
			mCursor = linhaBean.getResultado();
		}
		
		setListAdapter(new SimpleCursorAdapter(getActivity(),
				R.layout.linha_listitem, mCursor, new String[] {
						LinhaBean.COL_NUMERO_ONIBUS, LinhaBean.COL_NOME }, new int[] {
						R.id.cardNumero, R.id.cardLinha }, 0));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		
		Intent i = new Intent();
		Bundle b = new Bundle();
		
		percursoDAO = new PercursoDAO(getActivity().getApplicationContext());
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

		return inflater.inflate(R.layout.fragment_linhas, container, false);
	}

}
