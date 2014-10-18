package br.com.tcc.busitu.model;



import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BusituDatabaseHelper extends SQLiteAssetHelper {

	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "busituDEMO_v1.db";
	
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

}