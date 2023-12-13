package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.meuid.R;

public class TelaAdm extends AppCompatActivity {
    Button qr_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adm);
        qr_code = (Button) findViewById(R.id.bt_qrcode);

        getSupportActionBar().hide();

        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaAdm.this, LeitorQRcode.class);
                startActivity(intent);
            }
        });
    }
}