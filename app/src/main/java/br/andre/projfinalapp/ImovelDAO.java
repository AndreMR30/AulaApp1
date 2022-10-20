package br.andre.projfinalapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {

    private ConexaoBD conexaoBD;
    private SQLiteDatabase bdAgenciador;

    public ImovelDAO(Context contexto) {
        this.conexaoBD = new ConexaoBD(contexto);
        this.bdAgenciador = this.conexaoBD.getWritableDatabase();
    }

    public void cadastrarImovel(Imovel objImovel){
        ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("proprietario",objImovel.getProprietario());
        valoresCampos.put("telefone1",objImovel.getTelefone1());
        valoresCampos.put("telefone2",objImovel.getTelefone2());
        valoresCampos.put("condominio",objImovel.getCondominio());
        valoresCampos.put("endereco",objImovel.getEndereco());
        valoresCampos.put("data", objImovel.getData());
        valoresCampos.put("valor", objImovel.getValor());
        valoresCampos.put("observacao", objImovel.getObservacao());
        valoresCampos.put("statusAluguel", objImovel.getStatusAluguel());
        valoresCampos.put("agenciador", objImovel.getAgenciador());

        this.bdAgenciador.insert("tb_imovel", null,valoresCampos);

    }

    public List<Imovel> listarTodosOsImoveis(){
        List<Imovel> todosImoveis = new ArrayList<>();

        String [] campos = {"id", "proprietario", "telefone1", "telefone2", "condominio", "endereco", "data", "valor",  "observacao", "statusAluguel", "agenciador"};

        Cursor cursor = this.bdAgenciador.query("tb_imovel", campos, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Imovel objImovel = new Imovel();

            objImovel.setId(cursor.getInt(0));
            objImovel.setProprietario(cursor.getString(1));
            objImovel.setTelefone1(cursor.getString(2));
            objImovel.setTelefone2(cursor.getString(3));
            objImovel.setCondominio(cursor.getString(4));
            objImovel.setEndereco(cursor.getString(5));
            objImovel.setData(cursor.getString(6));
            objImovel.setValor(cursor.getDouble(7));
            objImovel.setObservacao(cursor.getString(8));
            objImovel.setStatusAluguel(cursor.getString(9));
            objImovel.setAgenciador(cursor.getString(10));

            todosImoveis.add(objImovel);
        }

        return todosImoveis;

    }

    public String somarComissao() {
        Cursor cursor = bdAgenciador.rawQuery("SELECT SUM(valor * 0.20) FROM tb_imovel", null);

        double valor;

        cursor.moveToFirst();
        valor = cursor.getInt(0);
        cursor.close();

        return String.valueOf(valor);
    }


    public void excluirImovel(Imovel objImovel) {

        String [] id = {String.valueOf(objImovel.getId())};

        this.bdAgenciador.delete("tb_imovel", "id = ?",id);

    }

    public void alterarImovel(Imovel objImovel) {

        ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("proprietario",objImovel.getProprietario());
        valoresCampos.put("telefone1",objImovel.getTelefone1());
        valoresCampos.put("telefone2",objImovel.getTelefone2());
        valoresCampos.put("condominio",objImovel.getCondominio());
        valoresCampos.put("endereco",objImovel.getEndereco());
        valoresCampos.put("data", objImovel.getData());
        valoresCampos.put("valor", objImovel.getValor());
        valoresCampos.put("observacao", objImovel.getObservacao());
        valoresCampos.put("statusAluguel", objImovel.getStatusAluguel());
        valoresCampos.put("agenciador", objImovel.getAgenciador());

        String [] id = {String.valueOf(objImovel.getId())};
        this.bdAgenciador.update("tb_imovel",valoresCampos,"id = ?",id);

    }

}
