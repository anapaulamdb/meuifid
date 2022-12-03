package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meuid.R;

public class TelaAdm extends AppCompatActivity {

    private TextView aprovados_adm;
    private TextView pendente_adm;
    private TextView expirados_adm;
    ImageView sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adm);

        getSupportActionBar().hide();
        aprovados_adm = (TextView) findViewById(R.id.aprovados_adm);
        pendente_adm = (TextView) findViewById(R.id.pendente_adm);
        expirados_adm = (TextView) findViewById(R.id.expirados_adm);
        sair = (ImageView) findViewById(R.id.sair);

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

        expirados_adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaAdm.this, Expirados.class);
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