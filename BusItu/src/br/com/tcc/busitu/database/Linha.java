package br.com.tcc.busitu.database;

import android.database.Cursor;

public class Linha {

	public static final String TABLE_NAME = "linha";

	public static final String COL_ID = "_id";
	public static final String COL_NUMERO_ONIBUS = "numero_onibus";
	public static final String COL_NOME = "nome";
	public static final String COL_ID_HORARIO = "id_horario";
	
    /**
     * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
     */
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.linha";

    /**
     * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
     * note.
     */
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.linha";


	public long id = -1;
	public int numero;
	public String nome = "";
	public int id_horario;

	public static final String[] FIELDS = { COL_ID, COL_NUMERO_ONIBUS,
			COL_NOME, COL_ID_HORARIO };

	public Linha() {
	}
	
	public Linha(final Cursor cursor){
		this.id = cursor.getLong(0);
		this.numero = cursor.getInt(1);
		this.nome = cursor.getString(2);
		this.id_horario = cursor.getInt(3);
	}

}
