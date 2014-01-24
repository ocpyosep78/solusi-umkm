/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidratul.solusiumkm.user.controller;

import com.sidratul.solusiumkm.dao.KategoriUmkmDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriUmkm;
import com.sidratul.solusiumkm.model.Pesan;
import com.sidratul.solusiumkm.model.Umkm;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profil-umkm")
public class UserUmkmController {
    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriUmkmDao kategoriUmkmDao;
    
    private List<Pesan> pesans;
    private Boolean error;
    
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
    ModelMap modelMap,
    RedirectAttributes redirectAttributes){
        
        error=false;
        pesans = new ArrayList<Pesan>();
        
        if(umkm.getNamaUmkm()==""){
            setPesanGagal("Nama UMKM harus diisi");
        }
        
        if(umkm.getPemilikUmkm()==""){
            setPesanGagal("Pemilik UMKM harus diisi");
        }
        
        if(umkm.getUsername() ==""){
            setPesanGagal("Username harus di isi");
        }else{
            final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(umkm.getUsername());
            
            Umkm umkm1 = umkmDao.getUmkmByUsernameDanBukanId(umkm.getUsername(),umkm.getId());
             
            if(!matcher.matches()){
                setPesanGagal("Username harus di mengandung 3 sampai 15 karakter yang terdiri dari huruf, angka, - atau _ ");
            }if(umkm1!=null){
                setPesanGagal("Username sudah dimiliki oleh UMKM yang lain");
            }
        }
        
        if(umkm.getPassword()==""){
            setPesanGagal("Password harus diisi");
        }
        
        if(umkm.getPasswordCek()==""){
            setPesanGagal("Password harus dituliskan kembali");
        }
        
        if(umkm.getPasswordCek()!="" && umkm.getPassword()!=""){
            
            if(umkm.getPassword().compareTo(umkm.getPasswordCek())!=0){
                setPesanGagal("Password tidak cocok");
            }
            
        }
        
        if(umkm.getPasswordLama()==""){
            setPesanGagal("Password Lama harus diisi");
        }else{
            Umkm umkm1 = umkmDao.getUmkmById(umkm.getId());
            if(umkm.getPasswordLama().compareTo(umkm1.getPassword())!=0){
                setPesanGagal("Password lama tidak cocok");
            }
        }
        
        if(umkm.getKategoriUmkm().getId() == null){
            setPesanGagal("Kategori UMKM harus dipilih");
        }
        
        if(error){
        
            List<KategoriUmkm> kategoriUmkms =  kategoriUmkmDao.getAllKategoriUmkm();
        
            modelMap.addAttribute("listKategoriUmkm",kategoriUmkms);
            modelMap.addAttribute("listPesan", pesans);
            return "profil-umkm/edit";
        }
        
        
        setPesanBerhasil("Berhasil mengubah data UMKM");
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        umkmDao.saveUmkm(umkm);
        return "redirect:detail";
    }
    
    private void setPesanBerhasil(String isiPesan){
        setPesan(isiPesan, "success");
    }
    
    private void setPesanGagal(String isiPesan){
        error = true;
        setPesan(isiPesan, "danger");
    }
    
    private void setPesan(String isiPesan, String jenisPesan){
        Pesan pesan = new Pesan();
        pesan.setJenisPesan(jenisPesan);
        pesan.setIsiPesan(isiPesan);
        pesans.add(pesan);
    }
}
