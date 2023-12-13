package com.example.meuid.activityViews;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;

import com.example.meuid.database.DBHelper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meuid.R;


public class QrCode extends AppCompatActivity {
    EditText editQRCode;
    Button btnGerarQRCode;
    ImageView imgQRCode;
    Cursor cursor;
    private ImageView sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        sair = (ImageView) findViewById(R.id.sair);

        DBHelper db = new DBHelper(getBaseContext());
        Intent intent = getIntent();
        int temp = intent.getIntExtra("codigo", 0);
        cursor = db.carregaDadoById(temp);

        String texto =  "Sucesso! Aluno(a) " + cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOME)) + " é aluno do curso " + cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CURSO)) + " e tem vínculo com a Instituição";

        initComponentes();
        gerarQRCode(texto);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(QrCode.this, Home.class);
                intent.putExtra("codigo", temp);
                startActivity(intent);
            }
        });
       // gerarQRCode(editQRCode.getText().toString());

   /* ORIGIN
       btnGerarQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(editQRCode.getText().toString())){
                    editQRCode.setError("*");
                    editQRCode.requestFocus();
                }else{
                    gerarQRCode(editQRCode.getText().toString());
                }
            }
        });*/
    }

    private void initComponentes() {
        imgQRCode = findViewById(R.id.imgQRCode);
    }

    private void gerarQRCode(String conteudoDoQRCode) {

        // zxing-android-embedded
        QRCodeWriter qrCode = new QRCodeWriter();

        try{
            BitMatrix bitMatrix = qrCode.encode(conteudoDoQRCode, BarcodeFormat.QR_CODE, 300, 300);

            int tamanho = bitMatrix.getWidth();
            int altura = bitMatrix.getHeight();

            Bitmap bitmap = Bitmap.createBitmap(tamanho,altura,Bitmap.Config.RGB_565);

            for (int x = 0; x < tamanho; x++) {
                for (int y = 0; y < altura; y++) {
                    bitmap.setPixel(x,y, bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
            }
            imgQRCode.setImageBitmap(bitmap);

        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}