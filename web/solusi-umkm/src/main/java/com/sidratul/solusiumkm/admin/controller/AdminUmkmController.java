package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Pesan;
import com.sidratul.solusiumkm.model.Umkm;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            
            Umkm umkm1 = umkmDao.getUmkmTerakhir();
            String kodeUmkm="umkm";
            if(umkm1==null){
                kodeUmkm="umkm0001";
            }else{
                Integer angka= new Integer((umkm1.getKodeUmkm().substring(4)).replace("0",""));
                angka++;
                for(int i=0;i<(4-angka.toString().length());i++){
                    kodeUmkm=kodeUmkm+"0";
                }

                kodeUmkm= kodeUmkm+angka.toString();
            }
            
            umkm.setKodeUmkm(kodeUmkm);
        }
        
        umkm.setPasswordCek(umkm.getPassword());
        modelMap.addAttribute("umkm", umkm);
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
            setPesanGagal("Nama UMKM harus diisi");
        }
        
        if(umkm.getPemilikUmkm()==""){
            setPesanGagal("Pemilik UMKM harus diisi");
        }
        
        if(umkm.getId()!=null){
            cekUsernameEdit(umkm);
        }else{
            cekUsernameInput(umkm);
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
        
        if(error){
            
            modelMap.addAttribute("listPesan", pesans);
            return "umkm/input-umkm";
        }
        
        
        if(umkm.getId()!=null){
            setPesanBerhasil("berhasil mengedit UMKM");
        }else{        
            setPesanBerhasil("berhasil menambahkan UMKM");
        }
        
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        
        umkmDao.saveUmkm(umkm);
        return "redirect:index";
    }
    
    private void cekKodeUmkmInput(Umkm umkm){
        if(umkm.getKodeUmkm()==""){
            setPesanGagal("Kode UMKM harus diisi");
        }else{
            Umkm umkm1 = umkmDao.getUmkmByKodeUmkm(umkm.getKodeUmkm());
            
            if(umkm1!=null){
                setPesanGagal("Kode UMKM sudah dimiliki oleh UMKM yang lain");
            }
            
        }
    }
    
    private void cekKodeUmkmEdit(Umkm umkm){
        if(umkm.getKodeUmkm()==""){
            setPesanGagal("Kode UMKM harus diisi");
        }else{
            Umkm umkm2 = umkmDao.getUmkmByKodeUmkmDanBukanId(umkm.getKodeUmkm(), umkm.getId());
            
            if(umkm2!=null){
                setPesanGagal("Kode UMKM sudah dimiliki oleh UMKM yang lain");
            }
            
        }
    }
    
    private void cekUsernameInput(Umkm umkm){
        if(umkm.getUsername() ==""){
            setPesanGagal("Username harus di isi");
        }else{
            final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(umkm.getUsername());
            
            Umkm umkm1 = umkmDao.getUmkmByUsername(umkm.getUsername());
             
            if(!matcher.matches()){
                setPesanGagal("Username harus di mengandung 3 sampai 15 karakter yang terdiri dari huruf kecil, angka, - atau _ ");
            }else if(umkm1!=null){
                setPesanGagal("Username sudah dimiliki oleh UMKM yang lain");
            }
        }
    }
    
    private void cekUsernameEdit(Umkm umkm){
        if(umkm.getUsername() ==""){
            setPesanGagal("Username harus di isi");
        }else{
            final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
            Pattern pattern = Pattern.compile(USERNAME_PATTERN);
            Matcher matcher = pattern.matcher(umkm.getUsername());
            
            Umkm umkm1 = umkmDao.getUmkmByUsernameDanBukanId(umkm.getUsername(),umkm.getId());
             
            if(!matcher.matches()){
                setPesanGagal("Username harus mengandung 3 sampai 15 karakter yang terdiri dari huruf kecil, angka, - atau _ ");
            }if(umkm1!=null){
                setPesanGagal("Username sudah dimiliki oleh UMKM yang lain");
            }
        }
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
    
    @RequestMapping("/hapus-umkm")
    public String hapusUmkm(@RequestParam("id") Integer id,
    ModelMap modelMap,
    RedirectAttributes redirectAttributes){
        error=false;
        pesans = new ArrayList<Pesan>();
        
        setPesanBerhasil( "berhasil menghapus UMKM");
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        umkmDao.deleteUmkm(id);
        return "redirect:index";
    }
    
}