package com.guru.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MerekBan extends AppCompatActivity {
    Button Bridgestone,Dunlop,Gajah_Tunggal;


    public static final String mypreference = "LoginCredentials";
    public static final String Name = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merek_ban);

        //menginisiasi dan memanggil widget button pada file layout
        Bridgestone = (Button) findViewById(R.id.bridgestone);
        Dunlop = (Button) findViewById(R.id.dunlop);
        Gajah_Tunggal = (Button) findViewById(R.id.gajahTunggal);

        Bridgestone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View argO) {
                //TODO Auto-generated method stub

                Intent pindah1 = new Intent(MerekBan.this, Bridgestone.class);
                startActivity(pindah1);

                //menghubungkan antar activity dengan inten
               Toast.makeText(MerekBan.this, getEmail().toString(), Toast.LENGTH_LONG).show();
                //Log.d("SPSP", getEmail().toString());

            }
        });

        Dunlop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View argO) {
                //TODO Auto-generated method stub
                Intent pindah2 = new Intent(MerekBan.this, Dunlop.class);
                startActivity(pindah2);
                //menghubungkan antar activity dengan inten
            }
        });

        Gajah_Tunggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View argO) {
                //TODO Auto-generated method stub
                Intent pindah3 = new Intent(MerekBan.this, SuccessActivity.class);
                startActivity(pindah3);
                //menghubungkan antar activity dengan inten
            }
        });

    }
    public String getEmail() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginCredentials", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Username", "");
    }
}
