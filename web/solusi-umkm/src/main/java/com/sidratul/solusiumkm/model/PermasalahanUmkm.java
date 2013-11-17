package com.sidratul.solusiumkm.model;

public class PermasalahanUmkm {
    private Integer idPermasalahanUMKM;
    private Umkm umkm;
    private Permasalahan permasalahan;

    public Integer getIdPermasalahanUMKM() {
        return idPermasalahanUMKM;
    }

    public void setIdPermasalahanUMKM(Integer idPermasalahanUMKM) {
        this.idPermasalahanUMKM = idPermasalahanUMKM;
    }

    public Umkm getUmkm() {
        return umkm;
    }

    public void setUmkm(Umkm umkm) {
        this.umkm = umkm;
    }

    public Permasalahan getPermasalahan() {
        return permasalahan;
    }

    public void setPermasalahan(Permasalahan permasalahan) {
        this.permasalahan = permasalahan;
    }
}
