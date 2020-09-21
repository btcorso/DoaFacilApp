package com.example.doafacilapp.repository;

// Aqui será o lugar para armazenamento de dados

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.doafacilapp.util.Constantes;
import com.example.doafacilapp.util.Util;

import java.sql.SQLData;


public class LoginRepository extends SQLiteOpenHelper {


//aqui que vamos criar o nosso banco de dados
    public LoginRepository(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
}

//onde vamos criar as tabelas e as estruturas e tudo mais. Método sempre inicia quando chama o LoginRepository (cuidado para não recriar as coisas denovo).
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS TB_LOGIN( ");
        query.append(" ID_LOGIN INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" USUARIO TEXT(15) NOT NULL,");
        query.append(" SENHA TEXT(15) NOT NULL)");

        db.execSQL(query.toString());
        popularBD(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
    }

    private void popularBD(SQLiteDatabase db) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO TB_LOGIN(USUARIO, SENHA) VALUES(?, ?) ");

        db.execSQL(query.toString(), new String[] {"doador", "doador"});

    }

    public void listarLogins(Activity activity){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_LOGIN", null, null, null, null, null, "USUARIO");
        while(cursor.moveToNext()){
            String txt = "ID de usuário: " + String.valueOf(cursor.getInt(cursor.getColumnIndex("ID_LOGIN")));
            Util.showMsgToast(activity, txt);
        }

    }

    public void addLogin(String login, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USUARIO", login);
        contentValues.put("SENHA", senha);

        db.insert("TB_LOGIN",null, contentValues);

    }

    public void updateLogin(String login, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USUARIO", login);
        contentValues.put("SENHA", senha);

        db.update("TB_LOGIN", contentValues, "id_login > 0",null);
    }
}



