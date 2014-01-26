package com.sidratul.solusiumkm.model;

public class Permasalahan {
    private Integer id;
    private String isiPermasalahan;
    private Integer bobot;

    public Integer getBobot() {
        return bobot;
    }

    public void setBobot(Integer bobot) {
        this.bobot = bobot;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsiPermasalahan() {
        return isiPermasalahan;
    }

    public void setIsiPermasalahan(String isiPermasalahan) {
        this.isiPermasalahan = isiPermasalahan;
    }
    
}
