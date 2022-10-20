package br.andre.projfinalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ComissaoActivity extends AppCompatActivity {

    private ImageButton imageHomeButton;
    private ImovelDAO objImovelDAO;
    private List<Imovel> todosValores;
    private TextView txtResultadoComissao;
    private Imovel objImovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comissao);
        getSupportActionBar().hide();

        imageHomeButton = findViewById(R.id.imageHomeButton);

        imageHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComissaoActivity.this, OpcoesActivity.class);
                startActivity(intent);
            }
        });

        txtResultadoComissao = findViewById(R.id.txtResultadoComissao);

        objImovelDAO = new ImovelDAO(getApplicationContext());

     txtResultadoComissao.setText("Valor total da comissão: \n\n" + "R$ " + objImovelDAO.somarComissao());
    }
}