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

public class ListaLocal extends AppCompatActivity {

    private ListView listaItens;
    private Button btnVoltar;
    private String[] itens = {
            "\nNão+Pelo Treze de Maio\n" +
                    "Av. Treze de Maio, 2072 · (85) 99713-9021\n",
            "\nNão + Pêlo\n" +
                    "Av. Professor Gomes de Matos, 661 - Loja A · (85) 3032-6162\n",
            "\nNão+Pelo\n" +
                    "Av. Sargento Hermínio Sampaio, 3100 - 2112 · (85) 3023-7800\n",
            "\nNão + Pelo\n" +
                    " Shopping center · North shopping joquei, loja 2008 Piso 2, Av. Lineu Machado\n",
            "\nNão Mais Pêlo\n" +
                    " Av. Senador Virgílio Távora, 1461\n",
            "\nNão Mais pêlo\n" +
                    " Shopping Cambeba Open Mall, Av. Washington Soares, 6180\n",
            "\nNão mais Pêlo\n" +
                    " Av. Bezerra de Menezes, 1985\n",
            "\nNão mais Pêlo Papicu\n" +
                    " R. Dr. Gilberto Studart, 155\n"
    };

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_local);

        listaItens = (ListView) findViewById(R.id.lvLocal);

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
                Intent i = new Intent(ListaLocal.this, Perfil.class);
                startActivity(i);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaLocal.this, ListaArea.class);
                startActivity(i);
            }
        });

    }

    private void inicializaComponentes(){
        btnVoltar = (Button) findViewById(R.id.btnLocalVoltar);
        listaItens = (ListView) findViewById(R.id.lvLocal);
    }



    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
