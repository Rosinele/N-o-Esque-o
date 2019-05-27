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

public class ListaPos extends AppCompatActivity {

    private ListView listaItens;
    private String[] itens = {
            "Evite exposição ao sol 15 dias depois de cada sessão e dobre os cuidados nas 72h após o tratamento.",
            "Utilize continuamente o filtro solar com fator igual ou superior a 25 até o final do tratamento.",
            "Hidrate sua pele com o creme de hidratação Não+Pelo nos 5 dias posteriores à sessão para restaurá-la. Hidratantes com ação anti-inflamatória ou cicatrizante podem ajudar a sua pele a se recuperar mais rápido após as sessões. Se houver dúvida, consulte o seu dermatologista.",
            "Sempre utilize sabonete neutro nas áreas onde realizou sessões de luz pulsada. Outros sabonetes podem ressecar a pele.",
            "Se receber alguma orientação dos nossos profissionais ou do seu médico para evitar algum dermocosmético após as sessões, confie e siga a orientação. Cada pele reage de uma forma e você precisa ficar atenta aos cuidados especiais.",
            "Continue utilizando apenas lâminas, aparadores ou creme depilatório se precisar remover os pelos.",
            "Organize-se para não faltar à próxima sessão agendada, a frequência é essencial para o sucesso do resultado que você almeja."
    };

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_pos);

        listaItens = (ListView) findViewById(R.id.lvPos);

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
                Intent i = new Intent(ListaPos.this, Perfil.class);
                startActivity(i);
            }
        });

    }

    private void inicializaComponentes(){
        listaItens = (ListView) findViewById(R.id.lvPos);
    }



    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
