package com.lisa.dispoman;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lisa.dispoman.Model.Users;
import com.lisa.dispoman.Model.apiDispen;
import com.lisa.dispoman.Model.apiGuru;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<apiDispen> dispenku;

    public Users users;
    private AppSession appSession;
    TextView txtNama, txtTanggal,txtAlasan, txtDeskripsi, txtStatus;
    Context ctx;



    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        txtNama = v.findViewById(R.id.tvNama);
        txtTanggal = v.findViewById(R.id.tvTgl);
        txtAlasan = v.findViewById(R.id.tvAlasan);
        txtDeskripsi = v.findViewById(R.id.tvDeskripsi);
        txtStatus = v.findViewById(R.id.tvStatus);

        appSession = new AppSession(parent.getContext()) ;
        users = appSession.getUser();

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final apiDispen dispen = dispenku.get(position);



        if (dispen !=null){

            txtNama.setText(users.name);
            txtTanggal.setText(dispen.tgl_dispen);
            txtAlasan.setText(ambilAlasan(dispen.alasan_dispen));
            txtDeskripsi.setText(dispen.deskripsi_dispen);
            txtStatus.setText(ambilStatus(dispen.approval));

        }

    }
    public String ambilAlasan ( String teksSpinner) {
        String hasil = "-";
        switch (teksSpinner) {
            case "1":
                hasil ="Tidak Hadir";
                break;

            case "2":
                hasil = "Terlambat";
                break;
            case "3":
                hasil = "Pulang sebelum waktu";
                break;
            case "4":
                hasil = "Tidak berada ditempat tugas";
                break;
            case "5":
                hasil = "Tidak mengisi daftar hadir";
                break;
        }
        return hasil;

    }
    public String ambilStatus ( String teksSpinner) {
        String hasil = "-";
        switch (teksSpinner) {
            case "0":
                hasil ="Menunggu Approval";
                break;

            case "1":
                hasil = "Diterima";
                break;
            case "2":
                hasil = "Ditolak";
                break;
        }
        return hasil;

    }

    @Override
    public int getItemCount() {
        if(dispenku == null) {
            return 0;
        }
        Log.d("tes", "getItemCount: pesan"+dispenku.size());
        return dispenku.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }
    public MyAdapter(Context context, List<apiDispen> myDataset) {
        dispenku = myDataset;
        ctx = context;
    }
}
