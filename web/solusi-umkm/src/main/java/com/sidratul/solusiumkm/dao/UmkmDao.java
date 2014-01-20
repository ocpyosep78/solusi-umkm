package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Umkm;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;

public interface UmkmDao {
    public List<Umkm> getAllUmkm();
    
    public List<Umkm> getAllUmkmTidakMemilikiUser();
    
    public void saveUmkm(Umkm umkm) throws DuplicateKeyException;
    
    public Umkm getUmkmById(Integer id);
    
    public Umkm getUmkmByUsername(String username);
    
    public Umkm getUmkmByKodeUmkm(String kodeUmkm);
    
    public Umkm getUmkmByKodeUmkmDanBukanId(String kodeUmkm, Integer id);
    
    public Umkm getUmkmByUsernameDanBukanId(String username, Integer id);
    
    public void deleteUmkm(Integer id);
}
