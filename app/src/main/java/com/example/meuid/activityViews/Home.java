package com.example.meuid.activityViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.meuid.R;

public class Home extends AppCompatActivity {
    Button bt_qrcode;
    Button bt_id;
    ImageView sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt_qrcode = (Button) findViewById(R.id.bt_qrcode);
        bt_id = (Button) findViewById(R.id.bt_id);
        sair = (ImageView) findViewById(R.id.sair);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        int codigo_recebido = intent.getIntExtra("codigo", 0);

        // login
        bt_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, TelaPrincipal.class);
                intent.putExtra("codigo", codigo_recebido);
                startActivity(intent);
            }
        });
        bt_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, QrCode.class);
                intent.putExtra("codigo", codigo_recebido);
                startActivity(intent);
            }
        });
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });
    }
}