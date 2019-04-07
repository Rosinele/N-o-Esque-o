package com.example.marcarconsulta_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {

    private EditText Usuario;
    private EditText Senha;
    private TextView Info;
    private Button Login;
    private int counter = 5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario = (EditText)findViewById(R.id.etUsuario);
        Senha = (EditText)findViewById(R.id.etSenha);
        Info = (TextView) findViewById(R.id.info);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("Você tem apenas 5 tentativas");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validacao(Usuario.getText().toString(), Senha.getText().toString());

            }
        });
    }

    private void validacao(String usrNome, String usrSenha){
        if((usrNome.equals("Rose")) && (usrSenha.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

        }else{

            counter--;
            Info.setText("Restam apenas " + String.valueOf(counter) + " tentativas");

            if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }
}
