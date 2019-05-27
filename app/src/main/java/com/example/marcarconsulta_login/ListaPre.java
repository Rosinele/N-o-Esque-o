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

public class ListaPre extends AppCompatActivity {

    private ListView listaItens;
    private String[] itens = {"Evite exposição ao sol 15 dias antes de cada sessão e dobre os cuidados nas 72h próximas ao tratamento.",
            "Organize-se para aparar os pelos antes das sessões, isso facilita a execução do tratamento, você observa o retardo no crescimento e se sente muito mais confortável.",
            " Certifique-se de que o pelo está de 1 a 3mm para fora da pele. É necessário que ele esteja fora para captar a luz e conduzi-la para a cauterização.",
             "Ao realizar a sua primeira sessão o método que você utilizou para depilar recentemente não afetará o tratamento.",
            " Após iniciar o tratamento, não utilize nenhum método de depilação que extraia os pelos pela raiz. Métodos de extração eliminam os pelos na fase anágena que é a fase ideal para cauterização do raiz do pelo. Então, se for necessário aparar os pelos, utilize somente lâminas, aparadores ou creme depilatório.",
            "Caso você já apresente retardo no crescimento, leve o tempo de crescimento em consideração para que os pelos estejam no comprimento necessário. Mantendo assim, o intervalo recomendado entre as sessões.",
            "Aplique o creme hidratante 2x ao dia, durante os 5 dias que antecedem a sessão.",
            "Faça uso contínuo de filtro solar na área em tratamento com fator de proteção igual ou superior a 25.",
            "Durante o tratamento, não faça bronzeamento artificial, a pele ficará muito mais sensível e mudará a pigmentação, mudando completamente os resultados.",
            "Esteja atenta e cuide para que a sua pele não tenha sinais de alergia, vermelhidão ou machucados antes das sessões.",
            "Ao iniciar uma medicação contínua, entre em contato com a sua técnica para obter informações sobre como proceder para não interromper o tratamento de fotodepilação."
    };


    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_pre);

        listaItens = (ListView) findViewById(R.id.lvPre);

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
                Intent i = new Intent(ListaPre.this, Perfil.class);
                startActivity(i);

            }
        });


    }

    private void inicializaComponentes(){
        listaItens = (ListView) findViewById(R.id.lvPre);
    }



    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
