package br.com.tcc.busitu.model;

import java.io.Serializable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class PercursoDAO{

	
	private SQLiteDatabase db;
	private Cursor resultado;
	
	public PercursoDAO(Context ctx){
		db = BusituDatabaseHelper.getInstance(ctx).getReadableDatabase();
	}
	
	
	public PercursoBean buscarPercursoPorID(long id){
		PercursoBean percursoBean = new PercursoBean();
		Cursor mCursor = db.query(
				PercursoBean.TABLE_NAME, PercursoBean.COLUMNS,
				"id_linha" + " IS ?",
				new String[] { String.valueOf(id) }, null, null,
				null, null);
		
		if(mCursor.moveToFirst()){
			percursoBean.setResultado(mCursor);
			percursoBean.setLinha(id);
			return percursoBean;
		}
		else {
			percursoBean.setResultado(null);
			return percursoBean;
		}
	}

}
