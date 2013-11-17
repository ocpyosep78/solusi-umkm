package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.KategoriUmkm;
import java.util.List;

public interface KategoriUmkmDao {
    
    public List<KategoriUmkm> getAllKategoriUMKM();
    
    public void saveKategoriUMKM(KategoriUmkm kategoriUMKM);
    
    public KategoriUmkm getKategoriUMKMById(Integer id);
    
    public void deleteKategoriUMKM(Integer id);
}
