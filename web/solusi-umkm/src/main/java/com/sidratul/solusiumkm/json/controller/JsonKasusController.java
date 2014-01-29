package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.KasusDao;
import com.sidratul.solusiumkm.model.Kasus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kasus")
public class JsonKasusController {
    @Autowired private KasusDao kasusDao;
    
    @RequestMapping("/tampil")
    public @ResponseBody List<Kasus> tampilKasus(){
        return kasusDao.getAllKasus();
    }
    
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public @ResponseBody Kasus tampilDetailKasus(@RequestParam("id") Integer id){
        return kasusDao.getKasusById(id);
    }
}
