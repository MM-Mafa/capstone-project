package com.example.fingerprintvotingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.fingerprintvotingsystem.adapters.BottomBarHandler;
import com.example.fingerprintvotingsystem.adapters.GridAdapter;
import com.example.fingerprintvotingsystem.databinding.ActivityHomeBinding;

import java.util.ArrayList;
public class Home extends AppCompatActivity {
   @NonNull ActivityHomeBinding binding;
    GridView gridView;
    ImageView popUpMenu;
    boolean isNewUser = false;
    int [] imageIds;
    String [] candidateNames;
    String [] partyNames;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        popUpMenu = findViewById(R.id.menuButton);
        view = findViewById(R.id.added_);
        Intent intent = getIntent();
        if(intent!=null)
            if(intent.hasExtra("isNewUser"))
                isNewUser =intent.getIntExtra("isNewUser",0) ==1;

        // video pop up start here
        if(isNewUser) {
            isNewUser = false;
            view.setVisibility(View.VISIBLE);
            // starts here
//            myDialog = new Dialog(this);
//            myDialog.setContentView(R.layout.popup_video);
             TextView cancel = findViewById(R.id.cancel_video_tut);
            VideoView videoView =findViewById(R.id.video_view);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.setVisibility(View.INVISIBLE);
                    onGridItemClick();

                }
            });
            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_presentation;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
       }
//        // video pop up ends here

        popUpMenu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                view.setVisibility(View.INVISIBLE);
                BottomBarHandler barHandler = new BottomBarHandler(Home.this,v);
                barHandler.showMenu();
            }
        });

         candidateNames = new String[]{
                 "Cyril Ramaphosa",
                 "John Steenhuisen",
                 "Julius Malema",
                 "Bantu Holomisa",

                 "Mosiuoa Lekota",
                 "Ganief Hendricks",
                 "Pieter Groenewald",
                 "Kenneth Meshoe",

                 "Mandla Galo",
                 "Mzwanele Nyhontso",
                 "Patricia de Lille",
                 "Velenkosini Hlabisa",

                 "Vuyolwethu Zungula",
         };

        partyNames = new String[]{
                "ANC",
                "DA",
                "EFF",
                "UDM",

                "COPE",
                "ALJAMA",
                "VF+",
                "ACDP",

                "AIC",
                "PAC",
                "GOOD",
                "IFP",

                "ATM"
        };

         imageIds = new int[]{
                 R.drawable.ramaphosa_anc,
                 R.drawable.john_da,
                 R.drawable.julius_malema_eff,
                 R.drawable.bantu_holomisa_udm,

                 R.drawable.cope,
                 R.drawable.ganief_hendricks,
                 R.drawable.groenewald_vf,
                 R.drawable.k_meshoe_toronto_acdp,

                 R.drawable.mandla_galo_aic,
                 R.drawable.pac,
                 R.drawable.patricia_de_lille_good,
                 R.drawable.velenkosini_hlabisa_ifp,

                 R.drawable.vuyolwethu_zungula_atm
         };

        GridAdapter gridAdapter = new GridAdapter(
                getApplicationContext(),
                partyNames,
                candidateNames,
                imageIds);


        binding.gridView.setAdapter(gridAdapter);
        gridView = findViewById(R.id.gridView);


    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 3:2);
        super.onConfigurationChanged(newConfig);
    }

    private void onGridItemClick(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Home.this,Vote.class)
                        .putExtra("candidateName",candidateNames[position])
                        .putExtra("partyName",partyNames[position])
                        .putExtra("imageId",imageIds[position]);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        onGridItemClick();
        super.onResume();
    }
}