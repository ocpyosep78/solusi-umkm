package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.FotoDao;
import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriProduk;
import com.sidratul.solusiumkm.model.Produk;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("ProdukDao")
public class ProdukDaoImpl implements ProdukDao{

    private static final String SQL_GETALL_PRODUK="SELECT * FROM produk";
    private static final String SQL_GETALL_PRODUK_BYUSERNAME="select p.* "
            + "from produk p, umkm u "
            + "where u.id = p.id_umkm and u.id in "
            + "(select u.id from user us, umkm m "
            + "where us.id_umkm = u.id and us.username=?)";
    private static final String SQL_GETPRODUK_BYID="SELECT * FROM produk WHERE id=?";
    private static final String SQL_GETPRODUK_BYKODE="SELECT * FROM produk "
            + "WHERE kode_produk=? and id_umkm = ? and nama_produk = ?";
    
    private static final String SQL_GETPRODUK_BYKODE_DANIDUMKM="SELECT * FROM produk "
            + "WHERE kode_produk=? and id_umkm = ?";
    
    private static final String SQL_GETPRODUK_BYKODEIDUMKM_DAN_ID="SELECT * FROM produk "
            + "WHERE kode_produk=? and id_umkm = ? and id != ?";
    
    private static final String SQL_GETPRODUKTERAKHIR_BYIDUMKMDAN_IDKATEGORIPRODUK="SELECT * FROM produk "
            + "WHERE id_umkm=? AND id_kategori_produk=? ORDER BY id DESC LIMIT 1";
    
    private static final String SQL_DELETE_PRODUK="DELETE FROM produk WHERE id=?";
    private static final String SQL_UPDATE_PRODUK="UPDATE `produk` SET `id_umkm` = ?,`id_kategori_produk` = ?,"
            + "`kode_produk` = ?,`nama_produk` = ?,`harga` = ?,`keterangan_produk` = ?,`tgl_update_produk` = ? "
            + "WHERE `id` = ?";
    private static final String SQL_INSERT_PRODUK="INSERT INTO `produk`"
            + "(`id_umkm`,`id_kategori_produk`,`kode_produk`,`nama_produk`,`harga`,`keterangan_produk`,`tgl_update_produk`)"
            + "VALUES(?,?,?,?,?,?,?)";
    
    private static final String SQL_DELETE_FOTOBYID="DELETE FROM foto WHERE id=?";
    private static final String SQL_DELETE_DISTRIBUSIFOTOBYIDPRODUK="DELETE FROM distribusi_foto WHERE id_produk=?";
    private static final String SQL_DELETE_DISTRIBUSIFOTOBYIDFOTO="DELETE FROM distribusi_foto WHERE id_foto=?";

    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriProdukDao kategoriProdukDao;
    @Autowired private FotoDao fotoDao;
    
    private JdbcTemplate jdbcTemplate;

    public final class ProdukParameterizedRowMapper implements ParameterizedRowMapper<Produk>{

        public Produk mapRow(ResultSet rs, int i) throws SQLException {
            Produk produk = new Produk();
            produk.setId(rs.getInt("id"));
            produk.setUmkm(umkmDao.getUmkmById(rs.getInt("id_umkm")));
            produk.setKategoriProduk(kategoriProdukDao.getKategoriProdukById(rs.getInt("id_kategori_produk")));
            produk.setKodeProduk(rs.getString("kode_produk"));
            produk.setNamaProduk(rs.getString("nama_produk"));
            produk.setHarga(rs.getBigDecimal("harga"));
            produk.setKeteranganProduk(rs.getString("keterangan_produk"));
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            try{
                date = format.parse(rs.getString("tgl_update_produk"));
            }catch(Exception e){
                date = null;
            }
            produk.setTglUpdateProduk(date);
            
            produk.setFotos(fotoDao.getAllFotoByIdProduk(rs.getInt("id")));
            
            return produk;
        }
    
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Produk> getAllProduk() {
        List<Produk> produks = jdbcTemplate.query(SQL_GETALL_PRODUK,new ProdukParameterizedRowMapper());
        return produks;
    }
    
    public List<Produk> getAllProdukByUsername(String username) {
        List<Produk> produks = jdbcTemplate.query(SQL_GETALL_PRODUK_BYUSERNAME,new ProdukParameterizedRowMapper(),username);
        return produks;
    }
    
    public void saveProduk(Produk produk) throws DuplicateKeyException {
        if(produk.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_PRODUK, new Object[]{
                produk.getUmkm().getId(),
                produk.getKategoriProduk().getId(),
                produk.getKodeProduk(),
                produk.getNamaProduk(),
                produk.getHarga(),
                produk.getKeteranganProduk(),
                produk.getTglUpdateProduk(),
                produk.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_PRODUK, new Object[]{
                produk.getUmkm().getId(),
                produk.getKategoriProduk().getId(),
                produk.getKodeProduk(),
                produk.getNamaProduk(),
                produk.getHarga(),
                produk.getKeteranganProduk(),
                produk.getTglUpdateProduk()
            });
        }
    }

    public Produk getProdukById(Integer id) {
        if(id==null){
            return null;
        }else{
            Produk produk = jdbcTemplate.queryForObject(SQL_GETPRODUK_BYID, new ProdukParameterizedRowMapper(), id);
            return produk;
        }
    }
    
    public Produk getProdukByKode(String kodeProduk,Integer idUmkm, String namaProduk){
        Produk produk = jdbcTemplate.queryForObject(SQL_GETPRODUK_BYKODE, new ProdukParameterizedRowMapper(), new Object[]{
            kodeProduk,idUmkm,namaProduk
        });
        return produk;
    }
    
    public Produk getProdukByKodeDanIdUMKM(String kodeProduk, Integer idUmkm) {
         try{
            Produk produk = jdbcTemplate.queryForObject(SQL_GETPRODUK_BYKODE_DANIDUMKM, new ProdukParameterizedRowMapper(), new Object[]{
                kodeProduk,
                idUmkm
            });
            
            return produk;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }

    public Produk getProdukByKodeIdUmkmDanBukanIdProduk(String kodeProduk, Integer idUmkm, Integer id) {
        try{
            Produk produk = jdbcTemplate.queryForObject(SQL_GETPRODUK_BYKODEIDUMKM_DAN_ID, new ProdukParameterizedRowMapper(), new Object[]{
                kodeProduk,
                idUmkm,
                id
            });
            
            return  produk;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }
    
    public Produk getProdukTerakhirByIdUmkmDanKategoriProduk(Integer idUmkm, Integer idKategoriProduk){
        try{
            Produk produk = jdbcTemplate.queryForObject(SQL_GETPRODUKTERAKHIR_BYIDUMKMDAN_IDKATEGORIPRODUK, new ProdukParameterizedRowMapper(), new Object[]{
                idUmkm,
                idKategoriProduk
            });
            
            return  produk;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }

    public void deleteProduk(Integer id) {
        jdbcTemplate.update(SQL_DELETE_PRODUK,id);
    }
    
    public void deleteFotoById(Integer id) {
        jdbcTemplate.update(SQL_DELETE_FOTOBYID,id);
    }

    public void deleteDistribusiFotoByIdFoto(Integer idFoto) {
        jdbcTemplate.update(SQL_DELETE_DISTRIBUSIFOTOBYIDFOTO,idFoto);
    }

    public void deleteDistribusiFotoByIdProduk(Integer idProduk) {
        jdbcTemplate.update(SQL_DELETE_DISTRIBUSIFOTOBYIDPRODUK,idProduk);
    }
    
}