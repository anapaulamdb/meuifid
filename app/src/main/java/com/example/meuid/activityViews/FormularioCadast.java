package com.example.meuid.activityViews;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.meuid.R;
import com.example.meuid.database.DBHelper;

import java.io.ByteArrayOutputStream;

public class FormularioCadast extends AppCompatActivity {

    EditText edit_nome;
    EditText cpf;
    EditText curso;
    EditText unidade;
    EditText matricula;
    EditText edit_email;
    EditText edit_senha;
    ImageView uploadImage;
    ImageView imgSelected;
    Button bt_cadastrar;
    byte[] img;

    ImageView uploadImg, photo;
    private final int GALLERY_IMAGES = 1;
    private final int PERMISSION_REQUEST = 2;
    String fotoStringFinal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadast);

        getSupportActionBar().hide();
        // imagem
        uploadImg = findViewById(R.id.uploadImage);
        photo = findViewById(R.id.imgSelected);

        // database
        edit_nome = (EditText) findViewById(R.id.edit_nome);
        cpf = (EditText) findViewById(R.id.cpf);
        matricula = (EditText) findViewById(R.id.matricula);
        unidade = (EditText) findViewById(R.id.unidade);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_senha = (EditText) findViewById(R.id.edit_senha);
        curso = (EditText) findViewById(R.id.curso);
        uploadImage = (ImageView) findViewById(R.id.uploadImage);
        imgSelected = (ImageView) findViewById(R.id.imgSelected);
        bt_cadastrar = (Button) findViewById(R.id.bt_cadastrar);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        }

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_IMAGES);
            }
        });
        /*
        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (!(edit_nome.getText().toString().equals("") || cpf.getText().toString().equals("") || matricula.getText().toString().equals("") || curso.getText().toString().equals("") || edit_email.getText().toString().equals("") || edit_senha.getText().toString().equals(""))){
                // || imageView4.getText().toString().equals(""))) {
                String resultado;
                DBHelper crud = new DBHelper(getBaseContext());
                Bitmap bitmap = ((BitmapDrawable)imgSelected.getDrawable()).getBitmap();
                ByteArrayOutputStream saida = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
                byte[] img = saida.toByteArray();

                System.out.println(edit_nome);
                System.out.println(cpf);
                System.out.println(matricula);

                //SALVA INFOS NO BANCO
                //resultado = crud.insereDado(edit_nome.getText().toString(), Integer.valueOf(matricula.getText().toString()), cpf.getText().toString(), curso.getText().toString(), unidade.getText().toString(), edit_email.getText().toString(), edit_senha.getText().toString(), String.valueOf(img), 0);
                //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                //limpaFormulario();

                /*
                Intent intent = new Intent(FormularioCadast.this, Login.class);
                startActivity(intent);


            }
        });
*/
        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_nome.getText().toString().equals("") || cpf.getText().toString().equals("") ||matricula.getText().toString().equals("") ||
                        curso.getText().toString().equals("") || edit_email.getText().toString().equals("") ||
                        edit_senha.getText().toString().equals("")){
                    //|| img.getText().toString().equals("")) ; || imgSelected.getBackground() == null
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                } else {
                    String resultado;
                    DBHelper crud = new DBHelper(getBaseContext());

                    //arrayToByte image
                    Bitmap bitmap = ((BitmapDrawable)imgSelected.getDrawable()).getBitmap();
                    ByteArrayOutputStream saida = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
                    img = saida.toByteArray();

                    resultado = crud.insereDado(edit_nome.getText().toString(), Integer.valueOf(matricula.getText().toString()), cpf.getText().toString(), curso.getText().toString(), unidade.getText().toString(), edit_email.getText().toString(), edit_senha.getText().toString(), String.valueOf(img), 0);
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    limpaFormulario();
                    abrirDialogo();
                }
            }
        });

    }

    public void abrirDialogo(){
        //instancia para o alerta do dialogo
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        //titulo e mensagem
        dialogo.setTitle("Atenção");
        dialogo.setMessage("Aguarde o ADMINISTRADOR aceitar seu cadastro!");

        //voltar ao inicio po meio de cancelamento

        dialogo.setCancelable(false);

        //configurar icone
        dialogo.setIcon(android.R.drawable.ic_dialog_alert);

        //botoes
        dialogo.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Informações salvas com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FormularioCadast.this, Login.class);
                startActivity(intent) ;
            }
        });

        //criar e exibir dialogo
        dialogo.create();
        dialogo.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALLERY_IMAGES) {
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            photo.setImageBitmap(thumbnail);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast t = Toast.makeText(getApplicationContext(),
                        "O acesso a galeria de fotos foi negado.",
                        Toast.LENGTH_LONG);
                t.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                t.show();
            }
            return;
        }

    }

    private void limpaFormulario() {
        edit_nome.setText("");
        edit_nome.requestFocus();
        matricula.setText("");
        curso.setText("");
        unidade.setText("");
        edit_email.setText("");
        edit_senha.setText("");
        cpf.setText("");
        imgSelected.setImageDrawable(null);
    }
}