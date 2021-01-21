package com.lisa.dispoman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.Button1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.Button2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.Button3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.Button4);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Button1:
                Intent moveIntent = new Intent(MainActivity.this, DispensasiActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.Button2:
                Intent moveIntent2 = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(moveIntent2);
                break;
            case R.id.Button3:
                Intent moveIntent3 = new Intent(MainActivity.this, HistoriActivity.class);
                startActivity(moveIntent3);
                break;

            case R.id.Button4:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Keluar dari aplikasi?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ya",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })

                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
        }
    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    }