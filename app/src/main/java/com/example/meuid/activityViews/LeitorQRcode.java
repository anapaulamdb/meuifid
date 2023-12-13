package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.example.meuid.R;

public class LeitorQRcode extends AppCompatActivity {

    private ImageView voltar;
    private ImageView sair;
    private TextView textViewResult;
    private ImageView imgSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.result);
        imgSelected = findViewById(R.id.imgSelected);

        //Inicia a leitura do QR code
        new IntentIntegrator(this).initiateScan();
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // Se o usuário cancelou a leitura
                // Faça algo aqui, se necessário
                textViewResult.setText("Leitura cancelada");
            } else {
                // result.getContents() contém o valor do QR code lido
                String qrCodeValue = result.getContents();
                if (qrCodeValue != null && qrCodeValue.contains("Sucesso")) {
                // Exibir o valor do QR code no TextView
                    textViewResult.setText(qrCodeValue);
                    // Tornar o TextView visível
                    textViewResult.setVisibility(TextView.VISIBLE);
                    imgSelected.setImageResource(R.drawable.check);
                } else {
                    textViewResult.setText("QR Code inválido!");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            textViewResult.setText("QR Code inválido!");
        }
    }
}
//        voltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(LeitorQRcode.this, TelaAdm.class);
//                startActivity(intent);
//            }
//        });
//
//        sair.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(LeitorQRcode.this,Login.class);
//                startActivity(intent);
//            }
//        });
