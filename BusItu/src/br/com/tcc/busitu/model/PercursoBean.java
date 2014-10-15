package br.com.tcc.busitu.model;

import java.io.Serializable;

import android.database.Cursor;

public class PercursoBean implements Serializable{
	
	private static final long serialVersionUID = 4392063217469129025L;
	
	public static final String TABLE_NAME = "percurso";
	public static final String COL_ID = "_id";
	public static final String COL_NOME = "nome";
	public static final String COL_ROTA = "rota";
	public static final String COL_REGIAO_ATENDIDA = "regiao_atendida";
	public static final String COL_TEMPO_PERCURSO = "tempo_percurso";
	public static final String COL_ID_LINHA = "id_linha";
	
	private static Cursor resultado;
	
	public static final String[] COLUMNS = new String[] { COL_ID + ", " + COL_NOME + ", " + COL_ROTA + ", " 
	+ COL_REGIAO_ATENDIDA + ", " + COL_TEMPO_PERCURSO + ", " + COL_ID_LINHA};
	
	
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.percurso";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.percurso";
	
	private int _id;
	private String nome;
	private String rota;
	private String regiaoAtendida;
	private String tempoPercurso;
	private int id_linha;
		
	public PercursoBean() {
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
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}
	public String getRegiaoAtendida() {
		return regiaoAtendida;
	}
	public void setRegiaoAtendida(String regiaoAtendida) {
		this.regiaoAtendida = regiaoAtendida;
	}
	public String getTempoPercurso() {
		return tempoPercurso;
	}
	public void setTempoPercurso(String tempoPercurso) {
		this.tempoPercurso = tempoPercurso;
	}
	public int getLinha() {
		return id_linha;
	}
	public void setLinha(int linha) {
		this.id_linha = linha;
	}

	public Cursor getResultado() {
		return resultado;
	}

	public void setResultado(Cursor resultado) {
		this.resultado = resultado;
	}
	
	

}
