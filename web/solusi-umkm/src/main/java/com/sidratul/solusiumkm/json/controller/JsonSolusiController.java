package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.SolusiDao;
import com.sidratul.solusiumkm.model.Solusi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/solusi")
public class JsonSolusiController {
    @Autowired private SolusiDao solusiDao;
    
    @RequestMapping("/tampil")
    public @ResponseBody List<Solusi> tampilSolusi(){
        return solusiDao.getAllSolusi();
    }
    
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public @ResponseBody Solusi tampilDetailSolusi(@RequestParam("idKasus") Integer idKasus){
        return solusiDao.getSolusiByIdKasus(idKasus);
    }
}
