package com.example.meuid.activityViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meuid.R;


public class Layout extends AppCompatActivity {
    TextView nome, matricula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        getSupportActionBar().hide();

        nome = (TextView) findViewById(R.id.nome);
        matricula = (TextView) findViewById(R.id.matricula);
    }
}