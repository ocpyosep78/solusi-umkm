package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.KategoriUmkm;
import java.util.List;

public interface KategoriUmkmDao {
    
    public List<KategoriUmkm> getAllKategoriUmkm();
    
    public void saveKategoriUmkm(KategoriUmkm kategoriUmkm);
    
    public KategoriUmkm getKategoriUmkmById(Integer id);
    
    public void deleteKategoriUmkm(Integer id);
}
