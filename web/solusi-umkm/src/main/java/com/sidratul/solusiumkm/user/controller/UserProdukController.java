package com.sidratul.solusiumkm.user.controller;

import com.sidratul.solusiumkm.dao.ProdukDao;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produk")
public class UserProdukController {
    @Autowired private ProdukDao produkDao;
    
    @RequestMapping("/daftat-produk")
    public void daftarProduk(Principal principal, ModelMap modelMap){
        
    }
    
    
}
