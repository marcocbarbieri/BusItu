package br.com.tcc.busitu.controller;

import br.com.tcc.busitu.R;
import br.com.tcc.busitu.database.Linha;
import br.com.tcc.busitu.database.BusituProvider;
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

	public LinhasActivity() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new SimpleCursorAdapter(getActivity(),
				R.layout.linha_listitem, null, new String[] {
						Linha.COL_NUMERO_ONIBUS, Linha.COL_NOME }, new int[] {
						R.id.cardNumero, R.id.cardLinha }, 0));

		// Load the content
		getLoaderManager().initLoader(0, null, new LoaderCallbacks<Cursor>() {
			@Override
			public Loader<Cursor> onCreateLoader(int id, Bundle args) {
				return new CursorLoader(getActivity(),
						BusituProvider.URI_LINHA, Linha.FIELDS, null, null,
						null);
			}

			@Override
			public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
				((SimpleCursorAdapter) getListAdapter()).swapCursor(c);
			}

			@Override
			public void onLoaderReset(Loader<Cursor> arg0) {
				((SimpleCursorAdapter) getListAdapter()).swapCursor(null);
			}
		});
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		// content://br.com.tcc.busitu.provider/linha/2
		Uri uri = ContentUris.withAppendedId(BusituProvider.URI_LINHA_ID, id);
		
		startActivity(new Intent(Intent.ACTION_EDIT, uri));

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_linhas, container, false);
	}

}
