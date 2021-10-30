package com.example.parcial_1_salario;

public class SpinnerMes {
    public String id;
    public String mes;

    public SpinnerMes(String id, String mes)
    {
        this.id = id;
        this.mes = mes;
    }
    @Override
    public String toString(){
        return mes;
    }
}
