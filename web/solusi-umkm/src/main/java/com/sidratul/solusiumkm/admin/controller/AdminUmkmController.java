package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.KategoriUmkmDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriUmkm;
import com.sidratul.solusiumkm.model.Pesan;
import com.sidratul.solusiumkm.model.Umkm;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/umkm")
public class AdminUmkmController {
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
    ModelMap modelMap,
    RedirectAttributes redirectAttributes ){
        Boolean error=false;
        List<Pesan> pesans = new ArrayList<Pesan>();
        
        
        if(umkm.getKodeUmkm()==""){
                error =true;
                Pesan pesan = new Pesan();
                pesan.setJenisPesan("danger");
                pesan.setIsiPesan("Kode UMKM harus diisi");
                pesans.add(pesan);
        }else{
            Umkm umkm1 = umkmDao.getUmkmByKodeUmkm(umkm.getKodeUmkm());

            if(umkm1!=null){
                error =true;
                Pesan pesan = new Pesan();
                pesan.setJenisPesan("danger");
                pesan.setIsiPesan("Kode UMKM sudah dimiliki oleh UMKM yang lain");
                pesans.add(pesan);
            }
        }
        
        if(umkm.getUsername() ==""){
            error =true;
            Pesan pesan = new Pesan();
            pesan.setJenisPesan("danger");
            pesan.setIsiPesan("Username harus di isi");
            pesans.add(pesan);
        }else{
            final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(umkm.getUsername());
        
            if(matcher.matches()){
                error =true;
                Pesan pesan = new Pesan();
                pesan.setJenisPesan("danger");
                pesan.setIsiPesan("Username harus di mengandung 3-15 karakter yang terdiri dari a-Z,A-Z,- dan _ ");
                pesans.add(pesan);
            }
        }
        
        if(umkm.getPemilikUmkm()==""){
            error =true;
            Pesan pesan = new Pesan();
            pesan.setJenisPesan("danger");
            pesan.setIsiPesan("Pemilik UMKM harus diisi");
            pesans.add(pesan);
        }
        
        if(umkm.getPassword()==""){
            error =true;
            Pesan pesan = new Pesan();
            pesan.setJenisPesan("danger");
            pesan.setIsiPesan("Password harus diisi");
            pesans.add(pesan);
        }
        
        if(umkm.getPasswordCek()==""){
            error =true;
            Pesan pesan = new Pesan();
            pesan.setJenisPesan("danger");
            pesan.setIsiPesan("Password harus dituliskan kembali");
            pesans.add(pesan);
        }
        
        if(umkm.getPasswordCek()!="" && umkm.getPassword()!=""){
            if(umkm.getPassword() != umkm.getPassword()){
                error =true;
                Pesan pesan = new Pesan();
                pesan.setJenisPesan("danger");
                pesan.setIsiPesan("Password tidak cocok");
                pesans.add(pesan);
            }
        }
        
        if(umkm.getKategoriUmkm().getId() == null){
            error =true;
            Pesan pesan = new Pesan();
            pesan.setJenisPesan("danger");
            pesan.setIsiPesan("Kategori UMKM harus dipilih");
            pesans.add(pesan);
        }
        
        if(error){
            
            List<KategoriUmkm> kategoriUmkms =  kategoriUmkmDao.getAllKategoriUmkm();
        
            modelMap.addAttribute("listKategoriUmkm",kategoriUmkms);
            modelMap.addAttribute("listPesan", pesans);
            return "umkm/input-umkm";
        }
        
        Pesan pesan = new Pesan();
        pesan.setJenisPesan("success");
        pesan.setIsiPesan("berhasil menambahkan umkm");
        
        pesans.add(pesan);
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        
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
    public String prosesInputKategoriUmkm(@ModelAttribute KategoriUmkm kategoriUmkm,
    ModelMap modelMap){
        Boolean error=false;
        List<Pesan> pesans = new ArrayList<Pesan>();
        
        if(kategoriUmkm.getJenisUmkm()==""){
            error =true;
            Pesan pesan = new Pesan();
            pesan.setJenisPesan("danger");
            pesan.setIsiPesan("Jenis UMKM harus di isi");
            pesans.add(pesan);
        }
        
        
        if(error){
            modelMap.addAttribute("listPesan", pesans);
            return "umkm/input-kategori";
        }
        
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