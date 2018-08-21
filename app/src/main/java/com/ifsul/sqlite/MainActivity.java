package com.ifsul.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private ListView lista;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BancoController crud = new BancoController(getBaseContext());
        cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {
                CriaBanco.ID,
                CriaBanco.TITULO
        };
        int[] idViews = new int[] {R.id.idLivro, R.id.nomeLivro};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_item, cursor, nomeCampos, idViews, 0);

        lista = findViewById(R.id.listView);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String codigo;
                cursor.moveToPosition(i);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(getBaseContext(), AlterDataActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cadastrar:
                Intent intentInsert = new Intent(getBaseContext(), InsertDataActivity.class);
                startActivity(intentInsert);
                break;
            case R.id.alterar:
                Intent intentAlter = new Intent(getBaseContext(), AlterDataActivity.class);
                startActivity(intentAlter);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
