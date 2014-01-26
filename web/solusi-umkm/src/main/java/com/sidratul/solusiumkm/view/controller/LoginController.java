package com.sidratul.solusiumkm.view.controller;

import com.sidratul.solusiumkm.dao.UserUmkmDao;
import com.sidratul.solusiumkm.model.UserUmkm;
import java.security.Principal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired private UserUmkmDao userUmkmDao;
    
    @RequestMapping("/form")
    public String formLogin(ModelMap modelMap, Principal principal){
        if(principal!=null){
            return "redirect:berhasil";
        }
        
//        String password = "plaintextPassword";
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(password);
        return null;
                
    }
    
    @RequestMapping("/berhasil")
    public String loginBerhasil(ModelMap modelMap, Principal principal, HttpServletRequest request){
        
        
        String redirect="redirect:";
        if(request.isUserInRole("ROLE_ADMIN")){
            redirect+="/admin/umkm/index";
        }else if(request.isUserInRole("ROLE_UMKM")){
            UserUmkm userUmkm = userUmkmDao.getUserByUsername(principal.getName());
        
            userUmkm.setTerakhirLogin(new Date());
            userUmkmDao.saveUserUmkm(userUmkm);
            
            redirect+="/user/profil-umkm/detail";
        }
        
        return redirect;
    }
    
    @RequestMapping("/gagal")
    public String loginGagal(ModelMap modelMap, Principal principal){
        if(principal!=null){
            return "redirect:berhasil";
        }
        
        modelMap.addAttribute("error", true);
        return "login/form";
    }
    
    @RequestMapping("/logout")
    public String logut(ModelMap modelMap, Principal principal){
        if(principal!=null){
            return "redirect:berhasil";
        }
        
        return "login/form";
    }
}
