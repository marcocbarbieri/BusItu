package br.com.tcc.busitu.database;



import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class LinhaProvider extends ContentProvider {

	public static final String AUTHORITY = "br.com.tcc.busitu.provider";
	public static final String SCHEME = "content://";

	public static final String LINHAS = SCHEME + AUTHORITY + "/linha";
	public static final Uri URI_LINHAS = Uri.parse(LINHAS);
	public static final String LINHAS_BASE = LINHAS + "/";
	
	public static final Uri URI_LINHAS_ID = Uri.parse(LINHAS_BASE);
	
	public static final String[] PROJECTION_PERCURSO = { "_id", "nome", "rota", "regiao_atendida", "tempo", "id_linha" };
	
    // The incoming URI matches the Notes URI pattern
    private static final int INT_URI_LINHA = 1;

    // The incoming URI matches the Note ID URI pattern
    private static final int INT_URI_LINHAS = 2;

	
	private static final UriMatcher sUriMatcher;

	static{
		
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // Add a pattern that routes URIs terminated with "notes" to a NOTES operation
        sUriMatcher.addURI(AUTHORITY, "linha", INT_URI_LINHA);

        // Add a pattern that routes URIs terminated with "notes" plus an integer
        // to a note ID operation
        sUriMatcher.addURI(AUTHORITY, "linha/#", INT_URI_LINHAS);
		
	}
	


	public LinhaProvider() {

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

		// If the pattern is for notes or live folders, returns the general
		// content type.
		case INT_URI_LINHAS:
			return Linha.CONTENT_TYPE;

			// If the pattern is for note IDs, returns the note ID content type.
		case INT_URI_LINHA:
			return Linha.CONTENT_ITEM_TYPE;

			// If the URI pattern doesn't match any permitted patterns, throws
			// an exception.
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor result = null;
		if (URI_LINHAS.equals(uri)) {
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

		return result;
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
