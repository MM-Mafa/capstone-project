package com.example.fingerprintvotingsystem;

import static com.example.fingerprintvotingsystem.adapters.UserInfo.userInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingerprintvotingsystem.adapters.DBHelper;

import java.util.concurrent.Executor;

public class Vote extends AppCompatActivity {
    TextView candidateName, partyName, about, brief;
    ImageView candidateImg;
    Button vote;
    Cursor userData;
    String userId;
    String userMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        vote = findViewById(R.id.button);
        candidateName = findViewById(R.id.candidateName);
        candidateImg = findViewById(R.id.candidateImg);
        about = findViewById(R.id.aboutParty);
        brief = findViewById(R.id.brief);
        partyName = findViewById(R.id.partyName);


        DBHelper dbHelper = new DBHelper(this);
        userMail = (String) userInfo.get("email");
        userId = (String) userInfo.get("id");

        //collect user id
        if ( userMail!=null && userMail.length()>0 ){
            userData = dbHelper.getDataFromCitizenByEmail( userMail,true);

            if(userData!=null){
                while((userData.moveToNext())) {
                    userId = userData.getString(0);
                }
            }
        }

        String [] summaries = {"summary"};
        Intent intent = getIntent();
        if(intent.hasExtra("candidateName")){
            candidateName.setText(intent.getStringExtra("candidateName"));
        }
        if(intent.hasExtra("partyName")){
            partyName.setText(intent.getStringExtra("partyName"));
        }
        if(intent.hasExtra("imageId")){
            candidateImg.setImageResource(intent.getIntExtra("imageId",0));
        }



        brief.setText(summaries[0]);

        vote.setOnClickListener(v -> {
            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("vote verification")
                    .setDescription("User Authentication required to verify vote")
                    .setNegativeButtonText("cancel")
                    .build();

            getPrompt().authenticate(promptInfo);
        });


    }


    private BiometricPrompt getPrompt(){

        Executor executor = ContextCompat.getMainExecutor(Vote.this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent = new Intent(Vote.this, VoteSummaryInfo.class);

                intent.putExtra("partyName", partyName.getText().toString());
                intent.putExtra("candidateName",candidateName.getText().toString());
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        };
        return new BiometricPrompt(this,executor,callback);
    }
}