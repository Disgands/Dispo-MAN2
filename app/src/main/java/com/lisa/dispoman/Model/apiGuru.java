package com.lisa.dispoman.Model;

public class apiGuru {
    public  Integer id;
    public Integer user_id;
    public String nip;
    public String golongan ;
    public String jabatan;
    public String alamat ;
    public Users user;

    public apiGuru(Integer id, Integer user_id, String nip, String golongan, String jabatan, String alamat, Users user) {
        this.id = id;
        this.user_id = user_id;
        this.nip = nip;
        this.golongan = golongan;
        this.jabatan = jabatan;
        this.alamat = alamat;
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public apiGuru(Integer id, Integer user_id, String nip, String golongan, String jabatan, String alamat) {
        this.id = id;
        this.user_id = user_id;
        this.nip = nip;
        this.golongan = golongan;
        this.jabatan = jabatan;
        this.alamat = alamat;
    }

    public apiGuru() {
    }
}
