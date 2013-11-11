package com.sidratul.solusiumkm.model;

public class Foto {
    private Integer idFoto;
    private String namaFoto;
    private String keterangan;
    private Long ukuranFoto;

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public String getNamaFoto() {
        return namaFoto;
    }

    public void setNamaFoto(String namaFoto) {
        this.namaFoto = namaFoto;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Long getUkuranFoto() {
        return ukuranFoto;
    }

    public void setUkuranFoto(Long ukuranFoto) {
        this.ukuranFoto = ukuranFoto;
    }
    
}
