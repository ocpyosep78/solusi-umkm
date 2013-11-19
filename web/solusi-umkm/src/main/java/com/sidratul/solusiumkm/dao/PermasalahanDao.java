package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Permasalahan;
import java.util.List;

public interface PermasalahanDao {
    public List<Permasalahan> getAllPermasalahan(Permasalahan permasalahan);
    
    public void savePermasalahan(Permasalahan permasalahan);
    
    public Permasalahan getPermasalahanById(Integer id);
    
    public void deletePermasalahan(Integer id);
}
