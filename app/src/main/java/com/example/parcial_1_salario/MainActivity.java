package com.example.parcial_1_salario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText salario;
    int castSalario;
    Spinner selectMes;
    Button calcular;
    TextView resultPrima;
    TextView resultCesantias;
    TextView resultVacaciones;
    TextView resultSalud;
    TextView resultPension;
    TextView resultConfa;
    TextView TotalNomina;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add
        Spinner spnMes = (Spinner) findViewById(R.id.spinnerRMes);
        ArrayList<SpinnerMes> mesList = new ArrayList<SpinnerMes>();
        mesList.add(new SpinnerMes("1", "Enero"));
        mesList.add(new SpinnerMes("2", "Febrero"));
        mesList.add(new SpinnerMes("3", "Marzo"));
        mesList.add(new SpinnerMes("4", "Abril"));
        mesList.add(new SpinnerMes("5", "Mayo"));
        mesList.add(new SpinnerMes("6", "Junio"));
        mesList.add(new SpinnerMes("7", "Julio"));
        mesList.add(new SpinnerMes("8", "Agosto"));
        mesList.add(new SpinnerMes("9", "Septiembre"));
        mesList.add(new SpinnerMes("10", "Octubre"));
        mesList.add(new SpinnerMes("11", "Noviembre"));
        mesList.add(new SpinnerMes("12", "Diciembre"));

        //Fill Data
        ArrayAdapter<SpinnerMes> mesAdapter = new ArrayAdapter<SpinnerMes>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, mesList);
        spnMes.setAdapter(mesAdapter);


        salario= findViewById(R.id.edtTextSalario);
        selectMes=findViewById(R.id.spinnerRMes);
        calcular=findViewById(R.id.btnCalcular);
        calcular.setOnClickListener(this);

        resultPrima = findViewById(R.id.txtRPrima);
        resultCesantias = findViewById(R.id.txtRCesantias);
        resultVacaciones = findViewById(R.id.txtRVacaciones);
        resultSalud = findViewById(R.id.txtRSalud);
        resultPension = findViewById(R.id.txtRPension);
        resultConfa = findViewById(R.id.txtRConfa);

        TotalNomina = findViewById(R.id.txtRNomina);

    }

    private double calcularPrima(int A_salario, int B_transporte, int C_mes){
        Double CalcP = (( Double.valueOf(A_salario)  + Double.valueOf( B_transporte))* Double.valueOf( C_mes)) / 360;
        return CalcP;
    }

    private double calcularCesantia(int A_salario, int B_transporte, int C_mes){
        Double CalcC = (( Double.valueOf(A_salario)  + Double.valueOf( B_transporte))* Double.valueOf( C_mes)) / 360;
        return CalcC;
    }

    private double calcularVacaciones(int A_salario, int B_mes){
        Double CalcV = (Double.valueOf(A_salario) * Double.valueOf( B_mes)) / 720;
        return CalcV;
    }

    private double calcularSalud(int A_salario, Double B_salud){
        Double CalcS = Double.valueOf(A_salario) * B_salud;
        return CalcS;
    }

    private double calcularPension(int A_salario, Double B_pension){
        Double CalcP = Double.valueOf(A_salario) * B_pension;
        return CalcP;
    }

    private double calcularConfa(int A_salario, Double B_confa){
        Double CalcCo = Double.valueOf(A_salario) * B_confa;
        return CalcCo;
    }


    private double calcularNomina(int A_salario, int B_transporte, Double C_salud, Double D_pension){
        Double CalcN= 0.0;
        if (A_salario <= (A_salario*2))
        {
        CalcN = Double.valueOf(A_salario) + B_transporte - C_salud - D_pension;
        }
        else {
            CalcN = Double.valueOf(A_salario) - C_salud - D_pension;
        }
        return CalcN;
    }

    private int calcularMes(String A_Mes){
        int CalcM=0;
        if (A_Mes.equals("Enero") || A_Mes.equals("Marzo") || A_Mes.equals("Mayo") || A_Mes.equals("Julio") || A_Mes.equals("Agosto") || A_Mes.equals("Octubre") || A_Mes.equals("Diciembre") ){
            CalcM=31;
        }
        else if (A_Mes.equals("Abril") || A_Mes.equals("Junio") || A_Mes.equals("Septiembre") || A_Mes.equals("Noviembre")){
            CalcM=30;
        }
        else if (A_Mes.equals("Febrero")){
            CalcM=28;
        }
        return CalcM;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btnCalcular){
            Double Confa = 0.04;
            Double salud = 0.04;
            Double pension = 0.04;
            int transporte = 106454;
            int mes = calcularMes(selectMes.getSelectedItem().toString());
            castSalario = (Integer.parseInt(salario.getText().toString())/30)*mes;

            Double calpri = calcularPrima(castSalario, transporte, mes);
            resultPrima.setText(calpri.toString());

            Double calces = calcularCesantia(castSalario, transporte, mes);
            resultCesantias.setText(calces.toString());

            Double calvac = calcularVacaciones(castSalario, mes);
            resultVacaciones.setText(calvac.toString());

            Double calsal = calcularSalud(castSalario, salud);
            resultSalud.setText(calsal.toString());

            Double calpen = calcularPension(castSalario, pension);
            resultPension.setText(calpen.toString());

            Double calcon = calcularConfa(castSalario, Confa);
            resultConfa.setText(calcon.toString());

            Double calnom = calcularNomina(castSalario, transporte, calsal, calpen);
            TotalNomina.setText(calnom.toString());

            Toast.makeText(this, "Total nomina: "+calnom, Toast.LENGTH_SHORT).show();
        }
    }
}