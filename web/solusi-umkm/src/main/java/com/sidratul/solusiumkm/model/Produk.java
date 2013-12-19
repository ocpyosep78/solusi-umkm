package com.sidratul.solusiumkm.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class Produk {
    private Integer id;
    private Umkm umkm;
    private KategoriProduk kategoriProduk;
    private String kodeProduk;
    private String namaProduk;
    private BigDecimal harga;
    private String keteranganProduk;
    private Date tglUpdateProduk;
    private List<Foto> fotos;
    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Umkm getUmkm() {
        return umkm;
    }

    public void setUmkm(Umkm umkm) {
        this.umkm = umkm;
    }

    public KategoriProduk getKategoriProduk() {
        return kategoriProduk;
    }

    public void setKategoriProduk(KategoriProduk kategoriProduk) {
        this.kategoriProduk = kategoriProduk;
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

    public String getKeteranganProduk() {
        return keteranganProduk;
    }

    public void setKeteranganProduk(String keteranganProduk) {
        this.keteranganProduk = keteranganProduk;
    }

    public Date getTglUpdateProduk() {
        return tglUpdateProduk;
    }

    public void setTglUpdateProduk(Date tglUpdateProduk) {
        this.tglUpdateProduk = tglUpdateProduk;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    
}
