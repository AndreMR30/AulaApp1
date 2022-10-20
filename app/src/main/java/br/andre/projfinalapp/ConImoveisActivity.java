package br.andre.projfinalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class ConImoveisActivity extends AppCompatActivity {

    private ListView conImoveis;
    private ImovelDAO objImovelDAO;
    private MenuItem icCadastrar, moAlterar, moExcluir;
    private SearchView icPesquisar;
    private List<Imovel> todosImoveis;
    private List<Imovel> imoveisFiltrados = new ArrayList<>();
    private MenuInflater mi;
    private ImageButton imageHomeButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_imoveis);

        imageHomeButton = findViewById(R.id.imageHomeButton);

        imageHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ConImoveisActivity.this, OpcoesActivity.class);
                startActivity(intent);
            }
        });

        conImoveis = findViewById(R.id.conImoveis);
        objImovelDAO = new ImovelDAO(getApplicationContext());

        todosImoveis = objImovelDAO.listarTodosOsImoveis();

        imoveisFiltrados.addAll(todosImoveis);

        ArrayAdapter<Imovel> adaptador = new ArrayAdapter<>(ConImoveisActivity.this, android.R.layout.simple_list_item_1, imoveisFiltrados);

        conImoveis.setAdapter(adaptador);

        mi = getMenuInflater();

        registerForContextMenu(conImoveis);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mi.inflate(R.menu.menu_superior,menu);

        icCadastrar = menu.findItem(R.id.icCadastrar);
        icPesquisar = (SearchView) menu.findItem(R.id.icPesquisar).getActionView();

        icCadastrar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(), CadImovelActivity.class));
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

                procurarImovelPorNome(s);

                return false;
            }
        });



        return true;
    }

    public void procurarImovelPorNome(String proprietario) {
        imoveisFiltrados.clear();
        for (int i = 0; i < todosImoveis.size(); i++) {
            if (todosImoveis.get(i).getProprietario().toLowerCase().contains(proprietario.toLowerCase())) {
                imoveisFiltrados.add(todosImoveis.get(i));
            }
        }

        conImoveis.invalidateViews();

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
                AdapterView.AdapterContextMenuInfo imovelEscolhido = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Imovel objImovelAExcluir = imoveisFiltrados.get(imovelEscolhido.position);

                AlertDialog confirmacao = new AlertDialog.Builder(ConImoveisActivity.this)
                        .setTitle("Atenção")
                        .setMessage("Deseja realente Excluir o Imóvel do proprietário " + objImovelAExcluir.getProprietario())
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                todosImoveis.remove(objImovelAExcluir);
                                imoveisFiltrados.remove(objImovelAExcluir);
                                objImovelDAO.excluirImovel(objImovelAExcluir);


                                AlertDialog ExclusaoOK = new AlertDialog.Builder(ConImoveisActivity.this)
                                        .setMessage("O Imóvel do proprietário " + objImovelAExcluir.getProprietario()+ " foi excluido com sucesso!")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                conImoveis.invalidateViews();
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
                AdapterView.AdapterContextMenuInfo imovelEscolhido = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Imovel objImovelAAlterar = imoveisFiltrados.get(imovelEscolhido.position);

                Intent i = new Intent(ConImoveisActivity.this,CadImovelActivity.class);
                i.putExtra("imovel",objImovelAAlterar);
                startActivity(i);

                return false;
            }
        });


    }
}