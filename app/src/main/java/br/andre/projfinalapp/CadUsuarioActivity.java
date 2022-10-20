package br.andre.projfinalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CadUsuarioActivity extends AppCompatActivity {

        private ImageButton imageHomeButton;
        private EditText edtNome, edtCpf, edtTelefone, edtUsuario, edtSenha;
        private Button btnCadastrar;
        private UsuarioDAO objUsuarioDAO;
        private Usuario objUsuario = null;
        private TextView txtTituloCad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        getSupportActionBar().hide();

        imageHomeButton = findViewById(R.id.imageHomeButton);

        imageHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadUsuarioActivity.this, OpcoesActivity.class);
                startActivity(intent);
            }
        });

        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtPass);


        btnCadastrar = findViewById(R.id.btnCadastrarNovoUsuario);
        objUsuarioDAO = new UsuarioDAO(CadUsuarioActivity.this);
        txtTituloCad = findViewById(R.id.txtTituloCad);

        Intent i = getIntent();

        if(i.hasExtra("usuario")) {
            objUsuario = (Usuario) i.getSerializableExtra("usuario");
            edtNome.setText(objUsuario.getNome());
            edtCpf.setText(String.valueOf(objUsuario.getCpf()));
            edtTelefone.setText(objUsuario.getTelefone());
            edtUsuario.setText(objUsuario.getUsuario());
            edtSenha.setText(objUsuario.getSenha());

            imageHomeButton.setVisibility(ImageButton.VISIBLE);
            btnCadastrar.setText("Alterar");
            txtTituloCad.setText("Alteração de Cadastro");

        } else if (i.hasExtra("novo_usuario")) {
            imageHomeButton.setVisibility(ImageButton.INVISIBLE);

        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtCpf.getText().toString().equals("")) {
                    edtCpf.setError("Campo Obrigatório, informe um CPF");
                } else {

                    if (objUsuario == null) {

                        objUsuario = new Usuario();

                        objUsuario.setNome(edtNome.getText().toString());
                        objUsuario.setCpf(Long.parseLong(edtCpf.getText().toString()));
                        objUsuario.setTelefone(edtTelefone.getText().toString());
                        objUsuario.setUsuario(edtUsuario.getText().toString());
                        objUsuario.setSenha(edtSenha.getText().toString());

                        objUsuarioDAO.cadastrarUsuario(objUsuario);

                        Toast.makeText(CadUsuarioActivity.this, "Usuário Cadastrado Com Sucesso", Toast.LENGTH_SHORT).show();

                        edtNome.setText("");
                        edtCpf.setText("");
                        edtTelefone.setText("");
                        edtUsuario.setText("");
                        edtSenha.setText("");
                        edtNome.requestFocus();

                        if (i.hasExtra("novo_usuario")) {
                            finish();
                        }

                    } else {

                        objUsuario.setNome(edtNome.getText().toString());
                        objUsuario.setCpf(Long.parseLong(edtCpf.getText().toString()));
                        objUsuario.setTelefone(edtTelefone.getText().toString());
                        objUsuario.setUsuario(edtUsuario.getText().toString());
                        objUsuario.setSenha(edtSenha.getText().toString());

                        objUsuarioDAO.alterarUsuario(objUsuario);

                        Toast.makeText(CadUsuarioActivity.this, "Usuário(a) Alteredo Com Sucesso", Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(new Intent(CadUsuarioActivity.this, ConUsuariosActivity.class));

                    }
                }
            }
        });



    }
}