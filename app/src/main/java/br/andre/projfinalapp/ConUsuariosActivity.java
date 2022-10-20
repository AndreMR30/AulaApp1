package br.andre.projfinalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConUsuariosActivity extends AppCompatActivity {

    private ListView conUsuarios;
    private UsuarioDAO objUsuarioDAO;
    private MenuItem icCadastrar, moAlterar, moExcluir;
    private SearchView icPesquisar;
    private List<Usuario> todosUsuarios;
    private List<Usuario> usuariosFiltrados = new ArrayList<>();
    private MenuInflater mi;
    private ImageButton imageHomeButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_usuarios);

        imageHomeButton = findViewById(R.id.imageHomeButton);

        imageHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConUsuariosActivity.this, OpcoesActivity.class);
                startActivity(intent);
            }
        });

        conUsuarios = findViewById(R.id.conUsuarios);
        objUsuarioDAO = new UsuarioDAO(getApplicationContext());

        todosUsuarios = objUsuarioDAO.listarTodosOsUsuarios();

        usuariosFiltrados.addAll(todosUsuarios);

        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<>(ConUsuariosActivity.this, android.R.layout.simple_list_item_1, usuariosFiltrados);

        conUsuarios.setAdapter(adaptador);

        mi = getMenuInflater();

        registerForContextMenu(conUsuarios);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mi.inflate(R.menu.menu_superior,menu);

        icCadastrar = menu.findItem(R.id.icCadastrar);
        icPesquisar = (SearchView) menu.findItem(R.id.icPesquisar).getActionView();

        icCadastrar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(), CadUsuarioActivity.class));
                finish();
                return false;
            }
        });

        icPesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                procurarUsuarioPorNome(s);

                return false;
            }
        });



        return true;
    }

    public void procurarUsuarioPorNome(String nome) {
        usuariosFiltrados.clear();
        for (int i = 0; i < todosUsuarios.size(); i++) {
            if (todosUsuarios.get(i).getNome().toLowerCase().contains(nome.toLowerCase())) {
                usuariosFiltrados.add(todosUsuarios.get(i));
            }
        }

        conUsuarios.invalidateViews();

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        mi.inflate(R.menu.menu_opcoes,menu);

        moAlterar = menu.findItem(R.id.moAlterar);
        moExcluir = menu.findItem(R.id.moExcluir);

        moExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo usuarioEscolhido = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Usuario objUsuarioAExcluir = usuariosFiltrados.get(usuarioEscolhido.position);

                AlertDialog confirmacao = new AlertDialog.Builder(ConUsuariosActivity.this)
                .setTitle("Atenção")
                .setMessage("Deseja realente Excluir o(a) Usuário(o) " + objUsuarioAExcluir.getNome())
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todosUsuarios.remove(objUsuarioAExcluir);
                        usuariosFiltrados.remove(objUsuarioAExcluir);
                        objUsuarioDAO.excluirUsuario(objUsuarioAExcluir);


                AlertDialog ExclusaoOK = new AlertDialog.Builder(ConUsuariosActivity.this)
                    .setMessage("Usuário(a) " + objUsuarioAExcluir.getNome()+ " excluido com sucesso!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        conUsuarios.invalidateViews();
                        }
                    })
                .create();
                ExclusaoOK.show();
                            }
                        })
                        .create();
                confirmacao.show();

                return false;
            }
        });

        moAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo usuarioEscolhido = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Usuario objUsuarioAAlterar = usuariosFiltrados.get(usuarioEscolhido.position);

                Intent i = new Intent(ConUsuariosActivity.this,CadUsuarioActivity.class);
                i.putExtra("usuario",objUsuarioAAlterar);
                startActivity(i);

                return false;
            }
        });

    }



}
