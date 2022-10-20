package br.andre.projfinalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CadImovelActivity extends AppCompatActivity {

    private ImageButton imageHomeButton;
    private EditText edtProprietario, edtTelefone1, edtTelefone2, edtCondominio, edtEndereco, edtData, edtValor, edtObservacao, edtStatusAluguel, edtAgenciador;
    private Button btnCadastrarNovoImovel;
    private ImovelDAO objImovelDAO;
    private Imovel objImovel = null;
    private TextView txtTituloCadImovel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_imovel);
        getSupportActionBar().hide();


        imageHomeButton = findViewById(R.id.imageHomeButton);

        imageHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadImovelActivity.this, OpcoesActivity.class);
                startActivity(intent);
            }
        });


        edtProprietario = findViewById(R.id.edtProprietario);
        edtTelefone1 = findViewById(R.id.edtTelefone1);
        edtTelefone2 = findViewById(R.id.edtTelefone2);
        edtCondominio = findViewById(R.id.edtCondominio);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtData = findViewById(R.id.edtData);
        edtValor = findViewById(R.id.edtValor);
        edtObservacao = findViewById(R.id.edtObservacao);
        edtStatusAluguel = findViewById(R.id.edtStatusAluguel);
        edtAgenciador = findViewById(R.id.edtAgenciador);


        btnCadastrarNovoImovel = findViewById(R.id.btnCadastrarNovoImovel);
        objImovelDAO = new ImovelDAO(CadImovelActivity.this);
        txtTituloCadImovel = findViewById(R.id.txtTituloCadImovel);

        Intent i = getIntent();

        if(i.hasExtra("imovel")) {
            objImovel = (Imovel) i.getSerializableExtra("imovel");
            edtProprietario.setText(objImovel.getProprietario());
            edtTelefone1.setText(objImovel.getTelefone1());
            edtTelefone2.setText(objImovel.getTelefone2());
            edtCondominio.setText(objImovel.getCondominio());
            edtEndereco.setText(objImovel.getEndereco());
            edtData.setText(objImovel.getData());
            edtValor.setText(String.valueOf(objImovel.getValor()));
            edtObservacao.setText(objImovel.getObservacao());
            edtStatusAluguel.setText(objImovel.getStatusAluguel());
            edtAgenciador.setText(objImovel.getAgenciador());


            imageHomeButton.setVisibility(ImageButton.VISIBLE);
            btnCadastrarNovoImovel.setText("Alterar");
            txtTituloCadImovel.setText("Alteração de Imóvel");

        } else if (i.hasExtra("imovel")) {
            imageHomeButton.setVisibility(ImageButton.INVISIBLE);

        }


            btnCadastrarNovoImovel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtValor.getText().toString().equals("")) {
                        edtValor.setError("Campo Obrigatório, informe um valor");
                    } else {
                        if (objImovel == null) {

                            objImovel = new Imovel();

                            objImovel.setProprietario(edtProprietario.getText().toString());
                            objImovel.setTelefone1(edtTelefone1.getText().toString());
                            objImovel.setTelefone2(edtTelefone2.getText().toString());
                            objImovel.setCondominio(edtCondominio.getText().toString());
                            objImovel.setEndereco(edtEndereco.getText().toString());
                            objImovel.setData(edtData.getText().toString());
                            objImovel.setValor(Double.valueOf(edtValor.getText().toString()));
                            objImovel.setObservacao(edtObservacao.getText().toString());
                            objImovel.setStatusAluguel(edtStatusAluguel.getText().toString());
                            objImovel.setAgenciador(edtAgenciador.getText().toString());


                            objImovelDAO.cadastrarImovel(objImovel);

                            Toast.makeText(CadImovelActivity.this, "Imóvel Cadastrado Com Sucesso", Toast.LENGTH_SHORT).show();

                            edtProprietario.setText("");
                            edtTelefone1.setText("");
                            edtTelefone2.setText("");
                            edtCondominio.setText("");
                            edtEndereco.setText("");
                            edtData.setText("");
                            edtValor.setText("");
                            edtObservacao.setText("");
                            edtStatusAluguel.setText("");
                            edtAgenciador.setText("");
                            edtProprietario.requestFocus();

                        } else {

                            objImovel.setProprietario(edtProprietario.getText().toString());
                            objImovel.setTelefone1(edtTelefone1.getText().toString());
                            objImovel.setTelefone2(edtTelefone2.getText().toString());
                            objImovel.setCondominio(edtCondominio.getText().toString());
                            objImovel.setEndereco(edtEndereco.getText().toString());
                            objImovel.setData(edtData.getText().toString());
                            objImovel.setValor(Double.valueOf(edtValor.getText().toString()));
                            objImovel.setObservacao(edtObservacao.getText().toString());
                            objImovel.setStatusAluguel(edtStatusAluguel.getText().toString());
                            objImovel.setAgenciador(edtAgenciador.getText().toString());

                            objImovelDAO.alterarImovel(objImovel);

                            Toast.makeText(CadImovelActivity.this, "Imóvel Alteredo Com Sucesso", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(new Intent(CadImovelActivity.this, ConImoveisActivity.class));

                        }
                    }
                }
        });



    }
}