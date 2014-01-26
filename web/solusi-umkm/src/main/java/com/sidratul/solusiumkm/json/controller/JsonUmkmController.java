package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriUmkm;
import com.sidratul.solusiumkm.model.Umkm;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/umkm")
public class JsonUmkmController {
    @Autowired UmkmDao umkmDao;
    
    @RequestMapping("/tampil")
    public @ResponseBody List<Umkm> tampilUmkm(){
        return umkmDao.getAllUmkm();
    }
    
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public @ResponseBody Umkm tampilUmkmSatu(@RequestParam("id") Integer id){
        return umkmDao.getUmkmById(id);
    }
    
    @RequestMapping(value = "/cek-username",method = RequestMethod.POST)
    public @ResponseBody Umkm tampilUmkmCekUsername(@RequestParam("id") Integer id, @RequestParam("username") String username){
        return umkmDao.getUmkmByUsernameDanBukanId(username, id);
    }
    
    @RequestMapping(value = "/edit-umkm",method = RequestMethod.POST)
    public @ResponseBody void prosesInputUmkm(@RequestParam("id") Integer id,
    @RequestParam("kodeUmkm") String kodeUmkm,
    @RequestParam("namaUmkm") String namaUmkm,
    @RequestParam("pemilikUmkm") String pemilikUmkm,
    @RequestParam("kategoriUmkm") Integer idKategoriUmkm,
    @RequestParam("keteranganUmkm") String keteranganUmkm,
    @RequestParam("visi") String visi,
    @RequestParam("misi") String misi,
    @RequestParam("alamat") String alamat,
    @RequestParam("noTelp") String noTelp,
    @RequestParam("email") String email,
    @RequestParam("username") String username,
    @RequestParam("password") String password){
        Umkm umkm = new Umkm();
        
        umkm.setId(id);
        umkm.setKodeUmkm(kodeUmkm);
        umkm.setNamaUmkm(namaUmkm);
        umkm.setPemilikUmkm(pemilikUmkm);
        
        KategoriUmkm kategoriUmkm = new KategoriUmkm();
        kategoriUmkm.setId(idKategoriUmkm);
        
        umkm.setKategoriUmkm(kategoriUmkm);
        umkm.setKeteranganUmkm(keteranganUmkm);
        umkm.setVisi(visi);
        umkm.setMisi(misi);
        umkm.setAlamat(alamat);
        umkm.setNoTelp(noTelp);
        umkm.setEmail(email);
        umkm.setUsername(username);
        umkm.setPassword(password);
        
        
        umkmDao.saveUmkm(umkm);
        return;
    }
}
