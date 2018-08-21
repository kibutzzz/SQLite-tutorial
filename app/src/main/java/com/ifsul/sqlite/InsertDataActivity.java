package com.ifsul.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        Button botao = (Button) findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoController crud = new BancoController(getBaseContext());
                EditText titulo = findViewById(R.id.editText);
                EditText autor =  findViewById(R.id.editText2);
                EditText editora = findViewById(R.id.editText3);
                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraString = autor.getText().toString();

                String resultado;

                resultado = crud.insereDado(tituloString, autorString, editoraString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            }
        });
    }
}
