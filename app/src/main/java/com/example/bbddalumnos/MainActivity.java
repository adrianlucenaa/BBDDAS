package com.example.bbddalumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText met_codigo, met_nombre, met_nota1, met_nota2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        met_codigo= (EditText) findViewById(R.id.cet_codigo);
        met_nombre= (EditText) findViewById(R.id.cet_nombre);
        met_nota1= (EditText) findViewById(R.id.cet_nota1);
        met_nota2= (EditText) findViewById(R.id.cet_nota2);
    }

    public void alta(View view){
        String v_codigo= met_codigo.getText().toString();
        String v_nombre= met_nombre.getText().toString();
        String v_nota1= met_nota1.getText().toString();
        String v_nota2= met_nota2.getText().toString();

        BBDD objeto_bd = new BBDD(this, "FicheroDeLaBD", null, 1);
        SQLiteDatabase manejador = objeto_bd.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("codigo", v_codigo);
        registro.put("nombre", v_nombre);
        registro.put("nota1", v_nota1);
        registro.put("nota2", v_nota2);

        //Inserta el registro
        manejador.insert("alumno", null, registro);
        manejador.close();
    }

    public void consulta(View view){
        String v_codigo= met_codigo.getText().toString();
        String v_nombre;
        String v_nota1;
        String v_nota2;

        BBDD objeto_bd = new BBDD(this, "FicheroDeLaBD", null, 1);
        SQLiteDatabase manejador = objeto_bd.getWritableDatabase();

        Cursor registro = manejador.rawQuery("select * from alumno where codigo=" + v_codigo, null);

            registro.moveToFirst();

            v_nombre = registro.getString(1);
            v_nota1 = registro.getString(2);
            v_nota2 = registro.getString(3);

            met_nombre.setText(v_nombre);
            met_nota1.setText(v_nota1);
            met_nota2.setText(v_nota2);

            manejador.close();
    }

    //Metodo para borrar un registro
    public void borrar(View view){
        String v_codigo= met_codigo.getText().toString();
        String v_nombre;
        String v_nota1;
        String v_nota2;

        BBDD objeto_bd = new BBDD(this, "FicheroDeLaBD", null, 1);
        SQLiteDatabase manejador = objeto_bd.getWritableDatabase();

        //Borra el registro
        manejador.delete("alumno", "codigo=" + v_codigo, null);
        manejador.close();

    }
}