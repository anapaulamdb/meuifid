package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meuid.R;
import com.example.meuid.database.DBHelper;

public class Login extends AppCompatActivity {

    private TextView text_tela_cadastro;

    EditText email, senha;
    Button button;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        button = (Button) findViewById(R.id.bt_entrar);
        db = new DBHelper(this);

        getSupportActionBar().hide();
        IniciarComponentes();

        // login
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String senha1 = senha.getText().toString();

                if(email1.equals("")||senha1.equals(""))
                    Toast.makeText(Login.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = db.checkEmailSenha(email1, senha1);
                    if(checkuserpass==true){
                        Cursor cursor = db.getIdAluno(email1, senha1);
                        Integer codigo = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.ID));
                        if(email1.equals("admin")) {
                            Intent intent = new Intent(Login.this, TelaAdm.class);
                            intent.putExtra("codigo", codigo);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Login.this, TelaPrincipal.class);
                            intent.putExtra("codigo", codigo);
                            startActivity(intent);
                        }
                        Toast.makeText(Login.this, "Login bem sucedido.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this, "Credenciais inválidas.", Toast.LENGTH_SHORT).show();
                        limpaFormulario();
                    }
                }
            }
        });

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, FormularioCadast.class);
                startActivity(intent);
            }
        });


    }

    private void IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.txt_telacadastro);
    }
    private void limpaFormulario() {
        email.setText("");
        senha.setText("");
        email.requestFocus();
    }
}