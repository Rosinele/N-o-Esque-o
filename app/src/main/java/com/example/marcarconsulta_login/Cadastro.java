package com.example.marcarconsulta_login;

import android.content.Intent;
import android.content.PeriodicSync;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    private EditText editEmail, editSenha, editNome;
    private Button btnRegistrar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializaComponentes();
        eventoClicks();
    }

    private void eventoClicks() {

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String nome = editNome.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                criarUser(email, senha, nome);
            }
        });
    }

    private void criarUser(String email, String senha, String nome){
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    alert("Usuário Cadastrado com sucesso!!!");
                    Intent i = new Intent(Cadastro.this, Perfil.class);
                    startActivity(i);
                    finish();
                }else{
                    alert("Não foi possível realizar o seu cadastro no momento.");
               }
            }
        });
    }

    private void alert (String msg){
        Toast.makeText(Cadastro.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes(){
        editEmail = (EditText) findViewById(R.id.etCadastroEmail);
        editNome = (EditText) findViewById(R.id.etCadastroNome);
        editSenha = (EditText) findViewById(R.id.etCadastroSenha);
        btnRegistrar = (Button) findViewById(R.id.btnCadastroRegistrar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
