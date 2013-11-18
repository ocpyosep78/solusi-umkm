package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.KategoriUmkmDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Umkm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("umkmDao")
public class UmkmDaoImpl implements UmkmDao{
    
    private static final String SQL_GETALL_UMKM="SELECT * FROM UMKM";
    private static final String SQL_GETUMKM_BYIDUMKM="SELECT * FROM UMKM where ID_UMKM=?";
    private static final String SQL_DELETE_UMKM="DELETE FROM UMKM where ID_UMKM=?";
    private static final String SQL_UPDATE_UMKM="UPDATE `solusi-umkm`.`UMKM` SET "
            + "`UMKM_ID` = ?, `NAMA_UMKM` = ?, `PEMILIK_UMKM` = ?,`DETAIL_PRODUK_UMKM` = ?,`ID_KATEGORI_UMKM` = ?,`VISI` = ?,`MISI` = ?,`ALAMAT` = ?,`NO_TELP` = ?,`EMAIL` = ? "
            + "WHERE `ID_UMKM` = ?;";
    private static final String SQL_INSERT_UMKM="INSERT INTO `UMKM`(`UMKM_ID`,`NAMA_UMKM`,`PEMILIK_UMKM`,`DETAIL_PRODUK_UMKM`,`ID_KATEGORI_UMKM`,`VISI`,`MISI`,`ALAMAT`,`NO_TELP`,`EMAIL`)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?);";
    
    @Autowired private KategoriUmkmDao kategoriUmkmDao;
    
    private JdbcTemplate jdbcTemplate;
    
    private final class UmkmParameterizedRowMapper implements 
            ParameterizedRowMapper<Umkm>{

        public Umkm mapRow(ResultSet rs, int i) throws SQLException {
            Umkm umkm = new Umkm();
            umkm.setIdUmkm(rs.getInt("ID_UMKM"));
            umkm.setUmkmId(rs.getString("UMKM_ID"));
            umkm.setNamaUmkm(rs.getString("NAMA_UMKM"));
            umkm.setPemilikUmkm(rs.getString("PEMILIK_UMKM"));
            umkm.setDetailProduk("DETAIL_PRODUK_UMKM");
            umkm.setKategoriUmkm(kategoriUmkmDao.getKategoriUmkmById(rs.getInt("ID_KATEGORI_UMKM")));
            umkm.setVisi(rs.getString("VISI"));
            umkm.setMisi(rs.getString("MISI"));
            umkm.setAlamat(rs.getString("Alamat"));
            umkm.setNoTelp(rs.getString("NO_TELP"));
            umkm.setEmail(rs.getString("EMAIL"));
            
            return umkm;
        }
        
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Umkm> getAllUmkm() {
        List<Umkm> umkms = jdbcTemplate.query(SQL_GETALL_UMKM, new UmkmParameterizedRowMapper());
        return umkms;
    }
    
    public void saveUmkm(Umkm umkm) throws DuplicateKeyException {
        if(umkm.getIdUmkm()!=null){
            jdbcTemplate.update(SQL_UPDATE_UMKM, new Object[]{
                umkm.getUmkmId(),
                umkm.getNamaUmkm(),
                umkm.getPemilikUmkm(),
                umkm.getDetailProduk(),
                umkm.getKategoriUmkm().getIdKategoriUMKM(),
                umkm.getVisi(),
                umkm.getMisi(),
                umkm.getAlamat(),
                umkm.getNoTelp(),
                umkm.getEmail(),
                umkm.getIdUmkm()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_UMKM, new Object[]{
                umkm.getUmkmId(),
                umkm.getNamaUmkm(),
                umkm.getPemilikUmkm(),
                umkm.getDetailProduk(),
                umkm.getKategoriUmkm().getIdKategoriUMKM(),
                umkm.getVisi(),
                umkm.getMisi(),
                umkm.getAlamat(),
                umkm.getNoTelp(),
                umkm.getEmail()
            });
        }
    }

    public Umkm getUmkmById(Integer id) {
        if(id==null){
            return null;
        }else{
            Umkm umkm = jdbcTemplate.queryForObject(SQL_GETUMKM_BYIDUMKM, new UmkmParameterizedRowMapper(),id);
            return umkm;
        }
    }

    public void deleteUmkm(Integer id) {
        jdbcTemplate.update(SQL_DELETE_UMKM);
    }
}
