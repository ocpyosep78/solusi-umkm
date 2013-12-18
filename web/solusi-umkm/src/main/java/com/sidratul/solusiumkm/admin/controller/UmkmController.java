package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.KategoriUmkmDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriUmkm;
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
    @Autowired private KategoriUmkmDao kategoriUmkmDao;
    
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
    
    @RequestMapping(value = "/input-umkm",method = RequestMethod.GET)
    public void formInputUmkm(@RequestParam(value = "id",required = false) Integer id,
    ModelMap modelMap){
        Umkm umkm = umkmDao.getUmkmById(id);
        if(umkm==null){
            umkm= new Umkm();
        }
        
        List<KategoriUmkm> kategoriUmkms =  kategoriUmkmDao.getAllKategoriUmkm();
        
        modelMap.addAttribute("umkm", umkm);
        modelMap.addAttribute("listKategoriUmkm",kategoriUmkms);
    }
    
    @RequestMapping(value = "/input-umkm",method = RequestMethod.POST)
    public String prosesInputUmkm(@ModelAttribute Umkm umkm ,
    ModelMap modelMap){
        umkmDao.saveUmkm(umkm);
        return "redirect:index";
    }
    
    
    @RequestMapping("/hapus-umkm")
    public String hapusUmkm(@RequestParam("id") Integer id,
    ModelMap modelMap){
        umkmDao.deleteUmkm(id);
        return "redirect:index";
    }
    
    
//    untuk kategori umkm
    @RequestMapping("/kategori")
    public void tampilKategoriUmkm(ModelMap modelMap){
        List<KategoriUmkm> kategoriUmkms = kategoriUmkmDao.getAllKategoriUmkm();
        modelMap.addAttribute("listKategoriUmkm", kategoriUmkms);
    }
    
    @RequestMapping(value = "/input-kategori",method = RequestMethod.GET)
    public void formInputKategoriUmkm(@RequestParam(value = "id",required = false) Integer id,
    ModelMap modelMap){
        KategoriUmkm kategoriUmkm = kategoriUmkmDao.getKategoriUmkmById(id);
        if(kategoriUmkm== null){
            kategoriUmkm = new KategoriUmkm();
        }
        
        modelMap.addAttribute("kategoriUmkm",kategoriUmkm);
    }
    
    @RequestMapping(value = "/input-kategori",method = RequestMethod.POST)
    public String prosesInputKategoriUmkm(@ModelAttribute KategoriUmkm kategoriUmkm ,
    ModelMap modelMap){
        kategoriUmkmDao.saveKategoriUmkm(kategoriUmkm);
        return "redirect:kategori";
    }
    
    @RequestMapping("/hapus-kategori")
    public String hapusKategoriUmkm(@RequestParam("id") Integer id,
    ModelMap modelMap){
        kategoriUmkmDao.deleteKategoriUmkm(id);
        return "redirect:kategori";
    }
}