package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Integer codigo;
    Cursor cursor;
    byte[] imageBytes;



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