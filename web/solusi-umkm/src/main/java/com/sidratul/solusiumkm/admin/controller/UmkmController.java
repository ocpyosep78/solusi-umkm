package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Umkm;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/umkm")
public class UmkmController {
    @Autowired private UmkmDao umkmDao;
    
    @RequestMapping("/lihat")
    public void tampilUmkm(ModelMap modelMap){
        List<Umkm> umkms = umkmDao.getAllUmkm();
        modelMap.addAttribute("listUmkm", umkms);
    }
    
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public void formInputUmkm(@RequestParam(value = "id",required = false) Integer id,
    ModelMap modelMap){
        Umkm umkm = umkmDao.getUmkmById(id);
        if(umkm==null){
            umkm= new Umkm();
        }
        
        modelMap.addAttribute("umkm", umkm);
    }
    
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String prosesInputUmkm(@ModelAttribute Umkm umkm ,
    ModelMap modelMap){
        umkmDao.saveUmkm(umkm);
        return "redirect:lihat";
    }
    
    
    @RequestMapping("/hapus")
    public String hapausUmkm(@RequestParam("id") Integer id,
    ModelMap modelMap){
        umkmDao.deleteUmkm(id);
        return "redirect:lihat";
    }
}
