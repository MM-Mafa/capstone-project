package com.example.fingerprintvotingsystem;


import static com.example.fingerprintvotingsystem.adapters.UserInfo.userInfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingerprintvotingsystem.adapters.DBHelper;
import com.example.fingerprintvotingsystem.adapters.DatabaseQuery;
import java.sql.*;
public class Login extends AppCompatActivity {
    TextView login,stringSignup;
    EditText email, pass;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_button);
        stringSignup = findViewById(R.id.stringSignup);
        email  = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        dbHelper = new DBHelper(this);

        stringSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity( new Intent(Login.this, Signup.class ));

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userExists(email.getText().toString())) {
                    userInfo.put("email", email.getText().toString());
                    startActivity(new Intent(Login.this, Home.class).putExtra("isNewUser", 0));
                }
            }
        });
    }


    private boolean userExists(String email){

        StringBuffer buffer = new StringBuffer();

        Cursor data = dbHelper.getDataFromCitizenByEmail(email,false);
        if(data !=null){
            while((data.moveToNext())) {
                buffer.append(data.getString(0));
            }
            if(buffer.toString().equals(pass.getText().toString())){
                return true;
            }
            Toast.makeText(this, "email/password incorrect!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "email/password incorrect!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}