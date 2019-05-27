package com.example.marcarconsulta_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pre extends AppCompatActivity {

    private Button btPre, btDia, btPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        inicializaComponentes();
        eventoClick();
    }
    private void eventoClick(){
        btPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pre.this, ListaPre.class);
                startActivity(i);
            }
        });


        btDia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pre.this, ListaNoDia.class);
                startActivity(i);
            }
        });

        btPos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pre.this, ListaPos.class);
                startActivity(i);
            }
        });

    }
    private void inicializaComponentes(){
        btPre = (Button) findViewById(R.id.btnPre);
        btDia = (Button) findViewById(R.id.btnDia);
        btPos = (Button) findViewById(R.id.btnPos);
    }
}
