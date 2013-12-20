package com.sidratul.solusiumkm.view.controller;

import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.model.KategoriProduk;
import com.sidratul.solusiumkm.model.Produk;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/produk")
public class ProdukController {
    @Autowired private ProdukDao produkDao;
    @Autowired private KategoriProdukDao kategoriProdukDao;
    
    
    @RequestMapping("/index")
    public void tampilProduk(ModelMap modelMap){
        List<Produk> produks = produkDao.getAllProduk();
        List<KategoriProduk> kategoriProduks =  kategoriProdukDao.getAllKategoriProduk();
        
        modelMap.addAttribute("listProduk", produks);
        modelMap.addAttribute("listKategoriProduk", kategoriProduks);
    }
    
    @RequestMapping("/detail")
    public void detailProduk(@RequestParam("id") Integer id,
    ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        List<KategoriProduk> kategoriProduks =  kategoriProdukDao.getAllKategoriProduk();
        
        modelMap.addAttribute("produk", produk);
        modelMap.addAttribute("listKategoriProduk", kategoriProduks);
    }
    
    
    
}
