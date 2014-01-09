package com.sidratul.solusiumkm.model;

import java.util.Date;

public class UserUmkm {
    private Integer id;
    private String username;
    private String password;
    private String peran;
    private Date terakhirLogin;
    private boolean aktif;
    private Umkm umkm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPeran() {
        return peran;
    }

    public void setPeran(String peran) {
        this.peran = peran;
    }

    public Date getTerakhirLogin() {
        return terakhirLogin;
    }

    public void setTerakhirLogin(Date terakhirLogin) {
        this.terakhirLogin = terakhirLogin;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public Umkm getUmkm() {
        return umkm;
    }

    public void setUmkm(Umkm umkm) {
        this.umkm = umkm;
    }
    
}
