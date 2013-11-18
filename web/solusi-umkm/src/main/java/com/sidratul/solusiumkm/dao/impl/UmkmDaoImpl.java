package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Umkm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class UmkmDaoImpl implements UmkmDao{
    
    private static final String SQL_GETALL_UMKM="SELECT * FROM UMKM";
    private static final String SQL_GETUMKM_BYIDUMKM="SELECT * FROM UMKM where ID_UMKM=?";
    private static final String SQL_DELETE_UMKM="DELETE FROM UMKM where ID_UMKM=?";
    private static final String SQL_UPDATE_UMKM="UPDATE `solusi-umkm`.`UMKM` SET "
            + "`UMKM_ID` = ?, `NAMA_UMKM` = ?, `PEMILIK_UMKM` = ?,`DETAIL_PRODUK_UMKM` = ?,`ID_KATEGORI_UMKM` = ?,`VISI` = ?,`MISI` = ?,`ALAMAT` = ?,`NO_TELP` = ?,`EMAIL` = ? "
            + "WHERE `ID_UMKM` = ?;";
    private static final String SQL_INSERT_UMKM="INSERT INTO `UMKM`(`UMKM_ID`,`NAMA_UMKM`,`PEMILIK_UMKM`,`DETAIL_PRODUK_UMKM`,`ID_KATEGORI_UMKM`,`VISI`,`MISI`,`ALAMAT`,`NO_TELP`,`EMAIL`)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?);";
    
    private JdbcTemplate jdbcTemplate;
    
    private static final class UmkmParameterizedRowMapper implements 
            ParameterizedRowMapper<Umkm>{

        public Umkm mapRow(ResultSet rs, int i) throws SQLException {
            Umkm umkm = new Umkm();
            
            return umkm;
        }
        
    }
    
    
    
    
    
    
    public List<Umkm> getAllUmkm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveUmkm(Umkm umkm) throws DuplicateKeyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Umkm getUmkmById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteUmkm(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
