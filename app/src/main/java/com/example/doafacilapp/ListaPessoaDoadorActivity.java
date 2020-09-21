package com.example.doafacilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.doafacilapp.entidade.PessoaDoador;
import com.example.doafacilapp.repository.PessoaDoadorRepository;
import com.example.doafacilapp.util.Constantes;

import java.util.ArrayList;
import java.util.List;

public class ListaPessoaDoadorActivity extends AppCompatActivity {

    private ListView lstPessoasDoador;

    private PessoaDoadorRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa_doador);

        repository = new PessoaDoadorRepository(this, Constantes.BD_NOME, null, Constantes.BD_VERSAO);

        lstPessoasDoador = findViewById(R.id.lstPessoasDoador);

        List<PessoaDoador> lista = repository.listarPessoasDoador();

        List<String> valores = new ArrayList<String>();
        for (PessoaDoador p : lista){
            valores.add(p.getNome());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,valores);
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lstPessoasDoador.setAdapter(adapter);
    }
}