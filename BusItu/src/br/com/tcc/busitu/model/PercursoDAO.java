package br.com.tcc.busitu.model;

import java.io.Serializable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class PercursoDAO{

	private static final long serialVersionUID = -7211178771326986629L;
	
	private SQLiteDatabase db;
	private Cursor resultado;
	
	public PercursoDAO(Context ctx){
		db = BusituDatabaseHelper.getInstance(ctx).getReadableDatabase();
	}
	
	
	public PercursoBean buscarPercursoPorID(Long id){
		PercursoBean percursoBean = new PercursoBean();
		Cursor mCursor = db.query(
				PercursoBean.TABLE_NAME, PercursoBean.COLUMNS,
				"id_linha" + " IS ?",
				new String[] { String.valueOf(id) }, null, null,
				null, null);
		
		if(mCursor.moveToFirst()){
			percursoBean.setResultado(mCursor);
			return percursoBean;
		}
		else {
			percursoBean.setResultado(null);
			return percursoBean;
		}
	}

}
