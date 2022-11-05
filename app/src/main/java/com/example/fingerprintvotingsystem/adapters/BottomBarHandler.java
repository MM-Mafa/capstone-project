package com.example.fingerprintvotingsystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.RequiresApi;

import com.example.fingerprintvotingsystem.ElectionResults;
import com.example.fingerprintvotingsystem.Profile;
import com.example.fingerprintvotingsystem.R;
import com.example.fingerprintvotingsystem.Tutorial;

public class BottomBarHandler {
    private final View view;
    Context context;


    public BottomBarHandler(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public  void showMenu () {
        PopupMenu popupMenu = new PopupMenu( context, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.profile:
                        context.startActivity( new Intent(context, Profile.class));
                        return true;
                    case R.id.tutorial:
                        // show tut popup
                        context.startActivity(new Intent(context, Tutorial.class));
                        return true;
                    case R.id.election_board:
                        context.startActivity( new Intent(context, ElectionResults.class));
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.setForceShowIcon(true);
        popupMenu.show();
    }
}
