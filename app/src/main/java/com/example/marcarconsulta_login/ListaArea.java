package com.example.marcarconsulta_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

public class ListaArea extends AppCompatActivity {

    private ListView listaItens;
    private Button btnVoltar;
    private String[] itens = {
            "Axila", "Buço", "Braço", "Panturrilha", "Virilha", "Coxa", "Abdomem", "Peito", "Lombar", "Facial", "Axila", "Buço", "Braço", "Panturrilha", "Virilha", "Coxa", "Abdomem", "Peito", "Lombar", "Facial"
    };


    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_area);

        listaItens = (ListView) findViewById(R.id.lvArea);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens

        );
        listaItens.setAdapter(adaptador);
        inicializaComponentes();
        eventoClick();

    }

    private void eventoClick(){
        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoposicao = position;
                String valorclicado = listaItens.getItemAtPosition(codigoposicao).toString();
                Intent i = new Intent(ListaArea.this, ListaLocal.class);
                startActivity(i);

                Intent intent = new Intent(ListaArea.this, Resumo.class);
                intent.putExtra("Area clicada", valorclicado);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaArea.this, Perfil.class);
                startActivity(i);
            }
        });

    }

    private void inicializaComponentes(){
        btnVoltar = (Button) findViewById(R.id.btnAreaVoltar);
        listaItens = (ListView) findViewById(R.id.lvArea);
    }



    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
