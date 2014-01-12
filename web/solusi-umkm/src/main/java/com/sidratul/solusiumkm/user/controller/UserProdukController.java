package com.sidratul.solusiumkm.user.controller;

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
import java.security.Principal;
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

@Controller
@RequestMapping("/produk")
public class UserProdukController {
    @Autowired private ProdukDao produkDao;
    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriProdukDao kategoriProdukDao;
    @Autowired private FotoDao fotoDao;
   
    @RequestMapping("/daftar-produk")
    public void daftarProduk(Principal principal, ModelMap modelMap){
        List<Produk> produks = produkDao.getAllProdukByUsername(principal.getName());
        
        modelMap.addAttribute("listProduk", produks);
    }
    
    @RequestMapping("/detail")
    public String detailProduk(@RequestParam("id") Integer id,
    Principal principal,
    ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        
        Umkm umkm= umkmDao.getUmkmByUsername(principal.getName());
        
        if(umkm.getId() != produk.getUmkm().getId()){
            return "redirect:daftar-produk";
        }
        
        modelMap.addAttribute("produk", produk);
        
        return null;
    }
    
    @RequestMapping(value = "/input-produk",method = RequestMethod.GET)
    public String formInputProduk(@RequestParam(value = "id", required = false) Integer id,
    Principal principal,
    ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        if(produk==null){
            produk = new Produk();
        }else{
            Umkm umkm= umkmDao.getUmkmByUsername(principal.getName());
        
            if(umkm.getId() != produk.getUmkm().getId()){
                return "redirect:daftar-produk";
            }
        }
        
        List<KategoriProduk> kategoriProduks = kategoriProdukDao.getAllKategoriProduk();
        List<Umkm> umkms = umkmDao.getAllUmkm();
        
        modelMap.addAttribute("produk", produk);
        
        modelMap.addAttribute("listUmkm", umkms);
        modelMap.addAttribute("listKategoriProduk", kategoriProduks);
        
        return null;
    }
    
    @RequestMapping(value = "/input-produk",method = RequestMethod.POST)
    public String prosesInputProduk(@ModelAttribute Produk produk,
    Principal principal,
    ModelMap modelMap,
    HttpServletRequest request) throws FileNotFoundException, IOException{
        produk.setUmkm(umkmDao.getUmkmByUsername(principal.getName()));
        produk.setTglUpdateProduk(new Date());
        
        List<MultipartFile> files = produk.getFiles();
        
        produkDao.saveProduk(produk);
        produk = produkDao.getProdukByKode(produk.getKodeProduk(), produk.getUmkm().getId(), produk.getNamaProduk());
        //System.out.println("isi :"+files.size());
        
        if(files!= null && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                
                if(!multipartFile.isEmpty()){
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
                    fotoDao.saveDistribusiFoto(produk.getId(), foto.getId());
                }
                
            }
        }
        
        return "redirect:daftar-produk";
    }
    
    @RequestMapping("/hapus-produk")
    public String hapusProduk(@RequestParam("id") Integer id,
    HttpServletRequest request,
    ModelMap modelMap){        
        List<Foto> fotos = fotoDao.getAllFotoByIdProduk(id);
        
        fotoDao.DeleteDistribusiFotoByIdProduk(id);
        
        for (Foto foto : fotos) {
            fotoDao.DeleteFotoByid(foto.getId());
            File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+foto.getNamaFile());
        
            if(file.exists() && file.isFile()) {
                file.delete();
            }
        }
        
        produkDao.deleteProduk(id);
        return "redirect:daftar-produk";
    }
    
    //foto
    @RequestMapping("/hapus-foto")
    public String hapusKategoriProduk(@RequestParam("id") Integer id,
    @RequestParam("idProduk") Integer idProduk,
    @RequestParam("namaFile") String namaFile,
    HttpServletRequest request,
    ModelMap modelMap){
        produkDao.deleteDistribusiFotoByIdFoto(id);
        produkDao.deleteFotoById(id);
        
        File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+namaFile);
        
        if(file.exists() && file.isFile()) {
            file.delete();
        }
        
        return "redirect:detail?id="+idProduk;
    }
    
}
