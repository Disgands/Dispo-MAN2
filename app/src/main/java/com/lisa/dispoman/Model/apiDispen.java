package com.lisa.dispoman.Model;

public class apiDispen {

    private Integer id;
    public Integer guru_id;
    public String tgl_dispen;
    public String alasan_dispen;
    public String deskripsi_dispen;
    public String approval;

    public apiDispen(Integer id, Integer guru_id, String tgl_dispen, String alasan_dispen, String deskripsi_dispen, String aproval) {
        this.id = id;
        this.guru_id = guru_id;
        this.tgl_dispen = tgl_dispen;
        this.alasan_dispen = alasan_dispen;
        this.deskripsi_dispen = deskripsi_dispen;
        this.approval = aproval;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(Integer guru_id) {
        this.guru_id = guru_id;
    }

    public String getTgl_dispen() {
        return tgl_dispen;
    }

    public void setTgl_dispen(String tgl_dispen) {
        this.tgl_dispen = tgl_dispen;
    }

    public String getAlasan_dispen() {
        return alasan_dispen;
    }

    public void setAlasan_dispen(String alasan_dispen) {
        this.alasan_dispen = alasan_dispen;
    }

    public String getDeskripsi_dispen() {
        return deskripsi_dispen;
    }

    public void setDeskripsi_dispen(String deskripsi_dispen) {
        this.deskripsi_dispen = deskripsi_dispen;
    }

    public String getAproval() {
        return approval;
    }

    public void setAproval(String aproval) {
        this.approval = aproval;
    }

    public apiDispen() {
    }
}
