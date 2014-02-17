package com.sidratul.solusiumkm.view.controller;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Umkm;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/umkm")
public class UmkmController {
    @Autowired private UmkmDao umkmDao;
    
    @RequestMapping("/index")
    public void tampilUmkm(ModelMap modelMap){
        List<Umkm> umkms = umkmDao.getAllUmkm();
        
        modelMap.addAttribute("listUmkm", umkms);
    }
    
    @RequestMapping("/detail")
    public void detailUmkm(@RequestParam("id") Integer id,
    ModelMap modelMap){
        Umkm umkm = umkmDao.getUmkmById(id);
        
        modelMap.addAttribute("umkm", umkm);
    }
}
