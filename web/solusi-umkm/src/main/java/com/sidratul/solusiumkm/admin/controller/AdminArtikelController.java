
package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.ArtikelDao;
import com.sidratul.solusiumkm.model.Artikel;
import com.sidratul.solusiumkm.model.Pesan;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping("/artikel")
public class AdminArtikelController {
    
    @Autowired private ArtikelDao artikelDao;
    private List<Pesan> pesans;
    private boolean error;
    
    @RequestMapping("/index")
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
    ModelMap modelMap,
    HttpServletRequest request,
    RedirectAttributes redirectAttributes) throws FileNotFoundException, IOException{
        error=false;
        pesans = new ArrayList<Pesan>();
        
        if(artikel.getJudul()==""){
            setPesanGagal("Judul harus diisi");
        }
        
        if(artikel.getIsi()==""){
            setPesanGagal("Artikel harus dituliskan");
        }
        
        if(artikel.getId()!=null){
            Artikel artikelLama = artikelDao.getArtikelById(artikel.getId());
            artikel.setNamaFoto(artikelLama.getNamaFoto());
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
        
        artikelDao.saveArtikel(artikel);
        
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        return "redirect:index";
    }
    
    @RequestMapping("/hapus-artikel")
    public String hapusArtikel(@RequestParam("id") Integer id,
    HttpServletRequest request,
    ModelMap modelMap,
    RedirectAttributes redirectAttributes){        
        error=false;
        pesans = new ArrayList<Pesan>();
        
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        
        Artikel artikel = artikelDao.getArtikelById(id);
        
        if(artikel.getNamaFoto()!=null){
            File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+artikel.getNamaFoto());
        
            if(file.exists() && file.isFile()) {
                file.delete();
            }
        }
        
        setPesanBerhasil("Berhasil menghapus artikel");
        redirectAttributes.addFlashAttribute("listPesan",pesans);
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
    
    private void setPesanBerhasil(String isiPesan){
        setPesan(isiPesan, "success");
    }
    
    private void setPesanGagal(String isiPesan){
        error = true;
        setPesan(isiPesan, "danger");
    }
    
    private void setPesan(String isiPesan, String jenisPesan){
        Pesan pesan = new Pesan();
        pesan.setJenisPesan(jenisPesan);
        pesan.setIsiPesan(isiPesan);
        pesans.add(pesan);
    }
}
