
package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Permasalahan;
import java.util.List;

public interface PermasalahanDao {
    
    public List<Permasalahan> getAllPermasalahan();
    
    public List<Permasalahan> getAllPermasalahanByIdKasus(Integer idKasus);
    
    public Permasalahan getPermasalahanById(Integer id);
    
}
