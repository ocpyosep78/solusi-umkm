package com.sidratul.solusiumkm.model;

public class DistribusiFoto {
    private Integer idDistribusi;
    private Foto f;
    private Produk p;

    public Integer getIdDistribusi() {
        return idDistribusi;
    }

    public void setIdDistribusi(Integer idDistribusi) {
        this.idDistribusi = idDistribusi;
    }

    public Foto getF() {
        return f;
    }

    public void setF(Foto f) {
        this.f = f;
    }

    public Produk getP() {
        return p;
    }

    public void setP(Produk p) {
        this.p = p;
    }
}
