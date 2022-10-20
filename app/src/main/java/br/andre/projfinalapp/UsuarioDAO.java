package br.andre.projfinalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private ConexaoBD conexaoBD;
    private SQLiteDatabase  bdAgenciador;

    public UsuarioDAO(Context contexto) {
        this.conexaoBD = new ConexaoBD(contexto);
        this.bdAgenciador = this.conexaoBD.getWritableDatabase();
    }

    public void cadastrarUsuario(Usuario objUsuario){
        ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("nome",objUsuario.getNome());
        valoresCampos.put("cpf",objUsuario.getCpf());
        valoresCampos.put("telefone",objUsuario.getTelefone());
        valoresCampos.put("usuario",objUsuario.getUsuario());
        valoresCampos.put("senha",objUsuario.getSenha());

        this.bdAgenciador.insert("tb_usuario", null,valoresCampos);

    }

    public List<Usuario> listarTodosOsUsuarios(){
        List<Usuario> todosUsuarios = new ArrayList<>();

        String [] campos = {"id", "nome", "cpf", "telefone", "usuario", "senha"};

        Cursor cursor = this.bdAgenciador.query("tb_usuario", campos, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
                Usuario objUsuario = new Usuario();

                objUsuario.setId(cursor.getInt(0));
                objUsuario.setNome(cursor.getString(1));
                objUsuario.setCpf(cursor.getLong(2));
                objUsuario.setTelefone(cursor.getString(3));
                objUsuario.setUsuario(cursor.getString(4));
                objUsuario.setSenha(cursor.getString(5));

                todosUsuarios.add(objUsuario);
        }

        return todosUsuarios;

    }



    public void excluirUsuario(Usuario objUsuario) {

        String [] id = {String.valueOf(objUsuario.getId())};

        this.bdAgenciador.delete("tb_usuario", "id = ?",id);

    }

    public void alterarUsuario(Usuario objUsuario) {

        ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("nome",objUsuario.getNome());
        valoresCampos.put("cpf",objUsuario.getCpf());
        valoresCampos.put("telefone",objUsuario.getTelefone());
        valoresCampos.put("usuario",objUsuario.getUsuario());
        valoresCampos.put("senha",objUsuario.getSenha());

        String [] id = {String.valueOf(objUsuario.getId())};
        this.bdAgenciador.update("tb_usuario",valoresCampos,"id = ?",id);

    }
}
