package com.lisa.dispoman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AsyncNotedAppOp;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lisa.dispoman.Model.apiDispen;
import com.lisa.dispoman.Model.apiGuru;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class DispensasiActivity extends AppCompatActivity {
    private EditText tanggal,deskripsi ;
    private Button btnKirimData;
    private Spinner spinner;
    private ProgressDialog loading;
    private RequestQueue requestQueue;
    private Button btnTanggal;
    private TextView tvTanggal;
    private AppSession appSession;
    public apiGuru guru;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispensasi);
        appSession = new AppSession(getApplicationContext());
        guru = appSession.getGuru();

        Log.d("ambilGuru", "onCreate: guru"+guru.id);


        btnTanggal = (Button) findViewById(R.id.btnDate);
        tvTanggal = (TextView) findViewById(R.id.tvTanggal);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        btnKirimData = (Button) findViewById(R.id.kirimButton);
        spinner = findViewById(R.id.spinner);
        loading = new ProgressDialog(this);

        myCalendar = Calendar.getInstance();
        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DispensasiActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        tvTanggal.setText("Tanggal Dispen : " + sdf.format(myCalendar.getTime()));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        btnKirimData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    kirimData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

    });

}

    private void kirimData() throws JSONException {
        String date = myCalendar.getTime().toString();
        String deskription = deskripsi.getText().toString();
        String spinner_1 = spinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(date))
        {
            Toast.makeText(this,"tanggal harus diisi",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(deskription))
        {
            Toast.makeText(this,"deskripsi harus diisi",Toast.LENGTH_SHORT).show();
        }
        else {
            loading.setTitle("Mengirim data");
            loading.setMessage("Mohon menungggu sebentar");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            validate(date,deskription,spinner_1);

        }
    }

    private void validate(final String date, final String deskription, final String spinner_1) throws JSONException {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        Log.e("TESKU", "inisiasi validate");
        //fill params
        JSONObject params = new JSONObject();
        params.put("guru_id", guru.id);
        params.put("tgl_dispen", date);
        params.put("alasan_dispen", ambilValueSpinner(spinner_1) );
        params.put("deskripsi_dispen", deskription);

        String url ="https://dispo-web.herokuapp.com/api/dispensasi";
        createCall(Request.Method.POST, url, params);

        Toast.makeText(DispensasiActivity.this, "Dispensasi anda sudah terkirim, mohon menunngu konfirmasi", Toast.LENGTH_SHORT).show();
        loading.dismiss();
        Log.e("TESKU", "end validate");

    }
    public void createCall(int type, String url, JSONObject data) {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(type, url,data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
//                        try {
//                            callback(response, callback);
//                        } catch (Exception e){
//                            Log.d("API callback error", e.getMessage());
//                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error response", error.toString());
                    }
                }
        );
        queue.add(jsonRequest);
    }
    public Integer ambilValueSpinner ( String teksSpinner) {
        Integer hasil = 0;
        switch (teksSpinner) {
            case "Tidak hadir":
                hasil = 1;
                break;

            case "Terlambat":
                hasil = 2;
                break;
            case "Pulang sebelum waktu":
                hasil = 3;
                break;
            case "Tidak berada ditempat tugas":
                hasil = 4;
                break;
            case "Tidak mengisi daftar hadir":
                hasil = 5;
                break;
        }
        return hasil;

    }


}