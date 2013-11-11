/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidratul.solusiumkm.model;

import java.math.BigDecimal;

/**
 *
 * @author sidratul
 */
public class Produk {
    private Integer idProduk;
    private UMKM umkm;
    private String kodeProduk;
    private String namaProduk;
    private BigDecimal harga;
    private String deskripsi;

    public Integer getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Integer idProduk) {
        this.idProduk = idProduk;
    }

    public UMKM getUmkm() {
        return umkm;
    }

    public void setUmkm(UMKM umkm) {
        this.umkm = umkm;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
}
