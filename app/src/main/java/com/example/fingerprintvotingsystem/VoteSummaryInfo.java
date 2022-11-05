package com.example.fingerprintvotingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
public class VoteSummaryInfo extends AppCompatActivity {
    TextView candidateName, partyName, date, time;
    AppCompatButton finish;
    String partyName1;
    String candidateName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_summary_info);
        candidateName = findViewById(R.id.voted_candidate_textview);
        partyName = findViewById(R.id.voted_party_name_textview);
        date = findViewById(R.id.vote_date_textView);
        time = findViewById(R.id.vote_time_textview);
        finish = findViewById(R.id.finish_button);



        Intent intent = getIntent();
        if(intent.hasExtra("partyName")){
            partyName1 = intent.getStringExtra("partyName");
        }
        if(intent.hasExtra("candidateName")){
            candidateName1 = intent.getStringExtra("candidateName");
        }

        if( candidateName1 !=null && partyName1 !=null){
            partyName.setText("party name: "+partyName1);
            candidateName.setText("candidate name: "+candidateName1);
            date.setText("Vote Date: "+ Calendar.getInstance().getTime());
            time.setVisibility(View.INVISIBLE);
        }

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(VoteSummaryInfo.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
            }
        });
    }
}