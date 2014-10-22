package br.com.tcc.busitu.model;


import java.io.Serializable;

import android.database.Cursor;
import android.location.Location;

public class PontoBean implements Serializable{

	private static final long serialVersionUID = -3987155123408382212L;
	public static final String TABLE_NAME = "ponto";
	public static final String COL_ID = "_id";
	public static final String COL_ENDERECO = "endereco";
	public static final String COL_LAT = "lat";
	public static final String COL_LONG = "long";
	
	private int _id;
	private String endereco;
	private double lat;
	private double lng;
	private Location pontoLocation;
	
	private Cursor cursor;
	
	public PontoBean(Cursor cursor){
		this.cursor = cursor;
	}
	
	public PontoBean(){};
	
	
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
	public Cursor getCursor() {
		return cursor;
	}
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Location getPontoLocation() {
		return pontoLocation;
	}

	public void setPontoLocation(Location pontoLocation) {
		this.pontoLocation = pontoLocation;
	}
	
	
	

}
