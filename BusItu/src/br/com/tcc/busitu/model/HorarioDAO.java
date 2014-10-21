package br.com.tcc.busitu.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HorarioDAO {	
	
	private SQLiteDatabase db;
	
	
	String BUSCA_HORARIO_POR_LINHA = ""
		+ "SELECT "
		+ "  horario._id, "
		+ "  horario.horario_inicial, "
		+ "  horario.horario_final, "
		+ "  horario.categoria "
		+ "FROM "
		+ "  linha, "
		+ "  horario "
		+ "WHERE "
		+ "  linha._id = horario.id_linha AND "
		+ "  linha._id = ?";
	
	
	public HorarioDAO(Context ctx){
		db = BusituDatabaseHelper.getInstance(ctx).getReadableDatabase();
	}
	
	public HorarioBean buscaHorarioPorLinha(Long id){
		
		String[] selectionArgs = new String[] {String.valueOf(id)};
		
		Cursor mResultado = db.rawQuery(BUSCA_HORARIO_POR_LINHA, selectionArgs);
		
		if(mResultado.moveToFirst()){
			return new HorarioBean(mResultado);
		} else {
			return null;
		}
		
	}
	
	

}
