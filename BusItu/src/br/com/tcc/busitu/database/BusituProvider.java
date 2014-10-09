package br.com.tcc.busitu.database;



import br.com.tcc.busitu.model.HorarioBean;
import br.com.tcc.busitu.model.LinhaBean;
import br.com.tcc.busitu.model.PercursoBean;
import br.com.tcc.busitu.model.PontoBean;
import br.com.tcc.busitu.model.PontoLigacaoBean;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class BusituProvider extends ContentProvider {

	public static final String SCHEME = "content://";
	public static final String AUTHORITY = "br.com.tcc.busitu.provider";

/*	public static final String LINHAS = SCHEME + AUTHORITY + "/linha";
	public static final Uri URI_LINHAS = Uri.parse(LINHAS);
	public static final String LINHAS_BASE = LINHAS + "/";
	
	public static final Uri URI_LINHAS_ID = Uri.parse(LINHAS_BASE);*/
	
	
	/**
	 * URI de todas as tabelas
	 */
	public static final String BASE = SCHEME + AUTHORITY + "/";

	public static final Uri URI_LINHA = Uri.parse(BASE + LinhaBean.TABLE_NAME);
	public static final Uri URI_LINHA_ID = Uri.parse(BASE + LinhaBean.TABLE_NAME + "/");
	
	public static final Uri URI_PERCURSO = Uri.parse(BASE + PercursoBean.TABLE_NAME);
	public static final Uri URI_PERCURSO_ID = Uri.parse(BASE + PercursoBean.TABLE_NAME + "/");
	
	public static final Uri URI_PONTO = Uri.parse(BASE + PontoBean.TABLE_NAME);
	public static final Uri URI_PONTO_ID = Uri.parse(BASE + PontoBean.TABLE_NAME + "/");
	
	public static final Uri URI_HORARIO = Uri.parse(BASE + HorarioBean.TABLE_NAME);
	public static final Uri URI_HORARIO_ID = Uri.parse(BASE + HorarioBean.TABLE_NAME + "/");
	
	public static final Uri URI_PONTO_LIG = Uri.parse(BASE + PontoLigacaoBean.TABLE_NAME);
	public static final Uri URI_PONTO_LIG_ID = Uri.parse(BASE + PontoLigacaoBean.TABLE_NAME + "/");
	
	public static final Uri URI_FILTER_RUA = Uri.parse(BASE + "filter_rua" + "/");
	
	
	
	public static final String[] PROJECTION_PERCURSO = { "_id", "nome", "rota", "regiao_atendida", "tempo", "id_linha" };
	
    // The incoming URI matches the Notes URI pattern
    private static final int INT_URI_LINHA = 1;

    // The incoming URI matches the Note ID URI pattern
    private static final int INT_URI_LINHAS = 2;

	
	private static final UriMatcher sUriMatcher;

	static{
		
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // Add a pattern that routes URIs terminated with "notes" to a NOTES operation
/*        sUriMatcher.addURI(AUTHORITY, "linha", INT_URI_LINHA);

        // Add a pattern that routes URIs terminated with "notes" plus an integer
        // to a note ID operation
        sUriMatcher.addURI(AUTHORITY, "linha/#", INT_URI_LINHAS);
        */
        //Novo
       sUriMatcher.addURI(AUTHORITY, LinhaBean.TABLE_NAME, 11);
        sUriMatcher.addURI(AUTHORITY, LinhaBean.TABLE_NAME + "/#", 12);
        
        sUriMatcher.addURI(AUTHORITY, PercursoBean.TABLE_NAME, 21);
        sUriMatcher.addURI(AUTHORITY, PercursoBean.TABLE_NAME + "/#", 22);
        
        sUriMatcher.addURI(AUTHORITY, PontoBean.TABLE_NAME, 31);
        sUriMatcher.addURI(AUTHORITY, PontoBean.TABLE_NAME + "/#", 32);
        
        sUriMatcher.addURI(AUTHORITY, HorarioBean.TABLE_NAME, 41);
        sUriMatcher.addURI(AUTHORITY, HorarioBean.TABLE_NAME + "/#", 42);
        
        sUriMatcher.addURI(AUTHORITY, PontoLigacaoBean.TABLE_NAME, 51);
        sUriMatcher.addURI(AUTHORITY, PontoLigacaoBean.TABLE_NAME + "/#", 52);
        
        sUriMatcher.addURI(AUTHORITY, "filter_rua", 61);
        
	}
	


	public BusituProvider() {

	}

	@Override
	public boolean onCreate() {
		return true;
	}
	
	@Override
	public String getType(Uri uri) {

		/**
		 * Chooses the MIME type based on the incoming URI pattern
		 */
		switch (sUriMatcher.match(uri)) {

/*		case INT_URI_LINHAS:
			return Linha.CONTENT_TYPE;
		case INT_URI_LINHA:
			return Linha.CONTENT_ITEM_TYPE;*/
		case 11:
			return LinhaBean.CONTENT_TYPE;
		case 12:
			return LinhaBean.CONTENT_ITEM_TYPE;
		case 21:
			return PercursoBean.CONTENT_TYPE;
		case 22:
			return PercursoBean.CONTENT_ITEM_TYPE;
		case 31:
			return PontoBean.CONTENT_TYPE;
		case 32:
			return PontoBean.CONTENT_ITEM_TYPE;
		case 41:
			return HorarioBean.CONTENT_TYPE;
		case 42:
			return HorarioBean.CONTENT_ITEM_TYPE;
		case 51:
			return PontoLigacaoBean.CONTENT_TYPE;
		case 52:
			return PontoLigacaoBean.CONTENT_ITEM_TYPE;
		case 61:
			return "vnd.android.cursor.item/vnd.google.filter_rua";

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		

	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor result = null;
		
		switch (sUriMatcher.match(uri)) {
		case 11:
			result = BusituDatabaseHelper
			.getInstance(getContext())
			.getReadableDatabase()
			.query(LinhaBean.TABLE_NAME, LinhaBean.PROJECTION, null, null, null,
					null, null, null);
	result.setNotificationUri(getContext().getContentResolver(),
			URI_LINHA);
			break;
			
		case 12:
			final long id = Long.parseLong(uri.getLastPathSegment());
			result = BusituDatabaseHelper
					.getInstance(getContext())
					.getReadableDatabase()
					.query(PercursoBean.TABLE_NAME, PercursoBean.PROJECTION,
							"id_linha" + " IS ?",
							new String[] { String.valueOf(id) }, null, null,
							null, null);
			result.setNotificationUri(getContext().getContentResolver(),
					URI_LINHA_ID);
			break;
			
		case 61:
			final String item = uri.getLastPathSegment();
			   SQLiteDatabase db = BusituDatabaseHelper.getInstance(getContext()).getReadableDatabase();
			   
			   String query = ""
			   		+ "SELECT "
			   		+ 		"linha.numero_onibus linha.nome, "
			   		+ "FROM linha "
			   		+ "INNER JOIN percurso "
			   		+ "ON linha._id = percurso.id_linha "
			   		+ "AND percurso.rota LIKE '%?%';";
			   
			   db.rawQuery(query, new String[]{item});

		default:
			throw new UnsupportedOperationException("Not yet implemented");
		}
		return result;
		
		/*if (URI_LINHAS.equals(uri)) {
			result = BusituDatabaseHelper
					.getInstance(getContext())
					.getReadableDatabase()
					.query(Linha.TABLE_NAME, Linha.FIELDS, null, null, null,
							null, null, null);
			result.setNotificationUri(getContext().getContentResolver(),
					URI_LINHAS);
		} else if (uri.toString().startsWith(LINHAS_BASE)) {
			final long id = Long.parseLong(uri.getLastPathSegment());
			result = BusituDatabaseHelper
					.getInstance(getContext())
					.getReadableDatabase()
					.query("percurso", PROJECTION_PERCURSO,
							"id_linha" + " IS ?",
							new String[] { String.valueOf(id) }, null, null,
							null, null);
			result.setNotificationUri(getContext().getContentResolver(),
					URI_LINHAS);
		} else {
			throw new UnsupportedOperationException("Not yet implemented");
		}

		return result;*/
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
