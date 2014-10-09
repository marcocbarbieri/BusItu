package br.com.tcc.busitu.model;

import android.database.Cursor;

public class PontoBean {
	
	public static final String TABLE_NAME = "ponto";
	public static final String COL_ID = "_id";
	public static final String COL_ENDERECO = "endereco";
	public static final String COL_LAT = "lat";
	public static final String COL_LONG = "long";
	
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.ponto";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.ponto";
	
	private int _id;
	private String endereco;
	private long lat;
	private long longi;
	
	public PontoBean(final Cursor cursor) {
		this._id = cursor.getInt(0);
		this.endereco = cursor.getString(1);
		this.lat = cursor.getLong(2);
		this.longi = cursor.getLong(3);
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	public long getLongi() {
		return longi;
	}
	public void setLongi(long longi) {
		this.longi = longi;
	}
	
	
	

}
