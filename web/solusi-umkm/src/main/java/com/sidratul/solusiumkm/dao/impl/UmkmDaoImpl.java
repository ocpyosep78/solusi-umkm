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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("umkmDao")
public class UmkmDaoImpl implements UmkmDao{
    
    private static final String SQL_GETALL_UMKM="SELECT * FROM umkm";
    private static final String SQL_GETALL_UMKM_BELUM_ADAUSER="SELECT um.* FROM umkm um LEFT JOIN user us "
            + "ON um.id = us.id_umkm "
            + "WHERE us.id_umkm is null";
    private static final String SQL_GETUMKM_BYID="SELECT * FROM umkm where id=?";
    private static final String SQL_GETUMKM_BYKODEUMKM="SELECT * FROM umkm where kode_umkm=?";
    private static final String SQL_GETUMKM_BYKODEUMKM_BUKANID="SELECT * FROM umkm where kode_umkm=? AND id != ?";
    
    private static final String SQL_GETUMKM_BYUSERNAME="SELECT um.* FROM umkm um "
            + "RIGHT JOIN user us ON um.id = us.id_umkm "
            + "where um.username=?";
    private static final String SQL_GETUMKM_BYUSERNAME_BUKANID="select * from umkm where username = ? and id != ? ";
    
    private static final String SQL_DELETE_UMKM="DELETE FROM umkm where id=?";
    private static final String SQL_UPDATE_UMKM="UPDATE `umkm` SET "
            + "`kode_umkm` = ?,`nama_umkm` = ?,`pemilik_umkm` = ?,username=?, password=?,`id_kategori_umkm` = ?,"
            + "`keterangan_umkm` = ?,`visi` = ?,`misi` = ?,`alamat` = ?,`no_telpon` = ?,`email` = ? "
            + "WHERE `id` = ?";
    private static final String SQL_INSERT_UMKM="INSERT INTO `umkm`"
            + "(`kode_umkm`,`nama_umkm`,`pemilik_umkm`,username, password,"
            + "`id_kategori_umkm`,`keterangan_umkm`,`visi`,`misi`,`alamat`,`no_telpon`,`email`)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    
    @Autowired private KategoriUmkmDao kategoriUmkmDao;
    
    private JdbcTemplate jdbcTemplate;

    
    private final class UmkmParameterizedRowMapper implements 
            ParameterizedRowMapper<Umkm>{

        public Umkm mapRow(ResultSet rs, int i) throws SQLException {
            Umkm umkm = new Umkm();
            umkm.setId(rs.getInt("id"));
            umkm.setKodeUmkm(rs.getString("kode_umkm"));
            umkm.setNamaUmkm(rs.getString("nama_umkm"));
            umkm.setPemilikUmkm(rs.getString("pemilik_umkm"));
            umkm.setUsername(rs.getString("username"));
            umkm.setPassword(rs.getString("password"));
            
            
            umkm.setKategoriUmkm(kategoriUmkmDao.getKategoriUmkmById(rs.getInt("id_kategori_umkm")));
            
            umkm.setKeteranganUmkm(rs.getString("keterangan_umkm"));
            umkm.setVisi(rs.getString("visi"));
            umkm.setMisi(rs.getString("misi"));
            umkm.setAlamat(rs.getString("alamat"));
            umkm.setNoTelp(rs.getString("no_telpon"));
            umkm.setEmail(rs.getString("email"));
            
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
    
    public List<Umkm> getAllUmkmTidakMemilikiUser() {
        List<Umkm> umkms = jdbcTemplate.query(SQL_GETALL_UMKM_BELUM_ADAUSER, new UmkmParameterizedRowMapper());
        return umkms;
    }
    
    public Umkm getUmkmByKodeUmkm(String kodeUmkm)  {
        try{
            Umkm umkm = jdbcTemplate.queryForObject(SQL_GETUMKM_BYKODEUMKM, new UmkmParameterizedRowMapper(),kodeUmkm);
            return umkm;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
        
    }
    
    public Umkm getUmkmByKodeUmkmDanBukanId(String kodeUmkm, Integer id) {
        try{
            Umkm umkm = jdbcTemplate.queryForObject(SQL_GETUMKM_BYKODEUMKM_BUKANID, new UmkmParameterizedRowMapper(),kodeUmkm,id);
            return umkm;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }

    public Umkm getUmkmByUsernameDanBukanId(String username, Integer id) {
        try{
            Umkm umkm = jdbcTemplate.queryForObject(SQL_GETUMKM_BYUSERNAME_BUKANID, new UmkmParameterizedRowMapper(),username,id);
            return umkm;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }
    
    public void saveUmkm(Umkm umkm) throws DuplicateKeyException {
        if(umkm.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_UMKM, new Object[]{
                umkm.getKodeUmkm(),
                umkm.getNamaUmkm(),
                umkm.getPemilikUmkm(),
                umkm.getUsername(),
                umkm.getPassword(),
                umkm.getKategoriUmkm().getId(),
                umkm.getKeteranganUmkm(),
                umkm.getVisi(),
                umkm.getMisi(),
                umkm.getAlamat(),
                umkm.getNoTelp(),
                umkm.getEmail(),
                umkm.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_UMKM, new Object[]{
                umkm.getKodeUmkm(),
                umkm.getNamaUmkm(),
                umkm.getPemilikUmkm(),
                umkm.getUsername(),
                umkm.getPassword(),
                umkm.getKategoriUmkm().getId(),
                umkm.getKeteranganUmkm(),
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
            Umkm umkm = jdbcTemplate.queryForObject(SQL_GETUMKM_BYID, new UmkmParameterizedRowMapper(),id);
            return umkm;
        }
    }

    public Umkm getUmkmByUsername(String username) {
        try{
            Umkm umkm = jdbcTemplate.queryForObject(SQL_GETUMKM_BYUSERNAME, new UmkmParameterizedRowMapper(),username);
            return umkm;
        }catch(EmptyResultDataAccessException erdae ){
            return null;
        }
    }
    
    public void deleteUmkm(Integer id) {
        jdbcTemplate.update(SQL_DELETE_UMKM,id);
    }
}
