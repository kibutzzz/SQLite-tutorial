package com.ifsul.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterDataActivity extends AppCompatActivity {

    EditText livro;
    EditText autor;
    EditText editora;
    Button alterar;
    Button deletar;

    Cursor cursor;
    String codigo;

    BancoController crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_data);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        livro = findViewById(R.id.editText4);
        autor = findViewById(R.id.editText5);
        editora = findViewById(R.id.editText6);

        alterar = findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.alterarRegistro(Integer.parseInt(codigo),
                        livro.getText().toString(),
                        autor.getText().toString(),
                        editora.getText().toString());
                startActivity(new Intent(AlterDataActivity.this, MainActivity.class));
                finish();
            }
        });

        deletar = findViewById(R.id.button3);

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                startActivity(new Intent(AlterDataActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
