package com.example.consumindorestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView texto = findViewById(R.id.nome);
        ArrayList<String> retorno = null;;

        try {
            retorno = new HttpRequisition().execute().get();
         }catch (Exception e){
            e.printStackTrace();
        }

        if(retorno != null){
            Log.i("teste" ," "+ retorno.size());
        }else{
            Log.i("erro"," erro");
        }
    }


}
