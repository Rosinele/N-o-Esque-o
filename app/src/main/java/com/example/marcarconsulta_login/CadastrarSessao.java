package com.example.marcarconsulta_login;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.example.marcarconsulta_login.modelo.Sessao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastrarSessao extends AppCompatActivity {
    EditText etArea, etLocal, etData;
    ListView lvDados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_sessao);
        etArea = (EditText) findViewById(R.id.etAgendarArea);
        etLocal = (EditText) findViewById(R.id.etAgendarLocal);
        etData = (EditText) findViewById(R.id.etAgendarData);
        lvDados = (ListView) findViewById(R.id.lvAgendarDados);

        inicializarFirebase();

    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(CadastrarSessao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_area, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_novo){
            Sessao s = new Sessao();
            s.setUid(UUID.randomUUID().toString());
            s.setArea(etArea.getText().toString());
            s.setLocal(etLocal.getText().toString());
            s.setData(etData.getText().toString());
            databaseReference.child("Sessão").child(s.getUid()).setValue(s);
            limparCampos();
        }
        return true;
    }

    private void limparCampos(){
        etArea.setText("");
        etLocal.setText("");
        etData.setText("");

    }
}
