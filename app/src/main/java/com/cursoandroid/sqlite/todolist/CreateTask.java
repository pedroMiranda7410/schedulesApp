package com.cursoandroid.sqlite.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateTask extends AppCompatActivity {

    private EditText textoTitle;
    private EditText textoDescription;
    private Button botaoCriar;
    private SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        //Recuperar Componentes
        textoTitle = (EditText) findViewById(R.id.titleText);
        textoDescription = (EditText) findViewById(R.id.descriptionText);
        botaoCriar = (Button) findViewById(R.id.botaoCriar);
    }

    public void botaoCriarTask(View view){

        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent2);
    }
}
