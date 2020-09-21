package com.example.doafacilapp.bo;
// Nessa Activity Constroi e Valida as informações inseridas nos campos de login;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.doafacilapp.repository.LoginRepository;
import com.example.doafacilapp.util.Constantes;
import com.example.doafacilapp.util.Util;
import com.example.doafacilapp.validation.LoginValidation;

public class LoginBOdoador {

    private LoginRepository loginRepository;

    public LoginBOdoador(Activity activity){
        LoginRepository loginRepository = new LoginRepository(activity, Constantes.BD_NOME,null,Constantes.BD_VERSAO);

    }

    public boolean validarCamposLogin(LoginValidation validation){
        boolean resultado = true;
        if (validation.getLogin() == null || "".equals(validation.getLogin())){
            validation.getEdtLogin().setError("LOGIN OBRIGATÓRIO!"); // Avisa se o campo está vazio
            resultado = false;
        }

        if (validation.getSenha() == null || "".equals(validation.getSenha())){
            validation.getEdtSenha().setError("SENHA OBRIGATÓRIO"); // Avisa se o campo está vazio
            resultado = false;
        }
// pega os valores inserido validados e ve se bate com o correto
        if (resultado){
            if(!validation.getLogin().equals("doador")||!validation.getSenha().equals("doador")){
                Util.showMsgToast(validation.getActivity(), "Login/Senha Inválido");
                resultado = false;
            }
            //                SharedPreferences.Editor editor = validation.getActivity().getSharedPreferences("pref_doa", Context.MODE_PRIVATE).edit();
            //                editor.putString("login", validation.getLogin());
            //                editor.putString("senha", validation.getSenha());
            //                editor.commit();
        }
        return resultado;
    }
}
