
package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.KategoriUmkmDao;
import com.sidratul.solusiumkm.model.KategoriUmkm;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kategori-umkm")
public class JsonKategoriUmkmController {
    @Autowired private KategoriUmkmDao kategoriUmkmDao;
    
    @RequestMapping("/tampil")
    public @ResponseBody List<KategoriUmkm> tampilKategoriUmkm(){
        return kategoriUmkmDao.getAllKategoriUmkm();
    }
    
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public @ResponseBody KategoriUmkm tampilDetailKategoriUmkm(@RequestParam("id") Integer id){
        return kategoriUmkmDao.getKategoriUmkmById(id);
    }
}
