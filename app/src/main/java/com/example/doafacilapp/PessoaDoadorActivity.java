package com.example.doafacilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.doafacilapp.entidade.PessoaDoador;
import com.example.doafacilapp.entidade.Sexo;
import com.example.doafacilapp.repository.PessoaDoadorRepository;
import com.example.doafacilapp.util.Constantes;
import com.example.doafacilapp.util.Mask;
import com.example.doafacilapp.util.Util;

public class PessoaDoadorActivity extends AppCompatActivity {

    private Spinner spnProfissao;

    private EditText edtCpf;
    private EditText edtNome;
    private EditText edtIdade;
    private EditText edtEndereco;
    private EditText edtEmail;

    private RadioGroup sexo;
    private RadioButton sexomasc;
    private RadioButton sexofem;
    private RadioButton sexoout;

    private TextWatcher cpfMask;

    private PessoaDoadorRepository pessoaDoadorRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_doador);
        pessoaDoadorRepository = new PessoaDoadorRepository(this, Constantes.BD_NOME, null, Constantes.BD_VERSAO);

        edtCpf = findViewById(R.id.edtCpf);
        edtNome = findViewById(R.id.edtNome);
        edtIdade = findViewById(R.id.edtIdade);
        edtEndereco = findViewById(R.id.edtEndereço);
        edtEmail = findViewById(R.id.edtEmail);

        sexo = findViewById(R.id.rdtGroup);
        sexomasc = findViewById(R.id.rdtMasc);
        sexofem = findViewById(R.id.rdtFem);
        sexoout = findViewById(R.id.rdtOut);

        cpfMask = Mask.insert("###.###.###-##", edtCpf);
        edtCpf.addTextChangedListener(cpfMask);

        edtCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (edtCpf.getText().length() < 14) {
                    edtCpf.setText("");
                }
            }
        });
    }

    public void enviarPessoaDoador(View view){
        PessoaDoador p = montarPessoa();
        validarPessoaDoador(p);
        Util.showMsgToast(this,"OK");
        pessoaDoadorRepository.salvarPessoaDoador(p);
        Intent i = new Intent(this,ListaPessoaDoadorActivity.class);
        startActivity(i);
    }

    public boolean validarPessoaDoador(PessoaDoador pessoaDoador){
        boolean erro = false;
        if (pessoaDoador.getNome() == null || "".equals(pessoaDoador.getNome())){
            erro = true;
            edtNome.setError("Campo Nome Obrigatório");
        }
        if (pessoaDoador.getIdade() == null || "".equals(pessoaDoador.getIdade())){
            erro = true;
            edtIdade.setError("Campo Idade Obrigatório");
        }
        if (pessoaDoador.getCpf() == null || "".equals(pessoaDoador.getCpf()) || edtCpf.getText().length() < 14){
            erro = true;
            edtCpf.setError("Campo CPF Completo Obrigatório");
        }
        if (pessoaDoador.getEmail() == null || "".equals(pessoaDoador.getEmail()) ){
            erro = true;
            edtEmail.setError("Campo Email Obrigatório");
        }
        return erro;
    }

    private PessoaDoador montarPessoa(){
        PessoaDoador pessoa = new PessoaDoador();
        pessoa.setNome(edtNome.getText().toString());
        pessoa.setCpf(edtCpf.getText().toString());
        pessoa.setEndereco(edtEndereco.getText().toString());
        pessoa.setEmail(edtEmail.getText().toString());
        pessoa.setIdade(edtIdade.getText().toString());
        switch (sexo.getCheckedRadioButtonId()){
            case R.id.rdtMasc:
                pessoa.setSexo(Sexo.MASCULINO);
            break;
            case R.id.rdtFem:
                pessoa.setSexo(Sexo.FEMININO);
            break;
            case R.id.rdtOut:
                pessoa.setSexo(Sexo.OUTROS);
            break;
        }

        return pessoa;
    }


}