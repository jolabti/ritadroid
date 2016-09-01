package com.guru.login;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.util.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Register extends AppCompatActivity {
    EditText name, email, password;
    String Getname, GetEmail, GetPassword;
    Button register;
    //   String DataParseUrl = "http://apidroid.devgolan.web.id/insert_pengguna.php";
    Boolean CheckEditText;
    String Response;
    HttpResponse response;

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText3);
        register = (Button) findViewById(R.id.button1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GetCheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    SendDataToServer(GetEmail, GetPassword);

                } else {


                    Toast.makeText(Register.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();


                }


            }
        });


    }

    public void GetCheckEditTextIsEmptyOrNot() {

        //  Getname = name.getText().toString();
        GetEmail = email.getText().toString();
        GetPassword = password.getText().toString();

        if (TextUtils.isEmpty(GetEmail) || TextUtils.isEmpty(GetPassword)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }

    }

    public void SendDataToServer(final String email, final String password) {

        class AsyncLogin extends AsyncTask<String, String, String> {
            ProgressDialog pdLoading = new ProgressDialog(Register.this);
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
                    url = new URL("http://192.168.1.14/rjbanadmin/register.php");

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
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */
                    Toast.makeText(Register.this, "Berhasil Nambah", Toast.LENGTH_LONG).show();

                } else if (result.equalsIgnoreCase("false")) {

                    // If username and password does not match display a error message
                    Toast.makeText(Register.this, "Invalid email or password", Toast.LENGTH_LONG).show();

                } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                    Toast.makeText(Register.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }
            }

        }
        new AsyncLogin().execute(email, password);
    }
}