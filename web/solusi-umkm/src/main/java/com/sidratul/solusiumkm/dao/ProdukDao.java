/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.Produk;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;

/**
 *
 * @author sidratul
 */
public interface ProdukDao {
    public List<Produk> getAllProduk();
    
    public void saveProduk(Produk produk) throws DuplicateKeyException;
    
    public Produk getProdukById(Integer id);
    
    public void deleteProduk(Integer id);
}
