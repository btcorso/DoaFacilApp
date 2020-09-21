package com.example.doafacilapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doafacilapp.entidade.PessoaDoador;
import com.example.doafacilapp.entidade.Sexo;
import com.example.doafacilapp.util.Constantes;

import java.util.ArrayList;
import java.util.List;

public class PessoaDoadorRepository extends SQLiteOpenHelper {
    public PessoaDoadorRepository(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS TB_PESSOA_DOADOR( ");
        query.append(" ID_PESSOA_DOADOR INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" IDADE INTEGER NOT NULL,");
        query.append(" EMAIL TEXT(50) NOT NULL,");
        query.append(" SEXO TEXT NOT NULL,");
        query.append(" CPF TEXT(14) NOT NULL,");
        query.append(" ENDERECO TEXT(50) )");

        sqLiteDatabase.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void salvarPessoaDoador(PessoaDoador pessoaDoador){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", pessoaDoador.getNome());
        contentValues.put("IDADE", pessoaDoador.getIdade());
        contentValues.put("EMAIL", pessoaDoador.getEmail());
        contentValues.put("SEXO",pessoaDoador.getSexo().ordinal());
        contentValues.put("CPF", pessoaDoador.getCpf());
        contentValues.put("ENDERECO", pessoaDoador.getEndereco());

        db.insert("TB_PESSOA_DOADOR",null, contentValues);
    }

    public List<PessoaDoador> listarPessoasDoador() {
        List<PessoaDoador> lista = new ArrayList<PessoaDoador>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("TB_PESSOA_DOADOR", null, null, null, null,null,"NOME");

        while (cursor.moveToNext()){
            PessoaDoador pessoaDoador = new PessoaDoador();
            pessoaDoador.setIdPessoa(cursor.getInt(cursor.getColumnIndex("ID_PESSOA_DOADOR")));
            pessoaDoador.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            pessoaDoador.setIdade(cursor.getString(cursor.getColumnIndex("IDADE")));
            pessoaDoador.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            int sexo = cursor.getInt(cursor.getColumnIndex("SEXO"));
            pessoaDoador.setSexo(Sexo.getSexo(sexo));
            pessoaDoador.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
            pessoaDoador.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));

            lista.add(pessoaDoador);
        }

        return lista;
    }
}
