package br.andre.projfinalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class OpcoesActivity extends AppCompatActivity {

    private Button btnCadastrarNovoUsuario;
    private Button btnCadastrarNovoImovel;
    private Button btnConUsuario;
    private Button btnConImovel;
    private Button btnConComissao;
    private TextView txtSair;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes);

        btnCadastrarNovoUsuario = findViewById(R.id.btnCadastrarNovoUsuario);

        btnCadastrarNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesActivity.this, CadUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnCadastrarNovoImovel = findViewById(R.id.btnCadastrarNovoImovel);

        btnCadastrarNovoImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpcoesActivity.this, CadImovelActivity.class);
                startActivity(intent);
            }
        });

        btnConUsuario = findViewById(R.id.btnConUsuario);

        btnConUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesActivity.this, ConUsuariosActivity.class);
                startActivity(intent);
            }
        });

        btnConImovel = findViewById(R.id.btnConImovel);

        btnConImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesActivity.this, ConImoveisActivity.class);
                startActivity(intent);
            }
        });

        btnConComissao = findViewById(R.id.btnConComissao);

        btnConComissao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesActivity.this, ComissaoActivity.class);
                startActivity(intent);
            }
        });

        txtSair = findViewById(R.id.txtSair);

        txtSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OpcoesActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}