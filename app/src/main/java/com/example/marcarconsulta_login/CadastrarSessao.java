package com.example.marcarconsulta_login;

import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.marcarconsulta_login.modelo.Sessao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import  android.widget.Toast;

public class CadastrarSessao extends AppCompatActivity {
    EditText etData, etHora;
    Spinner etArea,  etLocal;
    ListView lvDados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Sessao> listSessao = new ArrayList<Sessao>();
    private ArrayAdapter<Sessao> sessaoArrayAdapter;

    Sessao sessaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_sessao);
        etArea = (Spinner) findViewById(R.id.spnAgendarArea);
        etLocal = (Spinner) findViewById(R.id.spnAgendarLocal);
        etHora= (EditText) findViewById(R.id.etAgendarHora);
        etHora.addTextChangedListener(MaskEditUtil.mask(etHora, MaskEditUtil.FORMAT_HOUR));
        etData = (EditText)findViewById(R.id.etAgendarData);
        etData.addTextChangedListener(MaskEditUtil.mask(etData, MaskEditUtil.FORMAT_DATE));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        etData.setText(sdf.format(new Date()));

        lvDados = (ListView)findViewById(R.id.lvAgendarDados);

        inicializarFirebase();
        eventoDatabase();

        lvDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sessaoSelecionada = (Sessao) parent.getItemAtPosition(position);
                etData.setText(sessaoSelecionada.getData());
                etHora.setText(sessaoSelecionada.getHora());

            }
        });

    }


    private void eventoDatabase() {
        databaseReference.child("Sessão").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSessao.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Sessao s = objSnapshot.getValue(Sessao.class);
                    listSessao.add(s);
                }
                sessaoArrayAdapter = new ArrayAdapter<Sessao>(CadastrarSessao.this,
                        android.R.layout.simple_list_item_1, listSessao);
                lvDados.setAdapter(sessaoArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(CadastrarSessao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
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
            s.setArea(String.valueOf(etArea.getSelectedItem()));
            s.setLocal(String.valueOf(etLocal.getSelectedItem()));
            s.setHora(etHora.getText().toString());
            s.setData(etData.getText().toString());
            databaseReference.child("Sessão").child(s.getUid()).setValue(s);
            Toast.makeText(getApplicationContext(), "Sessão Agendada com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
        }else if(id == R.id.menu_atualiza){
            Sessao s = new Sessao();
            s.setUid(sessaoSelecionada.getUid());
            s.setArea(String.valueOf(etArea.getSelectedItem()).trim());
            s.setLocal(String.valueOf(etLocal.getSelectedItem()).trim());
            s.setHora(etHora.getText().toString().trim());
            s.setData(etData.getText().toString().trim());
            databaseReference.child("Sessão").child(s.getUid()).setValue(s);
            limparCampos();
        }
        else if(id == R.id.menu_deleta){
            Sessao s = new Sessao();
            s.setUid(sessaoSelecionada.getUid());
            databaseReference.child("Sessão").child(s.getUid()).removeValue();
            limparCampos();
        }
        return true;
    }

    private void limparCampos(){
        etArea.getFirstVisiblePosition();
        etLocal.getFirstVisiblePosition();
        etData.setText("");
        etHora.setText("");

    }
}
