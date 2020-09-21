package com.example.doafacilapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.doafacilapp.bo.LoginBOdoador;
import com.example.doafacilapp.bo.LoginBOong;
import com.example.doafacilapp.validation.LoginValidation;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivityOng extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtPassword;

    private LoginBOong loginBOong;

    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ong);

        loginBOong = new LoginBOong();

//        preferences = getSharedPreferences("pref_ong", Context.MODE_PRIVATE);
//        String login = preferences.getString("login",null);
//        String senha = preferences.getString("senha", null);
//        if (login != null && senha != null){
//            Intent i = new Intent(LoginActivityOng.this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }

        edtLogin = findViewById(R.id.edtLogin_ong);
        edtPassword = findViewById(R.id.edtPassword_ong);

        Button btnLogin = findViewById(R.id.btnLogin_ong);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = edtLogin.getText().toString();
                String senha = edtPassword.getText().toString();

                LoginValidation validation = new LoginValidation();
                validation.setActivity(LoginActivityOng.this);
                validation.setEdtLogin(edtLogin);
                validation.setEdtSenha(edtPassword);
                validation.setLogin(login);
                validation.setSenha(senha);
                boolean isValido = loginBOong.validarCamposLogin(validation);
                if(isValido){
                    Intent i = new Intent(LoginActivityOng.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
