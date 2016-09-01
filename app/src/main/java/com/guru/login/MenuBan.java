package com.guru.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuBan extends AppCompatActivity {
    Button merek, toko, bantuan, keluar;
    Intent intentku = getIntent();
   // Bundle bd = intentku.getExtras();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ban);
        //menginisiasi dan memanggil widget button pada file layout
        merek = (Button) findViewById(R.id.merek_button);
        toko = (Button) findViewById(R.id.toko_button);
        bantuan = (Button) findViewById(R.id.bantuan_button);
        keluar = (Button) findViewById(R.id.keluar_button);

        merek.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View argO) {


             //       String tampungemail = (String) bd.get("emailsession");



                //TODO Auto-generated method stub
                Intent pindah = new Intent(MenuBan.this, MerekBan.class);
             //   pindah.putExtra("emailsestopemesanan", tampungemail);
                startActivity(pindah);
                //menghubungkan antar activity dengan inten
            }
        });

        //Tampilan untuk menu Tentang Toko dengan menggunakan AlertDialog
        toko.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (view == toko) {
                    new AlertDialog.Builder(MenuBan.this)
                            .setTitle("Tentang Toko")
                            .setMessage("Aplikasi pemesanan ban mobil adalah aplikasi untuk orang yang ingin memesan ban mobil secara online." +
                                    "                                                                                                       "
                                    +
                                    "Tempat penjualannya terletak di Jln. H. Thalib Rt. 001/010, Kel. Cipete Utara, Kec. Kebayoran Baru-Jakarta Selatan" +
                                    "                                                                                                        "
                                    +
                                    "Dibuat oleh :" +
                                    "                                                                                                  " +
                                    "                                                                                                  "
                                    +
                                    "Nama : Rita Ratnasari" +
                                    "                                                                                                  "
                                    +
                                    "Npm  : 37113835 " +
                                    "                                                                                                  ")
                            .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int sumthin) {

                                }
                            }).show();
                }
            }
        });

        bantuan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (view == bantuan) {
                    new AlertDialog.Builder(MenuBan.this)
                            .setTitle("Bantuan")
                            .setMessage("Bantuan Pengguna Aplikasi :" +
                                    "                                                                                                      " +
                                    "1. Merek Ban Mobil : Untuk memilih merek ban apa yang ingin di pesan" +
                                    "                                                                                                                       " +
                                    "2. Tentang Toko : Untuk mengetahui informasi tentang toko" +
                                    "                                                                                                      " +
                                    "3. Bantuan : Untuk mengetahui cara penggunaan aplikasi" +
                                    "                                                                                                      " +
                                    "4. Keluar : Untuk keluar dari aplikasi")
                            .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int sumthin) {

                                }
                            }).show();
                }
            }
        });
        //Tampilan untuk menu keluar dengan menggunakan AlertDialog
        keluar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuBan.this);
                builder.setMessage("Apakah Kamu Yakin Ingin Keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MenuBan.this.finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                dialog.cancel();
                            }

                        }).show();
            }
        });
    }
}
