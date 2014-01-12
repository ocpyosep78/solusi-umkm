package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Artikel;
import java.util.List;

public interface ArtikelDao {
    public List<Artikel> getAllArtikel();
    
    public void saveArtikel(Artikel artikel);
    
    public Artikel getArtikelById(Integer id);
    
    public void deleteArtikel(Integer id);
    
    
}
