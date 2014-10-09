package br.com.tcc.busitu.model;

import android.database.Cursor;

public class HorarioBean {
	
	public static final String TABLE_NAME = "horario";
	
	public static final String COL_ID = "_id";
	public static final String COL_HORARIO_INICIAL = "horario_inicial";
	public static final String COL_HORARIO_FINAL = "horario_final";
	public static final String COL_CATEGORIA = "categoria";
	public static final String COL_ID_LINHA = "id_linha";
	
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.horario";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.horario";
	
	private int _id;
	private String horarioInicial;
	private String horarioFinal;
	private int categoria;
	private int id_linha;

	public HorarioBean(final Cursor cursor) {
		this._id = cursor.getInt(0);
		this.horarioInicial = cursor.getString(1);
		this.horarioFinal = cursor.getString(2);
		this.categoria = cursor.getInt(3);
		this.id_linha = cursor.getInt(4);
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getHorarioInicial() {
		return horarioInicial;
	}
	public void setHorarioInicial(String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}
	public String getHorarioFinal() {
		return horarioFinal;
	}
	public void setHorarioFinal(String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getLinha() {
		return id_linha;
	}
	public void setLinha(int linha) {
		this.id_linha = linha;
	}
	
	

}
