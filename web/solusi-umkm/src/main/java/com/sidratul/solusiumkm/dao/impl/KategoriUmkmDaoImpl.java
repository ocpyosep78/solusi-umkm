package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.KategoriUmkmDao;
import com.sidratul.solusiumkm.model.KategoriUmkm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("kategoriUmkmDao")
public class KategoriUmkmDaoImpl implements KategoriUmkmDao{
    
    private static final String SQL_GETALL_KATEGORI = "SELECT * FROM `kategori_umkm`";
    private static final String SQL_GETKATEGORI_BYID = "SELECT * FROM `kategori_umkm` where id=?";
    private static final String SQL_DELETE_KATEGORI_BYID = "DELETE FROM `kategori_umkm` where ID=?";
    private static final String SQL_UPDATE_KATEGORI = "UPDATE `KATEGORI_UMKM` SET `KATEGORI_UMKM` = ? WHERE "
            + "ID_KATEGORI_UMKM = ?";
    private static final String SQL_INSERT_KATEGORI = "INSERT INTO `KATEGORI_UMKM`(`KATEGORI_UMKM`)VALUES(?);";
    
    private JdbcTemplate jdbcTemplate;
    
    private static final class KategoriUmkmParameterizedRowMapper implements 
            ParameterizedRowMapper<KategoriUmkm>{

        public KategoriUmkm mapRow(ResultSet rs, int i) throws SQLException {
            KategoriUmkm kategoriUmkm= new KategoriUmkm();
            kategoriUmkm.setId(rs.getInt("id"));
            kategoriUmkm.setJenisUmkm(rs.getString("jenis_umkm"));
            
            return kategoriUmkm;
        }
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<KategoriUmkm> getAllKategoriUmkm() {
        List<KategoriUmkm> kategoriUmkms = jdbcTemplate.query(SQL_GETALL_KATEGORI, new KategoriUmkmParameterizedRowMapper());
        return kategoriUmkms;
    }

    public void saveKategoriUmkm(KategoriUmkm kategoriUmkm) {
        if(kategoriUmkm.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_KATEGORI, new Object[]{
                kategoriUmkm.getJenisUmkm(),
                kategoriUmkm.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_KATEGORI, kategoriUmkm.getJenisUmkm());
        }
    }

    public KategoriUmkm getKategoriUmkmById(Integer id) {
        if(id==null){
            return null;
        }else{
            KategoriUmkm kategoriUmkm= jdbcTemplate.queryForObject(SQL_GETKATEGORI_BYID,new KategoriUmkmParameterizedRowMapper(),id);
            return kategoriUmkm;
        }
    }

    public void deleteKategoriUmkm(Integer id){
        jdbcTemplate.update(SQL_DELETE_KATEGORI_BYID,id);
    }
}
