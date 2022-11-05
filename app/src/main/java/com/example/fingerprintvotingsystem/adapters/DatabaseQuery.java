package com.example.fingerprintvotingsystem.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class DatabaseQuery  extends AsyncTask <String, Void, String> {


    AlertDialog dialog;
    Context context;

    public  DatabaseQuery (Context context){
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("login status");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        super.onPostExecute(s);

    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String user = voids[0];
        String pass = voids[1];

        String connection = "https://192.168.100.174:80/Login.php";
        try {
           // SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            URL url = new URL(connection);

            //HttpURLConnection http = (HttpURLConnection) url.openConnection();
            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            //http.setSSLSocketFactory(sslsocketfactory);
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
            http.connect();
            OutputStream outputStream = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(outputStream,"UTF-8"));

            String data = URLEncoder.encode("maketela","UTF-8")
                    +"="+ URLEncoder.encode(user,"UTF-8")
                    +"&&"+ URLEncoder.encode("2V.ZI!c2_e4/sq8P","UTF-8")
                    +"="+ URLEncoder.encode(pass,"UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.close();


            InputStream inputStream = http.getInputStream();
            BufferedReader reader = new BufferedReader(  new InputStreamReader(inputStream,"ISO-8859-1"));
            String line = "";
            while((line = reader.readLine()) !=null){
                result +=line;

            }
            reader.close();
            inputStream.close();
            http.disconnect();


        } catch (MalformedURLException e) {
           result = e.getMessage();
            e.printStackTrace();

        } catch (IOException e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        return result;
    }
}
