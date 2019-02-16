package com.cursoandroid.sqlite.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private ListView listaSchedules;
    public SQLiteDatabase bancoDados;
    private ArrayAdapter<String> adaptadorSchedules;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            listaSchedules = (ListView) findViewById(R.id.listaSchedules);

            //CRIAR BANCO
            bancoDados = openOrCreateDatabase("scheduleApp", MODE_PRIVATE, null);

            //Criar Tabelas
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS schedules(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, description VARCHAR)");

            //Listar Schedules
            recuperarTarefas();

            //Deletar da lista
            listaSchedules.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removerSchedule(ids.get(position));
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void irParaTelaDeCriação(View view){

        Intent intent1 = new Intent(getApplicationContext(), CreateTask.class);
        startActivity(intent1);
    }

    private void recuperarTarefas(){

        try{

            Cursor cursorBanco = bancoDados.rawQuery("SELECT * FROM schedules ORDER BY id DESC", null);

            //Pegar indices de cada coluna
            int indiceColunaID = cursorBanco.getColumnIndex("id");
            int indiceColunaTitle = cursorBanco.getColumnIndex("title");
            int indiceColunaDescription = cursorBanco.getColumnIndex("description");

            //Lista
            listaSchedules = (ListView) findViewById(R.id.listaSchedules);

            //Criando adaptador
            itens = new ArrayList<String>();
            adaptadorSchedules = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_selectable_list_item,
                    android.R.id.text1,
                    itens);
            listaSchedules.setAdapter(adaptadorSchedules);

            ids = new ArrayList<Integer>();


            cursorBanco.moveToFirst();

            while(cursorBanco != null){
                Log.i("RESULTADO ID", cursorBanco.getString(indiceColunaID));
                Log.i("RESULTADO Title", cursorBanco.getString(indiceColunaTitle));
                Log.i("RESULTADO Description",cursorBanco.getString(indiceColunaDescription));
                itens.add(cursorBanco.getString(indiceColunaTitle));
                ids.add(Integer.parseInt(cursorBanco.getString(indiceColunaID)));
                cursorBanco.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void removerSchedule(Integer id){
        try{

            bancoDados.execSQL("DELETE FROM schedules WHERE id="+ id);
            recuperarTarefas();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
