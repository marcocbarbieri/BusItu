package br.com.tcc.busitu.model;



import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BusituDatabaseHelper extends SQLiteAssetHelper {

	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "busituDEMO_V04.db";
	
	private static BusituDatabaseHelper singleton;
	
	
	public static BusituDatabaseHelper getInstance(final Context context) {
		if (singleton == null) {
			singleton = new BusituDatabaseHelper(context);
		}
		return singleton;
	}

	public BusituDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
		
/*	public synchronized List<LinhaBean> getLinhas(){
		
		final SQLiteDatabase db = this.getReadableDatabase();
		final Cursor cursor = db.query(LinhaBean.TABLE_NAME, null, null, null, null, null, null);
		List<LinhaBean> linhas =  new ArrayList();
		
		if(cursor.equals(null)){
			linhas = null;
		}
		else if(cursor.moveToFirst()){
			linhas.add(new LinhaBean(cursor));
			while(cursor.moveToNext()){
				linhas.add(new LinhaBean(cursor));
			}
		}
		db.close();
		return linhas;
}*/
}