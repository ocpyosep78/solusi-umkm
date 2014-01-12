/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidratul.solusiumkm.user.controller;

import com.sidratul.solusiumkm.dao.KategoriUmkmDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriUmkm;
import com.sidratul.solusiumkm.model.Umkm;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profil-umkm")
public class UserUmkmController {
    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriUmkmDao kategoriUmkmDao;
    
    @RequestMapping("/detail")
    public void detailUmkmSaya(Principal principal,ModelMap modelMap){
        Umkm umkm= umkmDao.getUmkmByUsername(principal.getName());
        
        modelMap.addAttribute("umkm", umkm);
    }
    
    @RequestMapping(value = "/edit",method = RequestMethod.GET )
    public void FormEditProfilUmkm(Principal principal, 
    ModelMap modelMap){
        Umkm umkm= umkmDao.getUmkmByUsername(principal.getName());
        List<KategoriUmkm> kategoriUmkms = kategoriUmkmDao.getAllKategoriUmkm();
        
        modelMap.addAttribute("umkm", umkm);
        modelMap.addAttribute("listKategoriUmkm",kategoriUmkms);
    }
    
    @RequestMapping(value = "/edit",method = RequestMethod.POST )
    public String prosesEditProfilUmkm(@ModelAttribute Umkm umkm,
    Principal principal, 
    ModelMap modelMap){
        
        
        umkmDao.saveUmkm(umkm);
        return "redirect:detail";
    }
}
