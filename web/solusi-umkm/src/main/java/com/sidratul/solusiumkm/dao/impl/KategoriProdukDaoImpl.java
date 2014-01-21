package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.model.KategoriProduk;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("kategoriProdukDao")
public class KategoriProdukDaoImpl implements KategoriProdukDao{
    private static final String SQL_GETALL_KATEGORI_PRODUK = "SELECT * FROM `kategori_produk`";
    private static final String SQL_GETKATEGORI_PRODUK_BYID = "SELECT * FROM `kategori_produk` where id=?";
    private static final String SQL_GETKATEGORI_PRODUK_BYJENIS_PRODUK = "SELECT * FROM `kategori_produk` where jenis_produk=?";
    private static final String SQL_GETKATEGORI_PRODUK_BYJENIS_PRODUK_EDIT = "SELECT * FROM `kategori_produk` where jenis_produk=? and id != ?";
    private static final String SQL_DELETE_KATEGORI_PRODUK_BYID = "DELETE FROM `kategori_produk` where id=?";
    private static final String SQL_UPDATE_KATEGORI_PRODUK = "UPDATE `kategori_produk` SET `jenis_produk` = ? "
            + "WHERE id = ?";
    private static final String SQL_INSERT_KATEGORI_PRODUK = "INSERT INTO `kategori_produk`(`jenis_produk`)VALUES(?)";
    
    private JdbcTemplate jdbcTemplate;
    
    private static final class KategoriProdukParameterizedRowMapper implements 
            ParameterizedRowMapper<KategoriProduk>{

        public KategoriProduk mapRow(ResultSet rs, int i) throws SQLException {
            KategoriProduk kategoriProduk= new KategoriProduk();
            kategoriProduk.setId(rs.getInt("id"));
            kategoriProduk.setJenisProduk(rs.getString("jenis_produk"));
            
            return kategoriProduk;
        }
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<KategoriProduk> getAllKategoriProduk() {
        List<KategoriProduk> kategoriProduks = jdbcTemplate.query(SQL_GETALL_KATEGORI_PRODUK, new KategoriProdukParameterizedRowMapper());
        return kategoriProduks;
    }

    public void saveKategoriProduk(KategoriProduk kategoriProduk) {
        if(kategoriProduk.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_KATEGORI_PRODUK, new Object[]{
                kategoriProduk.getJenisProduk(),
                kategoriProduk.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_KATEGORI_PRODUK, kategoriProduk.getJenisProduk());
        }
    }

    public KategoriProduk getKategoriProdukById(Integer id) {
        if(id==null){
            return null;
        }else{
            KategoriProduk kategoriProduk= jdbcTemplate.queryForObject(SQL_GETKATEGORI_PRODUK_BYID,new KategoriProdukParameterizedRowMapper(),id);
            return kategoriProduk;
        }
    }
    
    public KategoriProduk getKategoriProdukByJenisKategoriProduk(String jenisKategoriProduk) {
        try{
            KategoriProduk kategoriProduk= jdbcTemplate.queryForObject(SQL_GETKATEGORI_PRODUK_BYJENIS_PRODUK,new KategoriProdukParameterizedRowMapper(),jenisKategoriProduk);
            return kategoriProduk;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }
    
    public KategoriProduk getKategoriProdukByJenisKategoriProdukEdit(String jenisKategoriProduk, Integer id) {
        try{
            KategoriProduk kategoriProduk= jdbcTemplate.queryForObject(SQL_GETKATEGORI_PRODUK_BYJENIS_PRODUK_EDIT,new KategoriProdukParameterizedRowMapper(),jenisKategoriProduk, id);
            return kategoriProduk;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }
    
    public void deleteKategoriProduk(Integer id) {
        jdbcTemplate.update(SQL_DELETE_KATEGORI_PRODUK_BYID,id);
    }
    
}
