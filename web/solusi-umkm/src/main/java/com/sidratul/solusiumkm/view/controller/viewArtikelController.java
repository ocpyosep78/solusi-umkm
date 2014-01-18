package com.sidratul.solusiumkm.view.controller;

import com.sidratul.solusiumkm.dao.ArtikelDao;
import com.sidratul.solusiumkm.model.Artikel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/artikel")
public class viewArtikelController {
    @Autowired private ArtikelDao artikelDao;
    
    @RequestMapping("index")
    public void tampilArtikel(ModelMap modelMap){
        List<Artikel> artikels = artikelDao.getAllArtikel();
        modelMap.addAttribute("listArtikel", artikels);
    }
    
    @RequestMapping("/detail")
    public void detailArtikel(@RequestParam("id") Integer id,
    ModelMap modelMap){
        Artikel artikel = artikelDao.getArtikelById(id);
        modelMap.addAttribute("artikel", artikel);
    }
   
}
