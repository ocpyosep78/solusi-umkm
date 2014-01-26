package com.sidratul.solusiumkm.model;

import java.util.List;

public class Kasus {
    private Integer id;
    private String isiKasus;
    private List<Permasalahan> permasalahans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsiKasus() {
        return isiKasus;
    }

    public void setIsiKasus(String isiKasus) {
        this.isiKasus = isiKasus;
    }

    public List<Permasalahan> getPermasalahans() {
        return permasalahans;
    }

    public void setPermasalahans(List<Permasalahan> permasalahans) {
        this.permasalahans = permasalahans;
    }
    
}
