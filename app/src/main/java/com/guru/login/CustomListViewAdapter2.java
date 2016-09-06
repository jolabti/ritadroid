package com.guru.login;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by johan on 16/08/16.
 */
public class CustomListViewAdapter2 extends ArrayAdapter<Banku> {

    private Activity activity;

    public CustomListViewAdapter2(Activity activity, int resource, List<Banku> books) {
        super(activity, resource, books);
        this.activity = activity;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        Banku book = getItem(position);

        holder.name_ban.setText("ID Pemesanan :" + book.getNamaBan());
        holder.kode_ban.setText("Kode Ban :" + book.getKodeBan());
        holder.merk_ban.setText("Email:" + book.getMerkBan());
        holder.harga_ban.setText("Pesanan:" + book.getHargaBan());
        holder.ukuran_ban.setText("Total Bayar :" + book.getUkuranBan());
        holder.stok_ban.setText("Tanggal:" + String.valueOf(book.getStokBan()));
        // holder.authorName.setText(book.getKodeBan());
        Picasso.with(activity).load(book.getImgUrl()).into(holder.image);

        return convertView;
    }

    private static class ViewHolder {
        private TextView name_ban, kode_ban, merk_ban, harga_ban, ukuran_ban, stok_ban;
        private TextView authorName;
        private ImageView image;

        public ViewHolder(View v) {
            name_ban = (TextView) v.findViewById(R.id.nama_ban);
            kode_ban = (TextView) v.findViewById(R.id.kd_ban);
            merk_ban = (TextView) v.findViewById(R.id.merek_ban);
            harga_ban = (TextView) v.findViewById(R.id.harga_ban);
            ukuran_ban = (TextView) v.findViewById(R.id.ukuran_ban);
            stok_ban = (TextView) v.findViewById(R.id.stok_ban);
            image = (ImageView) v.findViewById(R.id.thumbnail);
            //authorName = (TextView) v.findViewById(R.id.author);
        }
    }
}