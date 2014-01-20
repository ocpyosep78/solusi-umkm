package com.sidratul.solusiumkm.admin.controller;

import com.sidratul.solusiumkm.dao.FotoDao;
import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Foto;
import com.sidratul.solusiumkm.model.KategoriProduk;
import com.sidratul.solusiumkm.model.Pesan;
import com.sidratul.solusiumkm.model.Produk;
import com.sidratul.solusiumkm.model.Umkm;
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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produk")
public class AdminProdukController {
    @Autowired private ProdukDao produkDao;
    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriProdukDao kategoriProdukDao;
    @Autowired private FotoDao fotoDao;
    
    private List<Pesan> pesans;
    private boolean error;
    
    @RequestMapping("/index")
    public void tampilProduk(ModelMap modelMap){
        List<Produk> produks = produkDao.getAllProduk();
        modelMap.addAttribute("listProduk",produks);
    }
    
    @RequestMapping("/detail")
    public void detailProduk(@RequestParam("id") Integer id,
    ModelMap modelMap){
        Produk produk = produkDao.getProdukById(id);
        modelMap.addAttribute("produk", produk);
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
    HttpServletRequest request,
    RedirectAttributes redirectAttributes) throws FileNotFoundException, IOException{
        error = false;
        pesans = new ArrayList<Pesan>();
        
        produk.setTglUpdateProduk(new Date());
        
        if(produk.getKodeProduk()==""){
            setPesanGagal("Nama produk harus diisi");
        }
        
        if(produk.getNamaProduk()==""){
            setPesanGagal("Nama produk harus diisi");
        }
        
        if(produk.getKategoriProduk().getId()==null){
            setPesanGagal("Kategori produk harus dipilih");
        }
        
        if(produk.getUmkm().getId()==null){
            setPesanGagal("UMKM harus dipilih");
        }
        
        if(produk.getUmkm().getId() != null && produk.getKodeProduk()!=""){
            if(produk.getId()!=null){
                cekKodeProdukEdit(produk);
            }else{
                cekKodeProdukInput(produk);
            }
        }
       
        if(error){
            List<Umkm> umkms = umkmDao.getAllUmkm();

            modelMap.addAttribute("listUmkm", umkms);
            modelMap.addAttribute("listPesan", pesans);
            return "produk/input-produk";
        }
        
        List<MultipartFile> files = produk.getFiles();
        
        produkDao.saveProduk(produk);
        produk = produkDao.getProdukByKode(produk.getKodeProduk(), produk.getUmkm().getId(), produk.getNamaProduk());
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
        
        
        setPesanBerhasil("Berhasil menghapus produk");
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        return "redirect:index";
    }
    
    @RequestMapping("/hapus-produk")
    public String hapusProduk(@RequestParam("id") Integer id,
    HttpServletRequest request,
    ModelMap modelMap,
    RedirectAttributes redirectAttributes){        
        pesans = new ArrayList<Pesan>();
        List<Foto> fotos = fotoDao.getAllFotoByIdProduk(id);
        
        fotoDao.DeleteDistribusiFotoByIdProduk(id);
        
        for (Foto foto : fotos) {
            fotoDao.DeleteFotoByid(foto.getId());
            File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+foto.getNamaFile());
        
            if(file.exists() && file.isFile()) {
                file.delete();
            }
        }
        
        setPesanBerhasil("Berhasil menghapus produk");
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        
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
    ModelMap modelMap,
    RedirectAttributes redirectAttributes){
        error = false;
        pesans = new ArrayList<Pesan>();
        
        if(kategoriProduk.getJenisProduk()==""){
            setPesanGagal("Jenis kategori produk harus diisi");
        }
        
        if(error){
            modelMap.addAttribute("listPesan", pesans);
            return "produk/input-kategori";
        }
        
        kategoriProdukDao.saveKategoriProduk(kategoriProduk);
        
        setPesanBerhasil("Berhasil menambahkan kategori produk");
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        return "redirect:kategori";
    }
    
    @RequestMapping("/hapus-kategori")
    public String hapusKategoriProduk(@RequestParam("id") Integer id,
    ModelMap modelMap,
    RedirectAttributes redirectAttributes){
        error=false;
        pesans = new ArrayList<Pesan>();
        try{
            kategoriProdukDao.deleteKategoriProduk(id);
            setPesanBerhasil("Berhasil menghapus kategori produk");
        }catch(DataIntegrityViolationException dive){
            setPesanGagal("Gagal menghapus kategori produk. Kategori sedang digunakan");
        }
        
        redirectAttributes.addFlashAttribute("listPesan",pesans);
        return "redirect:kategori";
    }
    
    //foto
    @RequestMapping("/hapus-foto")
    public String hapusFotoProduk(@RequestParam("id") Integer id,
    @RequestParam("idProduk") Integer idProduk,
    @RequestParam("namaFile") String namaFile,
    HttpServletRequest request,
    ModelMap modelMap){
        error=false;
        pesans = new ArrayList<Pesan>();
        
        produkDao.deleteDistribusiFotoByIdFoto(id);
        produkDao.deleteFotoById(id);
        
        File file = new File(request.getSession().getServletContext().getRealPath("/upload-file")+"/foto/"+namaFile);
        
        if(file.exists() && file.isFile()) {
            file.delete();
        }
        
        setPesanBerhasil("Berhasil menghapus foto produk");
        return "redirect:detail?id="+idProduk;
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
    
    private void cekKodeProdukInput(Produk produk){
        Produk produk1 =  produkDao.getProdukByKodeDanIdUMKM(produk.getKodeProduk(), produk.getUmkm().getId());
        
        if(produk1 != null){
            setPesanGagal("Kode produk sudah digunakan");
        }
        
    }
    
    private void cekKodeProdukEdit(Produk produk){
        Produk produk1 =  produkDao.getProdukByKodeIdUmkmDanBukanIdProduk(produk.getKodeProduk(), produk.getUmkm().getId(), produk.getId());
        
        if(produk1 != null){
            setPesanGagal("Kode produk sudah digunakan");
        }
    }
}
