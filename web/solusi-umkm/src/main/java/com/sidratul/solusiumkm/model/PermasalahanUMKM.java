package com.sidratul.solusiumkm.model;

public class PermasalahanUMKM {
    private Integer idPermasalahanUMKM;
    private UMKM umkm;
    private Permasalahan permasalahan;

    public Integer getIdPermasalahanUMKM() {
        return idPermasalahanUMKM;
    }

    public void setIdPermasalahanUMKM(Integer idPermasalahanUMKM) {
        this.idPermasalahanUMKM = idPermasalahanUMKM;
    }

    public UMKM getUmkm() {
        return umkm;
    }

    public void setUmkm(UMKM umkm) {
        this.umkm = umkm;
    }

    public Permasalahan getPermasalahan() {
        return permasalahan;
    }

    public void setPermasalahan(Permasalahan permasalahan) {
        this.permasalahan = permasalahan;
    }
}
