package com.sidratul.solusiumkm.view.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping("/form")
    public void formLogin(ModelMap modelMap){
        
    }
    
    @RequestMapping("/berhasil")
    public String loginBerhasil(ModelMap modelMap, Principal principal, HttpServletRequest request){
        String redirect="redirect:";
        if(request.isUserInRole("ROLE_ADMIN")){
            redirect+="/admin/umkm/index";
        }else if(request.isUserInRole("ROLE_UMKM")){
            redirect+="/user/umkm/index";
        }
        
        return redirect;
    }
    
    @RequestMapping("/gagal")
    public String loginGagal(ModelMap modelMap){
        modelMap.addAttribute("error", true);
        return "login/form";
    }
    
    @RequestMapping("/logout")
    public String logut(ModelMap modelMap){
        return "login/form";
    }
}
