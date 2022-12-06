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

public class TelaAdm extends AppCompatActivity {

    private TextView aprovados_adm;
    private TextView pendente_adm;
    private TextView expirados_adm;
    TextView textNome;
    ImageView ft_adm;
    ImageView sair;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adm);

        getSupportActionBar().hide();
        aprovados_adm = (TextView) findViewById(R.id.aprovados_adm);
        pendente_adm = (TextView) findViewById(R.id.pendente_adm);
        textNome = (TextView) findViewById(R.id.textNome);
        ft_adm = (ImageView) findViewById(R.id.ft_adm);
        sair = (ImageView) findViewById(R.id.sair);

        DBHelper db = new DBHelper(getBaseContext());
        Intent intent = getIntent();
        int temp = intent.getIntExtra("codigo", 0);
        cursor = db.carregaDadoById(temp);

        String texto = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGEM));
        byte[] decodedString = Base64.decode(texto.getBytes(), Base64.NO_WRAP);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        textNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOME)));
        ft_adm.setImageBitmap(decodedByte);

        aprovados_adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaAdm.this, Aprovados.class);
                startActivity(intent);
            }

        });

        pendente_adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaAdm.this,Pendentes.class);
                startActivity(intent);
            }

        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaAdm.this, Login.class);
                startActivity(intent);
            }
        });
    }
}