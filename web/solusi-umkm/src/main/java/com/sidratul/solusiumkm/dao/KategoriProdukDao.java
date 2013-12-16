package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.KategoriProduk;
import java.util.List;

public interface KategoriProdukDao {
    
    public List<KategoriProduk> getAllKategoriProduk();
    
    public void saveKategoriProduk(KategoriProduk kategoriProduk);
    
    public KategoriProduk getKategoriProdukById(Integer id);
    
    public void deleteKategoriProduk(Integer id);
}