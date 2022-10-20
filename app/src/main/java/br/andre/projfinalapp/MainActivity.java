package br.andre.projfinalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    private Button btnEntrar;
    private TextView txtCadastre_se;
    private ImageView imageView2;
    private UsuarioDAO objUsuarioDAO;
    private EditText edtUser, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnEntrar = findViewById(R.id.btnEntrar);
        objUsuarioDAO = new UsuarioDAO(MainActivity.this);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                List<Usuario> lista = objUsuarioDAO.listarTodosOsUsuarios();

                boolean naoEncontrou = true;

                for(int i = 0; i < lista.size() && naoEncontrou; i++) {
                    if (lista.get(i).getUsuario().equals(user) && lista.get(i).getSenha().equals(pass)) {
                        Intent intent = new Intent(MainActivity.this,OpcoesActivity.class);
                        startActivity(intent);
                        finish();
                        naoEncontrou = false;

                    }
                }

                if (naoEncontrou) {
                    Toast.makeText(MainActivity.this, "Usuários ou Senha incorreto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtCadastre_se = findViewById(R.id.txtCadastre_se);

        txtCadastre_se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CadUsuarioActivity.class);
                i.putExtra("novo_usuario", true);
                startActivity(i);
            }
        });

        imageView2 = findViewById(R.id.imageView2);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/AndreMR30")));
            }
        });

    }
}