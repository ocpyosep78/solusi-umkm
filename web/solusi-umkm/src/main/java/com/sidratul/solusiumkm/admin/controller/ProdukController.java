package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.FotoDao;
import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Foto;
import com.sidratul.solusiumkm.model.KategoriProduk;
import com.sidratul.solusiumkm.model.Produk;
import com.sidratul.solusiumkm.model.Umkm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

@Controller
@RequestMapping("/produk")
public class ProdukController {
    @Autowired private ProdukDao produkDao;
    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriProdukDao kategoriProdukDao;
    @Autowired private FotoDao fotoDao;
    
    @RequestMapping("/index")
    public void tampilUmkm(ModelMap modelMap){
        List<Produk> produks = produkDao.getAllProduk();
        modelMap.addAttribute("listProduk",produks);
    }
    
    @RequestMapping(value = "/input-produk",method = RequestMethod.GET)
    public void formInputProduk(@RequestParam(value = "id", required = false) Integer id,
    ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        if(produk==null){
            produk = new Produk();
        }
        
        List<KategoriProduk> kategoriProduks = kategoriProdukDao.getAllKategoriProduk();
        List<Umkm> umkms = umkmDao.getAllUmkm();
        
        modelMap.addAttribute("produk", produk);
        
        modelMap.addAttribute("listUmkm", umkms);
        modelMap.addAttribute("listKategoriProduk", kategoriProduks);
    }
    
    @RequestMapping(value = "/input-produk",method = RequestMethod.POST)
    public String prosesInputProduk(@ModelAttribute Produk produk,
    ModelMap modelMap,
    HttpServletRequest request) throws FileNotFoundException, IOException{
        produk.setTglUpdateProduk(new Date());
        
        List<MultipartFile> files = produk.getFiles();
        
        if(files!= null && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
 
                SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyssmmHH");
                String s = format.format(new Date());
                        
                String namaFile = StringUtils.trimAllWhitespace(s+produk.getNamaProduk()+files.indexOf(multipartFile)+multipartFile.getOriginalFilename());
                //System.out.println("file name : "+fileName);
                
                Foto foto = new Foto();
                foto.setNamaFile(namaFile);
                foto.setTglUpload(new Date());
                
                File localFile = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+namaFile);
                OutputStream out = new FileOutputStream(localFile);
                out.write(multipartFile.getBytes());
                out.close();
                
                fotoDao.saveFoto(foto);
                foto = fotoDao.getFotoByNamaFile(namaFile);
                fotoDao.saveDistribusiFoto(produk.getUmkm().getId(), foto.getId());
                
            }
        }
        
        produkDao.saveProduk(produk);
        return "redirect:index";
    }
    
    @RequestMapping("/hapus-produk")
    public String hapusProduk(@RequestParam("id") Integer id,
    ModelMap modelMap){
        produkDao.deleteProduk(id);
        return "redirect:index";
    }
    
//    kategori produk
    @RequestMapping("/kategori")
    public void tampilKategoriProduk(ModelMap modelMap){
        List<KategoriProduk> kategoriProduks = kategoriProdukDao.getAllKategoriProduk();
        modelMap.addAttribute("listKategoriProduk", kategoriProduks);
    }
    
    @RequestMapping(value = "/input-kategori",method = RequestMethod.GET)
    public void formInputKategoriProduk(@RequestParam(value = "id",required = false) Integer id,
    ModelMap modelMap){
        KategoriProduk kategoriProduk = kategoriProdukDao.getKategoriProdukById(id);
        if(kategoriProduk==null){
            kategoriProduk = new KategoriProduk();
        }
        
        modelMap.addAttribute("kategoriProduk", kategoriProduk);
    }
    
    @RequestMapping(value = "/input-kategori",method = RequestMethod.POST)
    public String prosesInputKategoriProduk(@ModelAttribute KategoriProduk kategoriProduk,
    ModelMap modelMap){
        kategoriProdukDao.saveKategoriProduk(kategoriProduk);
        return "redirect:kategori";
    }
    
    @RequestMapping("/hapus-kategori")
    public String hapusKategoriProduk(@RequestParam("id") Integer id,
    ModelMap modelMap){
        kategoriProdukDao.deleteKategoriProduk(id);
        return "redirect:kategori";
    }
}
