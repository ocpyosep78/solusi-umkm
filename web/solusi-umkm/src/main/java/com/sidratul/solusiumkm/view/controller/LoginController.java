package com.sidratul.solusiumkm.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping("/form")
    public void formLogin(ModelMap modelMap){
        
    }
}
