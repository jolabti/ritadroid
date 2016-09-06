package com.guru.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Dunlop extends AppCompatActivity {
    private ListView listview;
    private ArrayList<Banku> books;
    private ArrayAdapter<Banku> adapter;
//    Spinner spin;


    // String arrayURL [] ={"http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2"."http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2"."http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2"};

    private final static String TAG = Dunlop.class.getSimpleName();
    String url = "http://192.168.88.31/rjbanadmin/index.php/maincontroller/get_product_controller_dunlop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dunlop);

        listview = (ListView) findViewById(R.id.listview);


        setListViewAdapter();
        getDataFromInternet();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                try {

                    String bku_name = books.get(position).getNamaBan().toString();
                    String lain = books.get(position).getKodeBan().toUpperCase().toString();
                    String kode_ban_detail = books.get(position).getKodeBan().toString();
                    String merk_ban_detail = books.get(position).getMerkBan().toString();
                    String harga_ban_detail = String.valueOf(books.get(position).getHargaBan());
                    String ukuran_ban_detail = books.get(position).getUkuranBan().toString();
                    String url_to_detail = books.get(position).getImgUrl().toString();
                    String stok_ban_detail = String.valueOf(books.get(position).getStokBan());


                    Toast.makeText(Dunlop.this, bku_name + " is created by " + lain, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dunlop.this, DetailActivity.class);
                    intent.putExtra("name", bku_name + " " + lain);
                    intent.putExtra("urltodetail", url_to_detail);
                    intent.putExtra("kodebandetail", kode_ban_detail);
                    intent.putExtra("merkbandetail", merk_ban_detail);
                    intent.putExtra("hargabandetail", harga_ban_detail);
                    intent.putExtra("ukuranbandetail", ukuran_ban_detail);
                    intent.putExtra("stokbandetail", stok_ban_detail);
                    startActivity(intent);

                    //String project_location = list_data.get(position).get("project_location");


                } catch (Exception e) {

                    Log.d("Error Error", e.getMessage().toString());
                }


            }
        });
    }

    private void getDataFromInternet() {


        new GetJsonFromUrlTask2(this, url).execute();
    }

    private void setListViewAdapter() {
        books = new ArrayList<Banku>();
        adapter = new CustomListViewAdapter(this, R.layout.item_listview, books);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);
    }

    public void parseJsonResponse(String result) {
        Log.i(TAG, result);
        try {
            JSONObject json = new JSONObject(result);
            JSONArray jArray = new JSONArray(json.getString("book_array"));
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject jObject = jArray.getJSONObject(i);

                Banku book = new Banku();
                book.setNamaBan(jObject.getString("nama_ban"));
                book.setKodeBan(jObject.getString("kd_ban"));
                book.setHargaBan(jObject.getString("harga_ban"));
                book.setStokBan(jObject.getInt("stok"));
                book.setUkuranBan(jObject.getString("ukuran"));
                book.setMerkBan(jObject.getString("merk_ban"));
                book.setImgUrl("http://192.168.88.31/rjbanadmin/media/" + jObject.getString("urlimage"));
                book.setKodeBan(jObject.getString("kd_ban"));
                books.add(book);
            }

            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

