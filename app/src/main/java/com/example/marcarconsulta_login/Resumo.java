package com.example.marcarconsulta_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Resumo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        Bundle extras = getIntent().getExtras();
        String[] areaClicada = null;
        if(extras != null && extras.contains("Area clicada")) {
            areaClicada = extras.getSerializable("Area clicada");
        }
    }

}
