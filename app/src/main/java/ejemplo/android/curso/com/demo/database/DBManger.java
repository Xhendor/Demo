package ejemplo.android.curso.com.demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by xhendor on 1/28/16.
 */
public class DBManger {


    // Books table name
    public static final String TABLE = "Usuarios";

    // Books Table Columns names
    public static final String KEY_ID = "codigo";
    public static final String KEY_TITLE = "nombre";
    public static final String[] COLUMNS = {KEY_ID,KEY_TITLE};

    public SQLiteDatabase openDatabaseWritable(Context context) {
    //Abrimos la base de datos 'DBUsuarios' en modo escritura
    DBHelper usdbh =
            new DBHelper(context, "DBUsuarios", null, 1);

    SQLiteDatabase db = usdbh.getWritableDatabase();
    return  db;
}

    public SQLiteDatabase openDatabaseReadable(Context context) {
        //Abrimos la base de datos 'DBUsuarios' en modo lectura
        DBHelper usdbh =
                new DBHelper(context, "DBUsuarios", null, 1);

        SQLiteDatabase db = usdbh.getReadableDatabase();
        return  db;
    }










}
