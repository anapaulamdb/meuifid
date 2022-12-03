package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meuid.R;

public class Aprovados extends AppCompatActivity {

    private TextView aprovados_adm;
    private ImageView voltar;
    private ImageView sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovados);

        getSupportActionBar().hide();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Aprovados.this, TelaAdm.class);
                startActivity(intent);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Aprovados.this, Login.class);
                startActivity(intent);
            }
        });


    }
}