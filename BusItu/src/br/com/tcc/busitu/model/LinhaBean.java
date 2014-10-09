package br.com.tcc.busitu.model;

import android.database.Cursor;

public class LinhaBean {
	
	public static final String TABLE_NAME = "linha";
	public static final String COL_ID = "_id";
	public static final String COL_NOME = "nome";
	public static final String COL_NUMERO_ONIBUS = "numero_onibus";
	
	public static final String[] PROJECTION = new String[] { COL_ID + ", " + COL_NUMERO_ONIBUS + ", " + COL_NOME + ", " + COL_NUMERO_ONIBUS };
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.linha";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.linha";
	
	private int _id;
	private String nome;
	private int numeroOnibus;

	public LinhaBean(final Cursor cursor) {
		this._id = cursor.getInt(0);
		this.nome = cursor.getString(1);
		this.numeroOnibus = cursor.getInt(2);
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumeroOnibus() {
		return numeroOnibus;
	}
	public void setNumeroOnibus(int numeroOnibus) {
		this.numeroOnibus = numeroOnibus;
	}
	

}
