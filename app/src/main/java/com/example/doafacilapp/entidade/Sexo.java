package com.example.doafacilapp.entidade;

public enum Sexo {
    MASCULINO, FEMININO, OUTROS;

    public static Sexo getSexo(int pos){
        for (Sexo sexo : Sexo.values()){
            if(sexo.ordinal() == pos){
                return sexo;
            }
        }
        return null;
    }
}
