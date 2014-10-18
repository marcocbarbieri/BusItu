package br.com.tcc.busitu.model;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LinhaDAO {
	private SQLiteDatabase db;
	private Cursor resultado;
	
	private String colunaPartida;
	private String colunaDestino;
	
	//TODO: fazer com que os metodos retornem LinhaBean
	
	private String BUSCA_POR_ROTA = 
			"SELECT " + "linha._id, linha.numero_onibus linha_numero"
			+ ", linha.nome linha_nome, percurso.nome percurso_nome "
			+ "FROM linha " + "INNER JOIN percurso "
			+ "ON linha._id = percurso.id_linha "
			+ "AND percurso.regiao_atendida LIKE ? COLLATE NOCASE";
	
	private String BUSCA_POR_RUA = 
			"SELECT " + "linha._id, linha.numero_onibus linha_numero"
			+ ", linha.nome linha_nome, percurso.nome percurso_nome "
			+ "FROM linha " + "INNER JOIN percurso "
			+ "ON linha._id = percurso.id_linha "
			+ "AND percurso.rota LIKE ? COLLATE NOCASE";
	
//	private String BUSCA_POR_LINHA = ""
//			+ "SELECT "
//			+ "linha._id, linha.numero_onibus linha_numero, linha.nome linha_nome, percurso.nome percurso_nome "
//			+ "FROM "
//			+ "linha, percurso "
//			+ "WHERE linha.numero_onibus LIKE ? COLLATE NOCASE AND linha._id = percurso.id_linha "
//			+ "OR linha.nome LIKE ? COLLATE NOCASE AND linha._id = percurso.id_linha";

	private String BUSCA_POR_LINHA = 	
			"SELECT 	a.rowid _id, "
			+ "	a.numero_onibus linha_numero, "
			+ "	a.nome linha_nome, "
			+ "	percurso.nome percurso_nome "
			+ "FROM "
			+ "	(SELECT rowid, "
			+ "		lines.numero_onibus, "
			+ "		lines.nome "
			+ "	FROM 	lines "
			+ "	WHERE lines MATCH ?) a "
			+ "INNER JOIN percurso ON a.rowid = percurso.id_linha";
	
	

			
	
//			
//	+ "SELECT " + "linha._id, linha.numero_onibus linha_numero"
//	+ ", linha.nome linha_nome "
//	+ "FROM linha "
//	+ "WHERE numero_onibus "
//	+ "LIKE ? OR nome LIKE ?";
	

	public LinhaDAO(Context ctx) {
		db = BusituDatabaseHelper.getInstance(ctx).getReadableDatabase();
	}
	
	
	public boolean buscaTodasLinhas(){
		
		Cursor mResultado = db.query("linha", new String[]{"_id", "nome", "numero_onibus"}, null, null, null, null, null);
		
		if (mResultado.moveToFirst()) {
			setResultado(mResultado);
			return true;
		} else {
			setResultado(null);
			return false;
		}
		
	}

	public boolean buscaLinhaPorBairro(String nome) {

		String[] selectionArgs = new String[] { "%" + nome + "%" };
		Cursor mResultado = db.rawQuery(BUSCA_POR_ROTA, selectionArgs);

		if (mResultado.moveToFirst()) {
			setResultado(mResultado);
			return true;
		} else {
			setResultado(null);
			return false;
		}

	}
	
	public boolean buscaLinhaPorRua(String nome) {

		String[] selectionArgs = new String[] { "%" + nome + "%" };

		Cursor mResultado = db.rawQuery(BUSCA_POR_RUA, selectionArgs);

		if (mResultado.moveToFirst()) {
			setResultado(mResultado);
			return true;
		} else {
			setResultado(null);
			return false;
		}
	}

	public boolean buscarLinhaPorLinha(String nome) {

		String[] selectionArgs = new String[] {nome};

		Cursor mResultado = db.rawQuery(BUSCA_POR_LINHA, selectionArgs);
		Log.d("query", BUSCA_POR_LINHA);

		if (mResultado.moveToFirst()) {
			setResultado(mResultado);
			return true;
		} else {
			setResultado(null);
			return false;
		}

	}
	
	public boolean buscarRota(String partida, String destino, String colunaPartida, String colunaDestino){
		
		String BUSCA_ROTA = 
				"SELECT 	linha._id _id, "
						+ "	linha.numero_onibus linha_numero, "
						+ "	linha.nome linha_nome, "
						+ "	percurso.nome percurso_nome "
						+ "FROM 	linha "
						+ "INNER JOIN percurso ON linha._id = percurso.id_linha "
						+ "AND (percurso." + colunaPartida + " LIKE ? "
						+ "AND percurso." + colunaDestino + " LIKE ? ) COLLATE NOCASE";
		
		String[] selectionArgs = new String[] {"%" + partida + "%", "%" + destino + "%"};
		
		Cursor mResultado = db.rawQuery(BUSCA_ROTA, selectionArgs);
		Log.d("query", BUSCA_ROTA);

		if (mResultado.moveToFirst()) {
			setResultado(mResultado);
			return true;
		} else {
			setResultado(null);
			return false;
		}
	}

	public Cursor getResultado() {
		return resultado;
	}

	public void setResultado(Cursor resultado) {
		this.resultado = resultado;
	}

}
