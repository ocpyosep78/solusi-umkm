package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.KategoriUmkm;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;

public interface KategoriUmkmDao {
    
    public List<KategoriUmkm> getAllKategoriUmkm();
    
    public void saveKategoriUmkm(KategoriUmkm kategoriUmkm);
    
    public KategoriUmkm getKategoriUmkmById(Integer id);
    
    public KategoriUmkm getKategoriUmkmByJenisUmkm(String jenisUmkm);
    
    public KategoriUmkm getKategoriUmkmByJenisUmkmEdit(String jenisUmkm, Integer id);
    
    public void deleteKategoriUmkm(Integer id)throws DataIntegrityViolationException;
}
