package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.meuid.R;
import com.example.meuid.database.BancoController;
import com.example.meuid.database.DBHelper;

import java.util.ArrayList;

public class Pendentes extends AppCompatActivity {

    private ImageView voltar;
    private ImageView sair;
    //public ListView  dadosAluno;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendentes);

        getSupportActionBar().hide();

        lista = (ListView) findViewById(R.id.lista);
        voltar = (ImageView) findViewById(R.id.voltar);
        sair = (ImageView) findViewById(R.id.sair);

        DBHelper db = new DBHelper(getBaseContext());

        //listarDados();

        DBHelper crud = new DBHelper(getBaseContext());
        SQLiteDatabase sqdb = db.getWritableDatabase();

        Cursor cursor = crud.carregaDadosPendentes();

        String[] nomeCampos = new String[]{DBHelper.NOME, DBHelper.MATRICULA};
        int[] idViews = new int[]{R.id.nome, R.id.matricula};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_layout, cursor, nomeCampos, idViews, 0);

        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(adaptador);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Pendentes.this, TelaAdm.class);
                startActivity(intent);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Pendentes.this,Login.class);
                startActivity(intent);
            }
        });

    }
}
/*
    public void listarDados() {
        try {
            DBHelper db = new DBHelper(getBaseContext());
            SQLiteDatabase sqdb = db.getWritableDatabase();
            Cursor cursor = sqdb.rawQuery("select nome, matricula from aluno", null);
            ArrayList<String> linhas = new ArrayList<String>();
            ArrayAdapter meuAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    linhas
            );
            lista.setAdapter(meuAdapter);
            cursor.moveToFirst();
            while(cursor != null){
                linhas.add(cursor.getString(1));
                cursor.moveToNext();

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/