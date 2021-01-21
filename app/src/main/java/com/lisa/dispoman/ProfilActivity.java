package com.lisa.dispoman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lisa.dispoman.Model.Users;
import com.lisa.dispoman.Model.apiGuru;

public class ProfilActivity extends AppCompatActivity {
    private TextView nama,nip,pangkat,jabatan, alamat, email;
    private AppSession appSession;
    public apiGuru guru;
    public Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        appSession = new AppSession(getApplicationContext()) ;

        nama = findViewById(R.id.txt_nama);
        nip = findViewById(R.id.txt_NIP);
        pangkat = findViewById(R.id.txt_pangkat);
        jabatan = findViewById(R.id.txt_Jabatan);
        alamat = findViewById(R.id.txt_alamat);
        email = findViewById(R.id.txt_email);

        guru = appSession.getGuru();
        users = appSession.getUser();
        Log.d("tesuser", "onCreate: user"+users.name);


        nama.setText(users.name);
        nip.setText(guru.nip);
        pangkat.setText(guru.golongan);
        jabatan.setText(guru.jabatan);
        alamat.setText(guru.alamat);
        email.setText(users.email);


    }
}