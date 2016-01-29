package ejemplo.android.curso.com.demo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ejemplo.android.curso.com.demo.database.DBManger;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DBManger dbManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         dbManger=new DBManger();


    }


    public void openReadable(){

    db=dbManger.openDatabaseReadable(getApplicationContext());

}
    public void openWritable(View view){

        db=dbManger.openDatabaseWritable(getApplicationContext());

    }


    public void writeDatabase(View view){
        openWritable(null);
        //Si hemos abierto correctamente la base de datos
        if(db != null)
        if(!db.isReadOnly())
        {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos
                int codigo = i;
                String nombre = "Usuario" + i;

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
                        "VALUES (" + codigo + ", '" + nombre +"')");
            }

            //Cerramos la base de datos
            db.close();
        }



    }



    public String readByCodigo(int id){

        Cursor cursor =
                db.query(DBManger.TABLE, // a. table
                        DBManger.COLUMNS, // b. column names
                        " codigo = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // Si tenenemos resultado revisamos el primero
        if (cursor != null)
            cursor.moveToFirst();


       return "Codigo:"+cursor.getInt(0)+" Nombre:"+cursor.getString(1);

    }



    public String readAll(View view){

        openReadable();
        StringBuilder stringBuilder=new StringBuilder();


        // 1. Construir query
        String query = "SELECT  * FROM " + DBManger.TABLE;

        // 2. Obtener referencia del cursor de la base de dato
        Cursor cursor = db.rawQuery(query, null);

        // 3. obtener cada renglon
        if (cursor.moveToFirst()) {
            do {
                stringBuilder.append("Codigo:" + cursor.getInt(0) + " Nombre:" + cursor.getString(1));

            } while (cursor.moveToNext());
        }


        System.err.println(stringBuilder.toString());



        return stringBuilder.toString();
    }







}
