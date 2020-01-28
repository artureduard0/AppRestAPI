package com.example.consumindorestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView texto = findViewById(R.id.nome);
        String retorno = null;;

        try {
            retorno = new HttpRequisition().execute().get();
         }catch (Exception e){
            e.printStackTrace();
        }

        if(retorno != null){
            texto.setText(retorno);
        }
    }


}
