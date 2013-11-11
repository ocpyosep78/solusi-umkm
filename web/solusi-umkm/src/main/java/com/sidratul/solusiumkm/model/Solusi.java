package com.sidratul.solusiumkm.model;

public class Solusi {
    private Integer idSolusi;
    private DetailPermasalahan detailPermasalahan;
    private String isiSolusi;

    public Integer getIdSolusi() {
        return idSolusi;
    }

    public void setIdSolusi(Integer idSolusi) {
        this.idSolusi = idSolusi;
    }

    public DetailPermasalahan getDetailPermasalahan() {
        return detailPermasalahan;
    }

    public void setDetailPermasalahan(DetailPermasalahan detailPermasalahan) {
        this.detailPermasalahan = detailPermasalahan;
    }

    public String getIsiSolusi() {
        return isiSolusi;
    }

    public void setIsiSolusi(String isiSolusi) {
        this.isiSolusi = isiSolusi;
    }
    
}
