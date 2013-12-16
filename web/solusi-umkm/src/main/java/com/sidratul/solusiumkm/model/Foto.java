package com.sidratul.solusiumkm.model;

import java.util.Date;

public class Foto {
    private Integer id;
    private String namaFile;
    private Date tglUpload;
    private String keteranganFoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public Date getTglUpload() {
        return tglUpload;
    }

    public void setTglUpload(Date tglUpload) {
        this.tglUpload = tglUpload;
    }

    public String getKeteranganFoto() {
        return keteranganFoto;
    }

    public void setKeteranganFoto(String keteranganFoto) {
        this.keteranganFoto = keteranganFoto;
    }
}
