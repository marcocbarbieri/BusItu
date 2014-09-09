package br.com.tcc.busitu.model;

import android.provider.BaseColumns;

public final class BusituContract {
	
	private BusituContract() {};
	
	public static abstract class Busitu implements BaseColumns{
		public static final String TABLE_NAME = "linha";
		//public static final String COLUMN_NAME_ENTRY_ID = "_id";
		public static final String COLUMN_NAME_LINHA = "numero";
		public static final String COLUMN_NAME_ROTA = "rota";
	}
	
}
