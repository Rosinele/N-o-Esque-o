package com.example.marcarconsulta_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    private TextView textNome, btnLogOut;
    private Button btnAgendarDois, btnCuidados;


    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializaComponentes();
        eventoClick();
    }

    private void eventoClick(){
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logOut();
                finish();
            }
        });


        btnAgendarDois.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, CadastrarSessao.class);
                startActivity(i);
            }
        });

        btnCuidados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil.this, Pre.class);
                startActivity(i);
            }
        });


    }

    private void inicializaComponentes(){
        textNome = (TextView) findViewById(R.id.tvPerfilNome);
        btnLogOut = (TextView) findViewById(R.id.tvPerfilSair);
        btnAgendarDois = (Button) findViewById(R.id.btnPerfilAgendarDois);
        btnCuidados = (Button) findViewById(R.id.btnDicaseCuidados);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser(){
        if(user == null){
            finish();
        }else{
            textNome.setText(user.getEmail());
        }
    }
}