package com.sidratul.solusiumkm.model;

public class Solusi {
    private Integer id;
    private String isiSolusi;
    private Kasus kasus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsiSolusi() {
        return isiSolusi;
    }

    public void setIsiSolusi(String isiSolusi) {
        this.isiSolusi = isiSolusi;
    }

    public Kasus getKasus() {
        return kasus;
    }

    public void setKasus(Kasus kasus) {
        this.kasus = kasus;
    }
}
