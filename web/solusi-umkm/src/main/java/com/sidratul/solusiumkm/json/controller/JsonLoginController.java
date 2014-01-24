package com.sidratul.solusiumkm.json.controller;

import com.sidratul.solusiumkm.dao.UserUmkmDao;
import com.sidratul.solusiumkm.model.UserUmkm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class JsonLoginController {
    @Autowired private UserUmkmDao userUmkmDao;
    
    @RequestMapping(value = "/cek",method = RequestMethod.POST)
    public @ResponseBody UserUmkm tampilUmkm(@RequestParam("username") String username,
    @RequestParam("password") String password){
        return userUmkmDao.getUserByUsernameAndPassword(username, password);
    }
   
}
