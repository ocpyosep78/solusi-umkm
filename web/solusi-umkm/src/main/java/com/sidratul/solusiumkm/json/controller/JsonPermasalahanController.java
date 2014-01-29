
package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.PermasalahanDao;
import com.sidratul.solusiumkm.model.Permasalahan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permasalahan")
public class JsonPermasalahanController {
    
    @Autowired PermasalahanDao permasalahanDao;
     
    @RequestMapping("/tampil")
    public @ResponseBody List<Permasalahan> tampilPermasalahan(){
        return permasalahanDao.getAllPermasalahan();
    }
    
    @RequestMapping(value = "/tampil-by-kasus",method = RequestMethod.POST)
    public @ResponseBody List<Permasalahan> tampilPermasalahanByKasus(@RequestParam("idKasus") Integer idKasus){
        return permasalahanDao.getAllPermasalahanByIdKasus(idKasus);
    }
    
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public @ResponseBody Permasalahan tampilPermasalahanById(@RequestParam("id") Integer id){
        return permasalahanDao.getPermasalahanById(id);
    }
}
