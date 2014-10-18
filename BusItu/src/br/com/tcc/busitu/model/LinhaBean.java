package br.com.tcc.busitu.model;

import java.io.Serializable;

import android.database.Cursor;

public class LinhaBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2173234364569646490L;
	
	public static final String TABLE_NAME = "linha";
	public static final String COL_ID = "_id";
	public static final String COL_NOME = "nome";
	public static final String COL_NUMERO_ONIBUS = "numero_onibus";
	
	public static final String[] PROJECTION = new String[] { COL_ID + ", " + COL_NUMERO_ONIBUS + ", " + COL_NOME + ", " + COL_NUMERO_ONIBUS };
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.linha";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.linha";
	
	private static Cursor resultado = null;
	

	private int _id;
	private String nome;
	private int numeroOnibus;
	
	
	public LinhaBean(){
		
	}
	
	public LinhaBean(Cursor cursor){
		this.resultado = cursor;
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

	public Cursor getResultado() {
		return resultado;
	}

	public void setResultado(Cursor resultado) {
		LinhaBean.resultado = resultado;
	}
	

}
