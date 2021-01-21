package com.lisa.dispoman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.lisa.dispoman.Model.Users;
import com.lisa.dispoman.Model.apiGuru;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText InputUsername, InputPassword;
    private Button LoginButton;
    private ProgressDialog loading;
    private AppSession appsession;


    private boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loading = new ProgressDialog(this);
        appsession = new AppSession(getApplicationContext());

        LoginButton = findViewById(R.id.login);
        InputUsername =  findViewById(R.id.login_username);
        InputPassword = findViewById(R.id.login_password);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent moveIntent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(moveIntent);
                login();

            }
        });
}

    private void login() {
        String username = InputUsername.getText().toString();
        String password = InputPassword.getText().toString();
        boolean isEmptyField = false;


        if (TextUtils.isEmpty(username)) {
            isEmptyField = true;
            InputUsername.setError("Username tidak boleh kosong");
        }
        if (TextUtils.isEmpty(password)) {
            isEmptyField = true;
            InputPassword.setError("Password tidak boeh kosong");
        }
        else {
            validate(InputUsername.getText().toString(), InputPassword.getText().toString());
        }

    }

    private void validate(final String email, final String password) {
        loading.setMessage("Loading !!!");
        loading.show();
        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://dispo-web.herokuapp.com/api/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("hasil resp", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            Boolean succes = jsonObject.getBoolean("status");



                            if (succes== true){
                                JSONObject data = jsonObject.getJSONObject("data");
                                Log.d("hasil json", "onResponse: sukses"+data.toString());

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                Toast.makeText(LoginActivity.this, "berhasil", Toast.LENGTH_SHORT).show();


                                Gson gson = new Gson();
                                apiGuru guru = gson.fromJson(data.toString(), apiGuru.class);
//                                Users users = gson.fromJson(data.toString(), Users.class);
                                Log.d("ambluser ", " guru "+ guru.user.toString());
                                Log.d("ambluser ", " guru "+ guru.user.name);
                                appsession.setGuru(guru);
                                appsession.setUsers(guru.user);


//                                }

                            }
                            else {
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this, "Email dan password salah", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "Email dan password salah", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, "Cek Koneksi Internet", Toast.LENGTH_LONG).show();


            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email);
                params.put("password",password);

                return params;
            }
         };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}





