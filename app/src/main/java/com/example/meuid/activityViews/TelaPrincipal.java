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
        textValidade = (TextView) findViewById(R.id.textValidade);
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
        textUnidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CAMPUS)));
        imageView.setImageBitmap(decodedByte);

        //imageView.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CAMPUS)));
       // textValidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.)));
        textValidade.setText("00/00/0000");
        //imageView.setImageDrawable(foto);

        /*

        Intent intent = getIntent();
        int temp = intent.getIntExtra("int_value", 0);
        //alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(temp);
        textNome
        -----

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        livro = (EditText)findViewById(R.id.editText4);
        autor = (EditText)findViewById(R.id.editText5);
        editora = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

    }
         */
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
/*

        //alterar = (Button)findViewById(R.id.button2);


        textNome
        -----

        codigo = this.getIntent().getStringExtra("codigo");

        Bitmap raw;
        byte[] fotoArray = cursor.getColumnIndexOrThrow(DBHelper.IMAGEM);
        ImageView foto = imageView.findViewById(R.id.imageView);
        if(fotoArray!=null) {
            raw  = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
            foto.setImageBitmap(raw);
        }


         //String imagemBanco = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGEM));
        //imageBytes = Base64.decode(imagemBanco, Base64.DEFAULT);
       // Bitmap imagemDecode = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        //imageView.setImageBitmap(imagemDecode);


                           Bitmap bitmap = ((BitmapDrawable)imgSelected.getDrawable()).getBitmap();
                    ByteArrayOutputStream saida = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
                    img = saida.toByteArray();
                    String fotoSaida = Base64.encodeToString(img, Base64.DEFAULT);
 */