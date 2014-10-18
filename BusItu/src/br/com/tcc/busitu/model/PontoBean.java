package br.com.tcc.busitu.model;


import java.io.Serializable;

import android.database.Cursor;

public class PontoBean implements Serializable{

	private static final long serialVersionUID = -3987155123408382212L;
	public static final String TABLE_NAME = "ponto";
	public static final String COL_ID = "_id";
	public static final String COL_ENDERECO = "endereco";
	public static final String COL_LAT = "lat";
	public static final String COL_LONG = "long";
	
	private int _id;
	private String endereco;
	private long lat;
	private long longi;
	
	private Cursor cursor;
	
	
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
