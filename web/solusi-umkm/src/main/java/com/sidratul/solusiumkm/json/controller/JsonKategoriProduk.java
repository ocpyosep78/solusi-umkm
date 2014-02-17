package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.model.KategoriProduk;
import com.sidratul.solusiumkm.model.Produk;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kategori-produk")
public class JsonKategoriProduk {
    @Autowired KategoriProdukDao kategoriProdukDao;
    @Autowired ProdukDao produkDao;
    
    @RequestMapping(value = "/getkode-produk",method = RequestMethod.GET)
    public @ResponseBody String getKodeProduk(@RequestParam("id") Integer id,
    @RequestParam("idUmkm") Integer idUmkm){
        String kodeProduk;
        
        KategoriProduk kategoriProduk =  kategoriProdukDao.getKategoriProdukById(id);
        kodeProduk=kategoriProduk.getKode();
        Produk produk = produkDao.getProdukTerakhirByIdUmkmDanKategoriProduk(idUmkm, id);
        
        
        if(produk==null){
            kodeProduk = kodeProduk + "001";
        }else{
            Integer angka = new Integer(produk.getKodeProduk().substring(produk.getKodeProduk().length() - 3).replace("0",""));
            angka++;
            for(int i=0;i<(4-angka.toString().length());i++){
                kodeProduk=kodeProduk+"0";
            }
            
            kodeProduk = kodeProduk + angka.toString();
        }
        
        return kodeProduk;
    }
    
    
}
