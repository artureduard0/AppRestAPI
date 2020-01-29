package com.example.consumindorestapi;

import android.os.AsyncTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;

import android.os.Build;
import android.util.JsonReader;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequisition extends AsyncTask<Void, Void, ArrayList<String>> {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        try {
            // Create URL
            URL myApiEndpoint = new URL("https://app-subsea-homolog.wideds.com.br/wp-json/wp/v2/posts");
            // Autenticação
            String userCredentials = "72902175000:123456";
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

            // Create connection
            HttpsURLConnection myConnection = (HttpsURLConnection) myApiEndpoint.openConnection();

            // Inserir cabeçalho User-Agent e outros
            myConnection.setRequestProperty("Authorization", basicAuth);
            myConnection.setRequestMethod("GET");
            myConnection.setRequestProperty("Content-Type", "application/json");

            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginArray();

                //lista
                ArrayList<String> list = new ArrayList<>();

                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String key = jsonReader.nextName();
                    String value = jsonReader.nextString();
                    list.add(value);
                }
                jsonReader.endArray();

                // Fechar as conexões
                jsonReader.close();
                myConnection.disconnect();
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
