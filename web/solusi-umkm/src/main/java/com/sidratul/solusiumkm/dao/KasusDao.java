package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Kasus;
import java.util.List;


public interface KasusDao {
    public List<Kasus> getAllKasus();
    
    public Kasus getKasusById(Integer id);
}
