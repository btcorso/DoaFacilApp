package com.example.doafacilapp;
// Essa Activity é onde o usuário (doador) fará seu login;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.doafacilapp.bo.LoginBOdoador;
import com.example.doafacilapp.validation.LoginValidation;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivityDoador extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtPassword;
    private LoginBOdoador loginBOdoador;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doador); //abriu o xml do telo login doador

        loginBOdoador = new LoginBOdoador(LoginActivityDoador.this);

//        preferences = getSharedPreferences("pref_doa", Context.MODE_PRIVATE);
//        String login = preferences.getString("login",null);
//        String senha = preferences.getString("senha", null);
//        if (login != null && senha != null){
//            Intent i = new Intent(LoginActivityDoador.this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }

        edtLogin = findViewById(R.id.edtLogin_doador);
        edtPassword = findViewById(R.id.edtPassword_doador);

        Button btnLogin = findViewById(R.id.btnLogin_doador);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = edtLogin.getText().toString();
                String senha = edtPassword.getText().toString();

                LoginValidation validation = new LoginValidation();
                validation.setActivity(LoginActivityDoador.this);
                validation.setEdtLogin(edtLogin);
                validation.setEdtSenha(edtPassword);
                validation.setLogin(login);
                validation.setSenha(senha);
                boolean isValido = loginBOdoador.validarCamposLogin(validation);
                if(isValido){
                    Intent i = new Intent(LoginActivityDoador.this, PessoaDoadorActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    }
