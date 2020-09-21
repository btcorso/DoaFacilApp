package com.example.doafacilapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.doafacilapp.inicio.EscolhaInicio;
import com.example.doafacilapp.util.Util;

public class TelaInicio extends AppCompatActivity {

    private Object view;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicio);

        Util.showMsgToast(this, "Doa Fácil v1.0");

//        Button btnComecar = findViewById(R.id.btnComecar);
//        btnComecar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(TelaInicio.this, EscolhaInicio.class);
//                startActivity(i);
//                finish();
//            }
//        });

        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(TelaInicio.this, EscolhaInicio.class);
                startActivity(i);
                finish();
            }
        }.start();

        }


    }




