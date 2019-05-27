package com.example.marcarconsulta_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

public class ListaNoDia extends AppCompatActivity {
    private ListView listaItens;
    private String[] itens = {
            "Responda ao questionário de avaliação com sinceridade. As informações são sigilosas e relevantes para mantermos a integridade da sua pele durante o tratamento e garantirmos o resultado.",
            "Mantenha a área de tratamento longe de fontes de calor, evitando atividade física, banhos quentes e saunas imediatamente antes e logo após as sessões.",
            "Aplique somente produtos sem álcool na área de tratamento.",
            "Dica: fotografe para visualizar a evolução do seu tratamento."
    };

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_pos);

        listaItens = (ListView) findViewById(R.id.lvnoDia);

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
                Intent i = new Intent(ListaNoDia.this, Perfil.class);
                startActivity(i);
            }
        });

    }

    private void inicializaComponentes(){
        listaItens = (ListView) findViewById(R.id.lvnoDia);
    }



    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}

