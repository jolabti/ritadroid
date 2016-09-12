package com.guru.login;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by johan on 06/09/16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    //private ArrayList<AndroidVersion> android;
    private ArrayList<BookArray> bookArrays;

    public DataAdapter(ArrayList<BookArray> bookArrays) {
        this.bookArrays= bookArrays;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText("Kode Ban"+ " " +bookArrays.get(i).getKd_ban());
        viewHolder.tv_version.setText("ID_Pemesanan" + " " + bookArrays.get(i).getId_pemesanan());
        viewHolder.tv_api_level.setText("Email Pemesan"+ " " + bookArrays.get(i).getEmail());
        viewHolder.tv_jml_pemesanan.setText("Jumlah Pesanan :" + bookArrays.get(i).getPesanan());
        viewHolder.tv_pewaktuan.setText("Tanggal Pesan :" + bookArrays.get(i).getPewaktuan());
        viewHolder.tv_total_bayar.setText("Total Bayar :" + bookArrays.get(i).getTotal());

    }

    @Override
    public int getItemCount() {
        return bookArrays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level, tv_jml_pemesanan, tv_pewaktuan, tv_nama_ban, tv_total_bayar;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_version = (TextView)view.findViewById(R.id.tv_version);
            tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);
            tv_jml_pemesanan = (TextView)view.findViewById(R.id.tv_jml_pesanan);
            tv_pewaktuan = (TextView)view.findViewById(R.id.tv_waktu_pesanan);
            tv_total_bayar = (TextView)view.findViewById(R.id.tv_total_pembayaran);
        }
    }

}