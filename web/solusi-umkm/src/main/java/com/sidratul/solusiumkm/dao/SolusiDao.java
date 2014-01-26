package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Solusi;
import java.util.List;

public interface SolusiDao {

    public List<Solusi> getAllSolusi();
    
    public Solusi getSolusiByIdKasus(Integer idKasus);
}
