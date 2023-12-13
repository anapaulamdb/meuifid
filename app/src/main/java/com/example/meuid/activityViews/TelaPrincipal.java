package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
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
    TextView textValidade;
    Integer codigo;
    Cursor cursor;
    byte[] imageBytes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_estudantil);

        getSupportActionBar().hide();

        sair = (ImageView) findViewById(R.id.sair);
        textNome = (TextView) findViewById(R.id.textNome);
        textCurso = (TextView) findViewById(R.id.textCurso);
        textMatricula = (TextView) findViewById(R.id.textMatricula);
        textCPF = (TextView) findViewById(R.id.textCPF);

        imageView = (ImageView) findViewById(R.id.imageView);

        DBHelper db = new DBHelper(getBaseContext());
        Intent intent = getIntent();
        int temp = intent.getIntExtra("codigo", 0);
        cursor = db.carregaDadoById(temp);

        String texto = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGEM));

        byte[] decodedString = Base64.decode(texto.getBytes(), Base64.NO_WRAP);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        textNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOME)));
        textCurso.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CURSO)));
        textMatricula.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.MATRICULA)));
        textCPF.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CPF)));
        imageView.setImageBitmap(decodedByte);
       // textValidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.)));
        //textValidade.setText("00/00/0000");


        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPrincipal.this, Home.class);
                intent.putExtra("codigo", temp);
                startActivity(intent);
            }
        });

    }


}