package com.example.doafacilapp.bo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.doafacilapp.repository.LoginRepository;
import com.example.doafacilapp.util.Util;
import com.example.doafacilapp.validation.LoginValidation;

public class LoginBOong {

    private LoginRepository loginRepository;

    public boolean validarCamposLogin(LoginValidation validation){
        boolean resultado = true;
        if (validation.getLogin() == null || "".equals(validation.getLogin())){
            validation.getEdtLogin().setError("LOGIN OBRIGATÓRIO!");
            resultado = false;
        }

        if (validation.getSenha() == null || "".equals(validation.getSenha())){
            validation.getEdtSenha().setError("SENHA OBRIGATÓRIO");
            resultado = false;
        }

        if (resultado){
            if(!validation.getLogin().equals("ong")||!validation.getSenha().equals("ong")){
                Util.showMsgToast(validation.getActivity(), "Login/Senha Inválido");
                resultado = false;
            }
            //                SharedPreferences.Editor editor = validation.getActivity().getSharedPreferences("pref_ong", Context.MODE_PRIVATE).edit();
            //                editor.putString("login", validation.getLogin());
            //                editor.putString("senha", validation.getSenha());
            //                editor.commit();

        }
        return resultado;
    }
}