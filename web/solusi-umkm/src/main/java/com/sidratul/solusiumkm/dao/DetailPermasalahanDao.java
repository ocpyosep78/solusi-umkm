package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.DetailPermasalahan;
import java.util.List;

public interface DetailPermasalahanDao {
    public List<DetailPermasalahan> getAllDetailPermasalahan();
    
    public void saveDetailPermasalahan(DetailPermasalahan detailPermasalahan);
    
    public DetailPermasalahan getDetailPermasalahanById(Integer id);
    
    public void deleteDetailPermasalahan(Integer id);
}
