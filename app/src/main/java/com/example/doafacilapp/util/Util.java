package com.example.doafacilapp.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.doafacilapp.MainActivity;
import com.example.doafacilapp.R;

public class Util {

    public static void showMsgToast(Activity activity, String txt){

        LayoutInflater inflater = activity.getLayoutInflater();
        View lytToast = inflater.inflate(R.layout.toast_template, (ViewGroup) activity.findViewById(R.id.lytToast));

        TextView txtToast = (TextView)  lytToast.findViewById(R.id.txtToast);
        txtToast.setText(txt);

        Toast toast = new Toast(activity);
        toast.setView(lytToast);
        toast.setGravity(Gravity.CENTER,0,600);
        toast.show();
    }

    public static void showMsgOK(final Activity activity, String titulo, String txt, TipoMsg tipoMsg){
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        switch (tipoMsg){
            case INFO:
                alertDialog.setIcon(R.drawable.ic_action_name);
                break;
            case ERRO:
                alertDialog.setIcon(R.drawable.ic_action_name);
                break;
            case ALERTA:
                alertDialog.setIcon(R.drawable.ic_launcher_foreground);
                break;
        }
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Util.showMsgToast(activity, "Doa Fácil v1.0");
                alertDialog.dismiss();
            }
        });

//        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//        params.copyFrom(alertDialog.getWindow().getAttributes());
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

//        alertDialog.getWindow().setAttributes(params);
            alertDialog.show();
    }

}
