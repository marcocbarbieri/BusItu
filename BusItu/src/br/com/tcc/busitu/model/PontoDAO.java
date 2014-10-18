package br.com.tcc.busitu.model;

import java.io.Serializable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PontoDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7358366011713550763L;
	private static SQLiteDatabase db;
	
	private String BUSCAR_TODOS_PONTOS = ""
			+
			"SELECT _id, enedereco, lat, long FROM ponto";
	
	
	public PontoDAO(Context ctx){
		db = BusituDatabaseHelper.getInstance(ctx).getReadableDatabase();
	}
	
	public Cursor buscarPontos(){
		
		Cursor mResultado = db.query("ponto", new String[]{"_id", "endereco", "lat", "long"}, null, null, null, null, null);
		
		if (mResultado.moveToFirst()) {
			return mResultado;
		} else {
			return null;
		}
		
	}
}
