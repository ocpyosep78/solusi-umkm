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
    
    private List<Pesan> pesans;
    private Boolean error;
    
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
        
        umkm.setPasswordCek(umkm.getPassword());
        
        List<KategoriUmkm> kategoriUmkms =  kategoriUmkmDao.getAllKategoriUmkm();
        
        modelMap.addAttribute("umkm", umkm);
        modelMap.addAttribute("listKategoriUmkm",kategoriUmkms);
    }
    
    @RequestMapping(value = "/input-umkm",method = RequestMethod.POST)
    public String prosesInputUmkm(@ModelAttribute Umkm umkm ,
    ModelMap modelMap,
    RedirectAttributes redirectAttributes ){
        error=false;
        pesans = new ArrayList<Pesan>();
        
        if(umkm.getId()!=null){
            cekKodeUmkmEdit(umkm);
        }else{
            cekKodeUmkmInput(umkm);
        }
        
        if(umkm.getNamaUmkm()==""){
            setPesan("danger", "Nama UMKM harus diisi");
        }
        
        if(umkm.getPemilikUmkm()==""){
            setPesan("danger", "Pemilik UMKM harus diisi");
        }
        
        if(umkm.getId()!=null){
            cekUsernameEdit(umkm);
        }else{
            cekUsernameInput(umkm);
        }
        
        if(umkm.getPassword()==""){
            setPesan("danger", "Password harus diisi");
        }
        
        if(umkm.getPasswordCek()==""){
            setPesan("danger", "Password harus dituliskan kembali");
        }
        
        if(umkm.getPasswordCek()!="" && umkm.getPassword()!=""){
            System.out.println("pass dan ulang tidak kosong");
            System.out.println(umkm.getPassword());
            System.out.println(umkm.getPasswordCek());
            
            if(umkm.getPassword().compareTo(umkm.getPasswordCek())!=0){
                setPesan("danger", "Password tidak cocok");
            }
            
        }
        
        if(umkm.getKategoriUmkm().getId() == null){
            setPesan("danger", "Kategori UMKM harus dipilih");
        }
        
        if(error){
            
            List<KategoriUmkm> kategoriUmkms =  kategoriUmkmDao.getAllKategoriUmkm();
        
            modelMap.addAttribute("listKategoriUmkm",kategoriUmkms);
            modelMap.addAttribute("listPesan", pesans);
            return "umkm/input-umkm";
        }
        
        
        if(umkm.getId()!=null){
            setPesan("success", "berhasil mengedit UMKM");
        }else{        
            setPesan("success", "berhasil menambahkan UMKM");
        }
        
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        
        umkmDao.saveUmkm(umkm);
        return "redirect:index";
    }
    
    private void cekKodeUmkmInput(Umkm umkm){
        if(umkm.getKodeUmkm()==""){
            setPesan("danger", "Kode UMKM harus diisi");
        }else{
            Umkm umkm1 = umkmDao.getUmkmByKodeUmkm(umkm.getKodeUmkm());
            
            if(umkm1!=null){
                setPesan("danger", "Kode UMKM sudah dimiliki oleh UMKM yang lain");
            }
            
        }
    }
    
    private void cekKodeUmkmEdit(Umkm umkm){
        if(umkm.getKodeUmkm()==""){
            setPesan("danger", "Kode UMKM harus diisi");
        }else{
            Umkm umkm1 = umkmDao.getUmkmById(umkm.getId());
            Umkm umkm2 = umkmDao.getUmkmByKodeUmkm(umkm.getKodeUmkm());
            
            if(umkm2!=null){
                if(umkm1.getKodeUmkm()==umkm2.getKodeUmkm()){
                    setPesan("danger", "Kode UMKM sudah dimiliki oleh UMKM yang lain");
                }
            }
            
        }
    }
    
    private void cekUsernameInput(Umkm umkm){
        if(umkm.getUsername() ==""){
            setPesan("danger", "Username harus di isi");
        }else{
            final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(umkm.getUsername());
            
            Umkm umkm1 = umkmDao.getUmkmByUsername(umkm.getUsername());
             
            if(!matcher.matches()){
                setPesan("danger", "Username harus di mengandung 3-15 karakter yang terdiri dari a-Z,A-Z,- dan _ ");                
            }else if(umkm1!=null){
                setPesan("danger", "Username sudah dimiliki oleh UMKM yang lain");
            }
        }
    }
    
    private void cekUsernameEdit(Umkm umkm){
        if(umkm.getUsername() ==""){
            setPesan("danger", "Username harus di isi");
        }else{
            final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(umkm.getUsername());
            
            Umkm umkm1 = umkmDao.getUmkmByUsernameDanBukanId(umkm.getUsername(),umkm.getId());
             
            if(!matcher.matches()){
                setPesan("danger", "Username harus di mengandung 3-15 karakter yang terdiri dari a-Z,A-Z,- dan _ ");                
            }if(umkm1!=null){
                    setPesan("danger", "Username sudah dimiliki oleh UMKM yang lain");
            }
        }
    }
    
    private void setPesan(String jenisPesan, String isiPesan){
        error =true;
        Pesan pesan = new Pesan();
        pesan.setJenisPesan(jenisPesan);
        pesan.setIsiPesan(isiPesan);
        pesans.add(pesan);
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