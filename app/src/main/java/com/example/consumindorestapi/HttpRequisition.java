package com.example.consumindorestapi;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequisition extends AsyncTask<Void,Void,String> {

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Create URL
            URL githubEndpoint = new URL("https://api.github.com/");

            // Create connection
            HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();

            // Inserir cabeçalho User-Agent e outros
            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            myConnection.setRequestProperty("Contact-Me", "hathibelagal@example.com");

            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject(); // Start processing the JSON object
                while (jsonReader.hasNext()) { // Loop through all keys
                    String key = jsonReader.nextName(); // Fetch the next key
                    if (key.equals("organization_url")) { // Check if desired key
                        // Fetch the value as a String
                        String value = jsonReader.nextString();

                        // Fechar as conexões
                        jsonReader.close();
                        myConnection.disconnect();

                        // fazer algo com o value
                        return value;
                       // break; // Break out of the loop
                    } else {
                        jsonReader.skipValue(); // Skip values of other keys
                    }
                }
            } else {
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
