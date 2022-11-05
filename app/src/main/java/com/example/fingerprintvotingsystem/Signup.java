package com.example.fingerprintvotingsystem;

import static com.example.fingerprintvotingsystem.adapters.UserInfo.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingerprintvotingsystem.adapters.DBHelper;

public class Signup extends AppCompatActivity {
    TextView signup, stringLogin;
    EditText name, surname, email, password, id, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.signup_button);
        stringLogin = findViewById(R.id.stringLogin);
        name = findViewById(R.id.userNameEditText);
        surname = findViewById(R.id.userSurnameEditText);
        address = findViewById(R.id.userAddressEditText);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        id = findViewById(R.id.userIdEditText);


        DBHelper dbHelper = new DBHelper(this);

        stringLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Signup.this, Login.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uName = name.getText().toString();
                String uSurname = surname.getText().toString();
                String uEmail = email.getText().toString();
                String uId = id.getText().toString();
                String uPassword = password.getText().toString();
                String uAddress = address.getText().toString();
                if(!email.getText().toString().contains("@")) {
                    Toast.makeText(Signup.this, "email is incorrect", Toast.LENGTH_SHORT).show();
                }
                else if (uEmail.length()>1 && uName.length()>0 && uSurname.length()>0 && uId.length()==13 && uPassword.length()>0 && uAddress.length()>0){
                    boolean inserted = dbHelper.insertDataToCitizen(uName,uSurname,uId,uEmail,uPassword,-1);

                    if(inserted){
                        Toast.makeText(Signup.this, "successfully created account", Toast.LENGTH_SHORT).show();
                        userInfo.put("id",uId);
                        startActivity(new Intent(Signup.this,Home.class).putExtra("isNewUser",1));
                    }

                }
                else {
                    Toast.makeText(Signup.this, "please make sure all details are correct!", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}