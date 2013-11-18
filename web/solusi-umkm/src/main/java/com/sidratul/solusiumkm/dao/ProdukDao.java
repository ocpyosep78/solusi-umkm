package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Produk;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;

public interface ProdukDao {
    public List<Produk> getAllProduk();
    
    public void saveProduk(Produk produk) throws DuplicateKeyException;
    
    public Produk getProdukById(Integer id);
    
    public void deleteProduk(Integer id);
}