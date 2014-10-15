package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;
import br.com.tcc.busitu.R.id;
import br.com.tcc.busitu.R.layout;
import br.com.tcc.busitu.R.menu;
import br.com.tcc.busitu.database.Linha;
import br.com.tcc.busitu.database.BusituProvider;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.PercursoBean;
import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class LinhasDetail extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linhas_detail);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
////		getMenuInflater().inflate(R.menu.linhas_detail, menu);
////		return true;
//	}

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

		//public static final String[] PROJECTION = { "_id", "nome", "rota", "regiao_atendida", "tempo", "id_linha"};
		public static final String[] PROJECTION = {PercursoBean.COL_NOME, PercursoBean.COL_REGIAO_ATENDIDA, PercursoBean.COL_ROTA,
			PercursoBean.COL_TEMPO_PERCURSO };
		
		public PlaceholderFragment() {
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			Intent i = getActivity().getIntent();
			Bundle b = i.getExtras();
			
			PercursoBean percursoBean = (PercursoBean) b.getSerializable("PercursoBean");
			
			Cursor mCursor = percursoBean.getResultado();
			
			setListAdapter(new SimpleCursorAdapter(getActivity(),
					R.layout.percurso_listitem, mCursor,
					PROJECTION, new int[] {
							R.id.cardNome, R.id.cardRegiaoAtendida,
							R.id.cardRota, R.id.tempo }, 0));

//			// Load the content
//			getLoaderManager().initLoader(0, null,
//					new LoaderCallbacks<Cursor>() {
//						@Override
//						public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//							return new CursorLoader(getActivity(), uri,
//									PROJECTION, null,
//									null, null);
//						}
//
//						@Override
//						public void onLoadFinished(Loader<Cursor> loader,
//								Cursor c) {
//							((SimpleCursorAdapter) getListAdapter())
//									.swapCursor(c);
//						}
//
//						@Override
//						public void onLoaderReset(Loader<Cursor> arg0) {
//							((SimpleCursorAdapter) getListAdapter())
//									.swapCursor(null);
//						}
//					});
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_linhas_detail,
					container, false);
			return rootView;
		}
	}
}
