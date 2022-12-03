package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meuid.R;
import com.example.meuid.database.DBHelper;

public class TelaPrincipal extends AppCompatActivity {

    private ImageView code_qr;
    private ImageView sair;
    View foto_usuario;
    ImageView imageView;
    TextView textNome;
    TextView textCurso;
    TextView textMatricula;
    TextView textCPF;
    TextView textUnidade;
    TextView textValidade;
    String codigo;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        getSupportActionBar().hide();

        code_qr = (ImageView) findViewById(R.id.code_qr);
        sair = (ImageView) findViewById(R.id.sair);
        textNome = (TextView) findViewById(R.id.textNome);
        textCurso = (TextView) findViewById(R.id.textCurso);
        textMatricula = (TextView) findViewById(R.id.textMatricula);
        textCPF = (TextView) findViewById(R.id.textCPF);
        textUnidade = (TextView) findViewById(R.id.textUnidade);
        textValidade = (TextView) findViewById(R.id.textValidade);


        codigo = this.getIntent().getStringExtra("4");

        DBHelper crud = new DBHelper(getBaseContext());

        //alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        textNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOME)));
        textCurso.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CURSO)));
        textMatricula.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.MATRICULA)));
        textCPF.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CPF)));
        textUnidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CAMPUS)));
       // textValidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.)));


        code_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPrincipal.this, QrCode.class);
                startActivity(intent);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPrincipal.this, Login.class);
                startActivity(intent);
            }
        });

    }


}