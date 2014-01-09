package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.dao.UserUmkmDao;
import com.sidratul.solusiumkm.model.Umkm;
import com.sidratul.solusiumkm.model.UserUmkm;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user-umkm")
public class AdminUserUmkmController {
    @Autowired private UserUmkmDao userUmkmDao;
    @Autowired private UmkmDao umkmDao;
    
    @RequestMapping("/index")
    public void tampilProduk(@RequestParam(value = "aktif",required = false) Integer aktif,ModelMap modelMap){
        if(aktif==null || aktif==1){
            aktif=1;
        }else{
            aktif=0;
        }
        List<UserUmkm> userUmkms = userUmkmDao.getAllUser(aktif);
        
        modelMap.addAttribute("listUserUmkm",userUmkms);
        modelMap.addAttribute("aktif",aktif);
    }
    
    @RequestMapping(value = "/input-user",method = RequestMethod.GET)
    public void formInputProduk(@RequestParam(value = "id", required = false) Integer id,
    ModelMap modelMap){
        UserUmkm userUmkm = userUmkmDao.getUserById(id);
        if(userUmkm==null){
            userUmkm = new UserUmkm();
        }
        
        List<Umkm> umkms = umkmDao.getAllUmkm();
        
        modelMap.addAttribute("userUmkm", userUmkm);
        
        modelMap.addAttribute("listUmkm", umkms);
    }
    
    @RequestMapping(value = "/input-user",method = RequestMethod.POST)
    public String prosesInputUser(@ModelAttribute UserUmkm userUmkm,
    ModelMap modelMap){
        userUmkm.setTerakhirLogin(new Date());
        userUmkm.setPeran("ROLE_UMKM");
        
        userUmkmDao.saveUserUmkm(userUmkm);
        
        return "redirect:index?aktif="+userUmkm.getAktif();
    }
    
    @RequestMapping("/hapus-user")
    public String hapusUser(@RequestParam("id") Integer id, @RequestParam("aktif") Integer aktif,
    ModelMap modelMap){
        umkmDao.deleteUmkm(id);
        return "redirect:index?aktif="+aktif;
    }

}
