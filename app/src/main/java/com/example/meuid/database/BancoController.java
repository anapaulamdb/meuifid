package com.example.meuid.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private DBHelper banco;
    private SQLiteDatabase db;

    public String insereDado(String nome, Integer matricula, String cpf, String curso, String campus, String email, String senha, String imagem, Integer aprovado) {
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = banco.getWritableDatabase();
        long resultado;

        valores.put(banco.NOME, nome);
        valores.put(banco.MATRICULA, matricula);
        valores.put(banco.CPF, cpf);
        valores.put(banco.CURSO, curso);
        valores.put(banco.CAMPUS, campus);
        valores.put(banco.EMAIL, email);
        valores.put(banco.SENHA, senha);
        valores.put(banco.IMAGEM, imagem);
        valores.put(banco.APROVADO, aprovado);

        resultado = db.insert(DBHelper.TABLE_NAME, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir Registro";
        } else {
            return "Registro inserido com sucesso!";
        }
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABLE_NAME, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.MATRICULA, banco.CPF, banco.CURSO, banco.CAMPUS, banco.EMAIL, banco.SENHA, banco.IMAGEM, banco.APROVADO};
        String where = banco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABLE_NAME,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    // CHECK USER

    public Boolean checkUsuario(String nome) {
        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from aluno where nome = ?", new String[] {nome});

        if(cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailSenha(String email, String senha){
        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ banco.TABLE_NAME + " where "+ banco.EMAIL + " = ? and "+ banco.SENHA+" = ?", new String[] {email, senha});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }
}

//String nome, Integer matricula, String cpf, String curso, String campus, String email, String senha, String imagem, Integer aprovado)
