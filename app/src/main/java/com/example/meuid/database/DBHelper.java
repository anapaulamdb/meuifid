package com.example.meuid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import com.example.meuid.R;
import com.example.meuid.bean.Aluno;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "meuifId-v1.db";
    public static final String TABLE_NAME = "aluno";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String CURSO = "curso";
    public static final String MATRICULA = "matricula";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String IMAGEM = "imagem";
    public static final String APROVADO = "aprovado";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOME + " TEXT NOT NULL, "
                + CPF + " TEXT NOT NULL, "
                + CURSO + " TEXT NOT NULL, "
                + MATRICULA + " INTEGER NOT NULL, "
                + EMAIL + " TEXT NOT NULL, "
                + SENHA + " TEXT NOT NULL, "
                + IMAGEM + " TEXT,"
                + APROVADO + " INTEGER"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String insereDado(String nome, String cpf, String curso, Integer matricula, String email, String senha, String imagem, Integer aprovado) {
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        long resultado;

        valores.put(DBHelper.NOME, nome);
        valores.put(DBHelper.CPF, cpf);
        valores.put(DBHelper.CURSO, curso);
        valores.put(DBHelper.MATRICULA, matricula);
        valores.put(DBHelper.EMAIL, email);
        valores.put(DBHelper.SENHA, senha);
        valores.put(DBHelper.IMAGEM, imagem);
        valores.put(DBHelper.APROVADO, aprovado);

        resultado = db.insert(DBHelper.TABLE_NAME, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir Registro";
        } else {
            return "Registro inserido com sucesso!";
        }
    }

    public Cursor carregaDadosAprovados() {
        Cursor cursor;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] campos = {DBHelper.NOME, DBHelper.CPF, DBHelper.CURSO, DBHelper.MATRICULA, DBHelper.EMAIL, DBHelper.SENHA, DBHelper.IMAGEM, DBHelper.APROVADO};
        String where = DBHelper.APROVADO + "=" + 1;
        cursor = db.query(DBHelper.TABLE_NAME, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosPendentes() {
        Cursor cursor;
        String[] campos = {DBHelper.NOME, DBHelper.MATRICULA};
        String where = DBHelper.APROVADO + "=" + 0;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.query(DBHelper.TABLE_NAME, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    // textNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NOME)));
    /*
    public List<Aluno> buscaAlunosPendentes(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM aluno WHERE aprovado = 0";
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();

        while (c.moveToNext()){
            Aluno al = new Aluno();
            al.setNome();
            al.setMatricula();
            al.setNome(String.valueOf(c.getColumnIndex(DBHelper.NOME)));
            al.setMatricula(Integer.valueOf(c.getString(c.getColumnIndex(DBHelper.MATRICULA))));
        }
        return alunos;
    }
*/

    public Cursor carregaDadoById(int id) {
        Cursor cursor;
        String[] campos = {DBHelper.NOME, DBHelper.CPF, DBHelper.CURSO, DBHelper.MATRICULA, DBHelper.EMAIL, DBHelper.SENHA, DBHelper.IMAGEM, DBHelper.APROVADO};
        String where = DBHelper.ID + "=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.query(DBHelper.TABLE_NAME, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Boolean checkUsuario(String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from aluno where nome = ?", new String[]{nome});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailSenha(String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + EMAIL + " = ? and " + SENHA + " = ?", new String[]{email, senha});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getIdAluno(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + ID + " from " + TABLE_NAME + " where " + EMAIL + " = ? and " + SENHA + " = ?", new String[]{email, senha});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void listarDados() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("select nome, matricula from aluno where aprovado = 0", null);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}