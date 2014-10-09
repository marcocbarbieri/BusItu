package br.com.tcc.busitu.model;

import android.database.Cursor;

public class PontoLigacaoBean {
	
	public static final String TABLE_NAME = "ponto_ligacao";
	public static final String COL_ID = "_id";
	public static final String COL_ID_PONTO = "id_ponto";
	public static final String COL_ID_LINHA = "id_linha";
	
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.ponto_ligacao";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.ponto_ligacao";
	
	private int _id;
	private int id_ponto;
	private int id_linha;
	
	public PontoLigacaoBean(final Cursor cursor) {
		this._id = cursor.getInt(0);
		this.id_ponto = cursor.getInt(1);
		this.id_linha = cursor.getInt(2);
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getPonto() {
		return id_ponto;
	}
	public void setPonto(int ponto) {
		this.id_ponto = ponto;
	}
	public int getLinha() {
		return id_linha;
	}
	public void setLinha(int linha) {
		this.id_linha = linha;
	}
	
	

}
