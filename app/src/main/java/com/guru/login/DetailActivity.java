package com.guru.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;

import java.io.InputStream;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class DetailActivity extends AppCompatActivity {

    //http://192.168.88.31/rjbanadmin/index.php/maincontroller/lihat_pesanan_user_json/?=rita@gmail.com
    // Untuk membuat detail dari json
    int a, b, hitungHarga;


    String str = "";
    ImageView varImage;
    TextView tvIdNamaBan, tvIdKodeBan, tvIdUkuranBan, tvIdMerekBan, tvIdStokBan, tvIdHargaBan;
    EditText nml, eml, alm, notelp, juml, edttotal1;
    // EditText ditambahkan pesanan

    Button btnSimpan;
    //  EditText name, email, password;
    String Getname, GetEmail, GetPassword;
    Button register;
    //   String DataParseUrl = "http://apidroid.devgolan.web.id/insert_pengguna.php";
    Boolean CheckEditText;
    String Response;
    HttpResponse response;


    String id_user;
    String kd_ban;
    int pesanan;
    String email;
    String nama_lengkap;
    String alamat;
    String no_tlp;
    int total;

    InputStream is = null;
    String result = null;
    String line = null;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    String s1idus, s2nama, s3kodeban, s4email, s5alamat, s6notelp, i7jumlah;
    int i8total=100;

    int hitungTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        varImage = (ImageView) findViewById(R.id.img);
        tvIdKodeBan = (TextView) findViewById(R.id.kdBan);
        tvIdNamaBan = (TextView) findViewById(R.id.namaBan);
        tvIdMerekBan = (TextView) findViewById(R.id.merekBan);
        tvIdUkuranBan = (TextView) findViewById(R.id.ukuranBan);
        tvIdStokBan = (TextView) findViewById(R.id.hargaBan);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);


        nml = (EditText) findViewById(R.id.namaLkp);
        eml = (EditText) findViewById(R.id.et_nama);
        alm = (EditText) findViewById(R.id.et_alamat);
        notelp = (EditText) findViewById(R.id.et_notel);
        juml = (EditText) findViewById(R.id.et_jumlah);
        edttotal1 = (EditText) findViewById(R.id.et_total);



        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            String getName = (String) bd.get("name");
            String acceptedUrlImage = (String) bd.get("urltodetail");
            String kodeB = (String) bd.get("kodebandetail");
            //       String hargaB = (String) bd.get("hargabandetail");
            String merkB = (String) bd.get("merkbandetail");
            String ukuranB = (String) bd.get("ukuranbandetail");
            String stokB = (String) bd.get("stokbandetail");


            tvIdNamaBan.setText(getName);
            tvIdKodeBan.setText(kodeB);
            tvIdMerekBan.setText(merkB);
            //     tvIdHargaBan.setText(hargaB);
            tvIdUkuranBan.setText(ukuranB);
            tvIdStokBan.setText(stokB);

            Picasso.with(DetailActivity.this)
                    .load(acceptedUrlImage)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .resize(300, 300)
                    .into(varImage);
        }


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String iduser, String namalengkap, String kodeban, String email, String alamat, String notelp, int total, int pesanan

                s1idus = tvIdKodeBan.getText().toString();
                s2nama = nml.getText().toString();
                //insert();
                Log.d("Input Slidus", s1idus);
                Log.d("Hasil Kali", Integer.toString(hitungTotal));

                insertData(s1idus, s2nama,s1idus, s1idus, s1idus, s1idus, i8total, i8total);

                //    SendDataToServer(s1idus, s2nama, s3kodeban, s3kodeban, s3kodeban, s3kodeban, i7jumlah, i7jumlah);
            }
        });
        ;


    }





    public void insertData(String iduser, String namalengkap, String kodeban, String email, String alamat, String notelp, int total, int pesanan) {
        // call InterFace
        RegisterAPI registerAPI = ((RetrofitApplication) getApplication()).getMshoppinpApis();

        // call Inter face method
        Call<PojoDemo> call = registerAPI.serverCall(iduser, kodeban, pesanan, email, namalengkap, alamat, notelp, total);
        call.enqueue(new Callback<PojoDemo>() {
            @Override
            public void onResponse(Response<PojoDemo> response, Retrofit retrofit) {
                // postive responce

                // get pojo value using  getSuccess and get Body(Body is all responce)
                if (response.body().getSuccess()) {
                    Log.d("hasil output", response.raw().toString());

                    Toast.makeText(DetailActivity.this, "Value saved", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("hasil output", response.raw().toString());
                    Toast.makeText(DetailActivity.this, response.body().getError() + "", Toast.LENGTH_LONG).show();

                }
            }

            // Error Or Any Failure
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
    }














































   /* public void SendDataToServer(String iduser, String namalengkap, String kodeban, String email,
                                 String alamat, String notelp, int total, int pesanan) {

        class AsyncLogin extends AsyncTask<String, String, String> {
            ProgressDialog pdLoading = new ProgressDialog(DetailActivity.this);
            HttpURLConnection conn;
            URL url = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //this method will be running on UI thread
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();

            }

            @Override
            protected String doInBackground(String... params) {
                try {

                    // Enter URL address where your php file resides
                    url = new URL("http://192.168.1.14/rjbanadmin/pemesanan.php");

                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "exception";
                }
                try {
                    // Setup HttpURLConnection class to send and receive data from php and mysql
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(READ_TIMEOUT);
                    conn.setConnectTimeout(CONNECTION_TIMEOUT);
                    conn.setRequestMethod("POST");

                    // setDoInput and setDoOutput method depict handling of both send and receive
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    // Append parameters to URL
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("email", params[0])
                            .appendQueryParameter("password", params[1]);
                    String query = builder.build().getEncodedQuery();

                    // Open connection for sending data
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    return "exception";
                }

                try {

                    int response_code = conn.getResponseCode();

                    // Check if successful connection made
                    if (response_code == HttpURLConnection.HTTP_OK) {

                        // Read data sent from server
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        // Pass data to onPostExecute method
                        return (result.toString());

                    } else {

                        return ("unsuccessful");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    return "exception";
                } finally {
                    conn.disconnect();
                }


            }

            @Override
            protected void onPostExecute(String result) {

                //this method will be running on UI thread

                pdLoading.dismiss();

                if (result.equalsIgnoreCase("true")) {
                *//* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 *//*
                    Toast.makeText(DetailActivity.this, "Berhasil Nambah", Toast.LENGTH_LONG).show();

                } else if (result.equalsIgnoreCase("false")) {

                    // If username and password does not match display a error message
                    Toast.makeText(DetailActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

                } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                    Toast.makeText(DetailActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
            }

        }
        new AsyncLogin().execute(tvIdKodeBan, tvIdHargaBan);
    }*/


}
