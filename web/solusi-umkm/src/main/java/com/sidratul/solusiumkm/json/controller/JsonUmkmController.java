package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Umkm;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/umkm")
public class JsonUmkmController {
    @Autowired UmkmDao umkmDao;
    
    @RequestMapping("/tampil")
    public @ResponseBody List<Umkm> tampilUmkm(){
        System.out.println("tamppil semua umkm");
        return umkmDao.getAllUmkm();
    }
    
    @RequestMapping("/tampil-satu")
    public @ResponseBody Umkm tampilUmkmSatu(){
        System.out.println("tamppil satu umkm");
        return umkmDao.getUmkmByUsername("sidratul");
    }
}
