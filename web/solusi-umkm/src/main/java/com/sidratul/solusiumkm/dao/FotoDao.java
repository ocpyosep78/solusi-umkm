package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Foto;
import java.util.List;

public interface FotoDao {
    public List<Foto> getAllFotoByIdProduk(Integer idProduk);
    
    public void saveFoto(Foto foto, Integer idProduk);
    
    public Foto getFotoByid(Integer id);
    
    public void DeleteFotoByid(Integer id);
    
    public Foto getFotoByNamaFile(String namaFile);
    
    public void DeleteFotoByidProduk(Integer idProduk);
    
}
