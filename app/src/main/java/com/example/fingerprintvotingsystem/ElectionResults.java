package com.example.fingerprintvotingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.fingerprintvotingsystem.adapters.ElectionResultsAdapter;
import com.example.fingerprintvotingsystem.adapters.GridAdapter;
import com.example.fingerprintvotingsystem.databinding.ActivityElectionResultsBinding;
import com.example.fingerprintvotingsystem.databinding.ActivityHomeBinding;

public class ElectionResults extends AppCompatActivity {
    @NonNull
    ActivityElectionResultsBinding binding;
    GridView gridViewResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_results);
        binding = ActivityElectionResultsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String [] partyNames = {
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

        int [] imageIds = {
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
        ElectionResultsAdapter electionResultsAdapter = new ElectionResultsAdapter(ElectionResults.this,
                partyNames,
                imageIds,
                "0");
        binding.gridViewResults.setAdapter(electionResultsAdapter);
        gridViewResults = findViewById(R.id.grid_view_results);

    }
}