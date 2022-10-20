package br.andre.projfinalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexaoBD extends SQLiteOpenHelper {

    private static final String Name = "bdAgenciador";
    private static final int VERSION = 1;

    public ConexaoBD(Context contexto) {
        super(contexto, Name, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase bdAgenciador) {
        bdAgenciador.execSQL("create table tb_usuario(id integer primary key autoincrement, " +
                "nome varchar(100)," +
                "cpf bigint," +
                "telefone varchar(50)," +
                "usuario varchar(20)," +
                "senha varchar(16))");

        bdAgenciador.execSQL("create table tb_imovel(id integer primary key autoincrement, " +
                "proprietario varchar(100)," +
                "telefone1 varchar(20)," +
                "telefone2 varchar(20)," +
                "condominio varchar(50)," +
                "endereco varchar(100)," +
                "data varchar(20)," +
                "valor varchar(20)," +
                "observacao varchar(100)," +
                "statusAluguel varchar(20)," +
                "agenciador varchar(30))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bdAgenciador, int i, int i1) {

    }
}
