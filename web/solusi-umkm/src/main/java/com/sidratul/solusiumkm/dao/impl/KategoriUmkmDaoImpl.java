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
    
    private static final String SQL_GETALL_KATEGORI_UMKM = "SELECT * FROM `kategori_umkm`";
    private static final String SQL_GETKATEGORI_UMKM_BYID = "SELECT * FROM `kategori_umkm` where id=?";
    private static final String SQL_DELETE_KATEGORI_UMKM_BYID = "DELETE FROM `kategori_umkm` where id=?";
    private static final String SQL_UPDATE_KATEGORI_UMKM = "UPDATE `KATEGORI_UMKM` SET `jenis_umkm` = ? WHERE "
            + "id = ?";
    private static final String SQL_INSERT_KATEGORI_UMKM = "INSERT INTO `kategori_umkm`(`jenis_umkm`)VALUES(?);";
    
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
        List<KategoriUmkm> kategoriUmkms = jdbcTemplate.query(SQL_GETALL_KATEGORI_UMKM, new KategoriUmkmParameterizedRowMapper());
        return kategoriUmkms;
    }

    public void saveKategoriUmkm(KategoriUmkm kategoriUmkm) {
        if(kategoriUmkm.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_KATEGORI_UMKM, new Object[]{
                kategoriUmkm.getJenisUmkm(),
                kategoriUmkm.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_KATEGORI_UMKM, kategoriUmkm.getJenisUmkm());
        }
    }

    public KategoriUmkm getKategoriUmkmById(Integer id) {
        if(id==null){
            return null;
        }else{
            KategoriUmkm kategoriUmkm= jdbcTemplate.queryForObject(SQL_GETKATEGORI_UMKM_BYID,new KategoriUmkmParameterizedRowMapper(),id);
            return kategoriUmkm;
        }
    }

    public void deleteKategoriUmkm(Integer id){
        jdbcTemplate.update(SQL_DELETE_KATEGORI_UMKM_BYID,id);
    }
}
