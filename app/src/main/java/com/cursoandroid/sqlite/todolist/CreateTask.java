package com.cursoandroid.sqlite.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateTask extends AppCompatActivity {

    private EditText textoTitle;
    private EditText textoDescription;
    private Button botaoCriar;
    //public MainActivity objetoMainActivity = new MainActivity();
    public SQLiteDatabase bancoDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    try {
        //Recuperar Componentes
        textoTitle = (EditText) findViewById(R.id.titleText);
        textoDescription = (EditText) findViewById(R.id.descriptionText);
        botaoCriar = (Button) findViewById(R.id.botaoCriar);

        bancoDados = openOrCreateDatabase("scheduleApp", MODE_PRIVATE, null);



        /* EVENTO ON CLICK NO BOTAO SEM CRIAR FUNÇÃO

         * botaoAdicionar.setOnClickListener(new View.OnClickListener() {
         *
         * @Override
         * public void onClick(View v) {
         *
         *
         * }
         * });
         *
         * */
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void botaoCriarTask(View view){

        String textoTitleDigitado = textoTitle.getText().toString();
        String textoDescriptionDigitado = textoDescription.getText().toString();
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Erro");
        //define a mensagem
        builder.setMessage("Preencha todos os campos para criar");

        if(textoTitleDigitado.compareTo("") != 0) {
            if (textoDescriptionDigitado.compareTo("") != 0) {
                try {

                    bancoDados.execSQL("INSERT INTO schedules(title,description) VALUES ('" + textoTitleDigitado + "', '" + textoDescriptionDigitado + "')");
                    Toast.makeText(CreateTask.this, "Schedule saved successfully", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();
            }
        }
        else if(textoDescriptionDigitado.compareTo("") != 0){
            if (textoTitleDigitado.compareTo("") != 0) {
                try {

                    bancoDados.execSQL("INSERT INTO schedules(title,description) VALUES ('" + textoTitleDigitado + "', '" + textoDescriptionDigitado + "')");
                    Toast.makeText(CreateTask.this, "Schedule saved successfully", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();
            }
        }
        else{
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();
        }



    }

    public void botaoCancelarTask(View view){

        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent2);
    }
}
