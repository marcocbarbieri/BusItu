package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;
import br.com.tcc.busitu.R.id;
import br.com.tcc.busitu.R.layout;
import br.com.tcc.busitu.R.menu;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.PercursoBean;
import br.com.tcc.busitu.model.PercursoDAO;
import android.app.Activity;
import android.app.ActionBar;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;

public class LinhasResultActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linhas_result_fragment);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.linhas_result, menu);
	// return true;
	// }

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
			Bundle b = i.getExtras();
			LinhaBean l = (LinhaBean) b.getSerializable("LinhaBean");
			Cursor c = l.getResultado();

			setListAdapter(new SimpleCursorAdapter(getActivity(),
					R.layout.fragment_linha_result_listitem, c, new String[] {
							"linha_numero", "linha_nome",
							"percurso_nome" }, 
							new int[] { R.id.cardNumeroResult, 
							R.id.cardLinhaResult, R.id.cardNomeRotaResult }, 0));
		};

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_linhas_result,
					container, false);
			return rootView;
		}
	
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {

			Intent i = new Intent();
			Bundle b = new Bundle();
			
			PercursoDAO percursoDAO = new PercursoDAO(getActivity().getApplicationContext());
			PercursoBean percursoBean = new PercursoBean();
			percursoBean = percursoDAO.buscarPercursoPorID(id);
			
			if(percursoBean.getResultado() != null && percursoBean.getResultado().moveToFirst()){
				b.putSerializable("PercursoBean", percursoBean);
				i.putExtras(b);
				i.setClass(getActivity(), LinhasDetailActivity.class);
				startActivity(i);
			}
			

		};
		
		
	}
}
