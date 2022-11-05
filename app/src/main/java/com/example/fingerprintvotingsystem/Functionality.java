package com.example.fingerprintvotingsystem;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class Functionality {
    Context context;
    View view;
    public Functionality(Context context, View view) {
        this.context = context;
        this.view = view;

        popUpMenuShow(context,view);
    }
    private  void popUpMenuShow(Context context, View view){

        PopupMenu popupMenu = new PopupMenu( context, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

//                    case R.id.profile:
//                        startActivity( new Intent(MainMenu.this, MyProfile.class));
//                        return true;
//                    case R.id.help:
//                        startActivity( new Intent(MainMenu.this, Help.class));
//                        return true;
//                    case R.id.contact_us:
//                        startActivity( new Intent(MainMenu.this, ContactUs.class));
//                        return true;
//                    case R.id.bookings:
//                        startActivity( new Intent(MainMenu.this, MyBookings.class));
//                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }



}
