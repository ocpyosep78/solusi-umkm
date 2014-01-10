package com.sidratul.solusiumkm.user.controller;

import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriProduk;
import com.sidratul.solusiumkm.model.Produk;
import com.sidratul.solusiumkm.model.Umkm;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/produk")
public class UserProdukController {
    @Autowired private ProdukDao produkDao;
    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriProdukDao kategoriProdukDao; 
   
    @RequestMapping("/daftat-produk")
    public void daftarProduk(Principal principal, ModelMap modelMap){
        List<Produk> produks = produkDao.getAllProdukByUsername(principal.getName());
        
        modelMap.addAttribute("listProduk", produks);
    }
    
    @RequestMapping("/detail")
    public String detailProduk(@RequestParam("id") Integer id,
    Principal principal,
    ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        
        Umkm umkm= umkmDao.getUmkmByUsername(principal.getName());
        
        if(umkm.getId() != produk.getUmkm().getId()){
            return "redirect:daftar-produk";
        }
        
        modelMap.addAttribute("produk", produk);
        
        return null;
    }
    
    @RequestMapping(value = "/input-produk",method = RequestMethod.GET)
    public String formInputProduk(@RequestParam(value = "id", required = false) Integer id,
    Principal principal,
    ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        if(produk==null){
            produk = new Produk();
        }else{
            Umkm umkm= umkmDao.getUmkmByUsername(principal.getName());
        
            if(umkm.getId() != produk.getUmkm().getId()){
                return "redirect:daftar-produk";
            }
        }
        
        List<KategoriProduk> kategoriProduks = kategoriProdukDao.getAllKategoriProduk();
        List<Umkm> umkms = umkmDao.getAllUmkm();
        
        modelMap.addAttribute("produk", produk);
        
        modelMap.addAttribute("listUmkm", umkms);
        modelMap.addAttribute("listKategoriProduk", kategoriProduks);
        
        return null;
    }
    
    
}
