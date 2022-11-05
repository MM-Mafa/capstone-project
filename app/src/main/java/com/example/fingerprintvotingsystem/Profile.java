package com.example.fingerprintvotingsystem;

import static com.example.fingerprintvotingsystem.adapters.UserInfo.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingerprintvotingsystem.adapters.DBHelper;

import java.util.Enumeration;

public class Profile extends AppCompatActivity {
    TextView name, surname,mail, address, membership;
    Cursor data;
    String userEmail="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profle);
        name = findViewById(R.id.show_name_textView);
        surname = findViewById(R.id.show_surname_textView);
        address = findViewById(R.id.show_address_textView);
        mail = findViewById(R.id.show_email_textView);
        membership = findViewById(R.id.show_party_textView);
        DBHelper dbHelper = new DBHelper(this);
        String userMail = (String) userInfo.get("email");
        String userId = (String) userInfo.get("id");
        if(userId!=null && userId.length()>0){
            data = dbHelper.getDataFromCitizenById(userId);
        }
        else if ( userMail!=null && userMail.length()>0 ){
            data = dbHelper.getDataFromCitizenByEmail( userMail,true);
            userEmail =userMail;
        }

        if(data!=null){
            while((data.moveToNext())) {
                name.setText("name: "+ data.getString(1));
                surname.setText("surname: "+ data.getString(2));
                if(userEmail.length()==0) {
                    userEmail = data.getString(3);
                }
                mail.setText("email: "+ userEmail);
                membership.setText("membership: "+ (data.getString(5).equals("-1")? "none":data.getString(5)));
            }
        }

    }
}