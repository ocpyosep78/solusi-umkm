package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Umkm;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;

public interface UmkmDao {
    public List<Umkm> getAllUmkm();
    
    public void saveUmkm(Umkm umkm) throws DuplicateKeyException;
    
    public Umkm getUmkmById(Integer id);
    
    public void deleteUmkm(Integer id);
}
