package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Produk;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;

public interface ProdukDao {
    public List<Produk> getAllProduk();
    
    public List<Produk> getAllProdukByUsername(String username);
    
    public void saveProduk(Produk produk) throws DuplicateKeyException;
    
    public Produk getProdukById(Integer id);
    
    public Produk getProdukByKode(String kodeProduk, Integer idUmkm, String namaProduk);
    
    public void deleteProduk(Integer id);
    
    public void deleteFotoById(Integer id);
    
    //public void deleteFotoByIdProduk(Integer idProduk);
    
    public void deleteDistribusiFotoByIdFoto(Integer idFoto);
    
    public void deleteDistribusiFotoByIdProduk(Integer idProduk);
}