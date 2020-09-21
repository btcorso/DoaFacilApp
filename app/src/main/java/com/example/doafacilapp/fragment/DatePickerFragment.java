package com.example.doafacilapp.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    private int dia, mes, ano;

    public DatePickerFragment() {}

    public void setDateListener(DatePickerDialog.OnDateSetListener listener){
        this.listener = listener;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        ano = args.getInt("ano");
        mes = args.getInt("mes");
        dia = args.getInt("dia");
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return new DatePickerFragment(getActivity(), listener, ano, mes, dia);
//    }
}
