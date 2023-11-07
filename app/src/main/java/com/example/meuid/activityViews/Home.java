package com.example.meuid.activityViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.meuid.R;

public class Home extends AppCompatActivity {
//    private ImageView code_qr;

    Button cad;
    Button qr_code;
    Button id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cad = (Button) findViewById(R.id.bt_recadastro);
        qr_code = (Button) findViewById(R.id.bt_qrcode);
        id = (Button) findViewById(R.id.bt_id);

        getSupportActionBar().hide();

        // login
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, TelaPrincipal.class);
                intent.putExtra("codigo", 3);
                startActivity(intent);
            }
        });
        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, QrCode.class);
                startActivity(intent);
            }
        });
    }
}