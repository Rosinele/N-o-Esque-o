package com.example.marcarconsulta_login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnLogin , btnNovo;
    private TextView txtResetSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaComponentes();
        eventoClicks();
    }

    private void eventoClicks() {
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                login(email, senha);
            }
        });

        txtResetSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ResetSenha.class);
                startActivity(i);
            }
        });
    }

    private void login(String email, String senha){
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(Login.this, Perfil.class);
                            startActivity(i);
                        }else{
                            alert("E-mail ou senha inválidos");
                        }
                    }
                });
    }

    private void alert (String s){
        Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes () {
        editEmail = (EditText) findViewById(R.id.editLoginEmail);
        editSenha = (EditText) findViewById(R.id.etLoginSenha);
        btnLogin = (Button) findViewById(R.id.btnLoginLogar);
        btnNovo = (Button) findViewById(R.id.btnLoginNovoUsuario);
        txtResetSenha = (TextView) findViewById(R.id.infoLoginResetSenha);
    }

    @Override
    protected void onStart () {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
