
package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.ArtikelDao;
import com.sidratul.solusiumkm.model.Artikel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller()
@RequestMapping("/artikel")
public class AdminArtikelController {
    
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
    
    @RequestMapping(value = "/input-artikel",method = RequestMethod.GET)
    public void formInputArtikel(@RequestParam(value = "id", required = false) Integer id,
    ModelMap modelMap){
        Artikel artikel = artikelDao.getArtikelById(id);
        if(artikel==null){
            artikel = new Artikel();
        }else{
            artikel.setIsi(artikel.getIsi().replace("<br>\n", "\n"));
        }
        
        modelMap.addAttribute("artikel", artikel);
    }
    
    @RequestMapping(value = "/input-artikel",method = RequestMethod.POST)
    public String prosesInputArtikel(@ModelAttribute Artikel artikel,
    @RequestParam("foto") MultipartFile foto,
    @RequestParam("file") MultipartFile file,
    ModelMap modelMap,
    HttpServletRequest request) throws FileNotFoundException, IOException{
        
        
        if(artikel.getId()!=null){
            Artikel artikelLama = artikelDao.getArtikelById(artikel.getId());
            artikel.setNamaFoto(artikelLama.getNamaFoto());
            artikel.setNamaFile(artikelLama.getNamaFile());
        }
        artikel.setTglUpdate(new Date());
        
        
        if(!foto.isEmpty()){
            if(artikel.getNamaFoto()!=null){
                File localFile = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+artikel.getNamaFoto());

                if(localFile.exists() && localFile.isFile()) {
                    localFile.delete();
                }
            }
        
            
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyssmmHH");
            String s = format.format(new Date());

            String namaFile = StringUtils.trimAllWhitespace(s+foto.getOriginalFilename());
            artikel.setNamaFoto(namaFile);

            File localFile = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+namaFile);
            OutputStream out = new FileOutputStream(localFile);
            out.write(foto.getBytes());
            out.close();
        }
        
        if(!file.isEmpty()){
            
            if(artikel.getNamaFile()!=null){
                File localFile = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+artikel.getNamaFile());

                if(localFile.exists() && localFile.isFile()) {
                    localFile.delete();
                }
            }
            
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyssmmHH");
            String s = format.format(new Date());

            String namaFile = StringUtils.trimAllWhitespace(s+file.getOriginalFilename());
            artikel.setNamaFile(namaFile);
            
            File localFile = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/aplikasi/"+namaFile);
            OutputStream out = new FileOutputStream(localFile);
            out.write(file.getBytes());
            out.close();
        }
        
        artikelDao.saveArtikel(artikel);
        return "redirect:index";
    }
    
    @RequestMapping("/hapus-artikel")
    public String hapusArtikel(@RequestParam("id") Integer id,
    HttpServletRequest request,
    ModelMap modelMap){        
        
        Artikel artikel = artikelDao.getArtikelById(id);
        
        if(artikel.getNamaFoto()!=null){
            File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+artikel.getNamaFoto());
        
            if(file.exists() && file.isFile()) {
                file.delete();
            }
        }
        
        if(artikel.getNamaFile()!=null){
            File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/aplikasi/"+artikel.getNamaFile());
        
            if(file.exists() && file.isFile()) {
                file.delete();
            }
        }
        
        artikelDao.deleteArtikel(id);
        return "redirect:index";
    }
    
    @RequestMapping("/hapus-foto")
    public String hapusFotoArtikel(@RequestParam("id") Integer id,
    HttpServletRequest request,
    ModelMap modelMap){        
        
        Artikel artikel = artikelDao.getArtikelById(id);
        artikel.setIsi(artikel.getIsi().replace("<br>\n", "\n"));
        if(artikel.getNamaFoto()!=null){
            File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+artikel.getNamaFoto());
        
            if(file.exists() && file.isFile()) {
                file.delete();
                artikel.setNamaFoto(null);
            }
        }

        artikelDao.saveArtikel(artikel);
        return "redirect:detail?id="+artikel.getId();
    }
    
    @RequestMapping("/hapus-file")
    public String hapusFileArtikel(@RequestParam("id") Integer id,
    HttpServletRequest request,
    ModelMap modelMap){        
        
        Artikel artikel = artikelDao.getArtikelById(id);
        artikel.setIsi(artikel.getIsi().replace("<br>\n", "\n"));
        
        if(artikel.getNamaFile()!=null){
            File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/aplikasi/"+artikel.getNamaFile());
        
            if(file.exists() && file.isFile()) {
                file.delete();
                artikel.setNamaFile(null);
            }
        }

        artikelDao.saveArtikel(artikel);
        return "redirect:detail?id="+artikel.getId();
    }
}
