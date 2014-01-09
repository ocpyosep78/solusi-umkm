package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.dao.UserUmkmDao;
import com.sidratul.solusiumkm.model.Umkm;
import com.sidratul.solusiumkm.model.UserUmkm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("userUmkmDao")
public class UserDaoImpl implements UserUmkmDao{
    
    private static final String SQL_GETALL_USERUMKM_BYAKTIF="SELECT * FROM user where aktif=? and peran='ROLE_UMKM'";
    private static final String SQL_GETUSERUMKM_BYID="SELECT * FROM user where id=?";
    private static final String SQL_DELETEUSERUMKM_BYID="DELETE FROM user where id=?";
    private static final String SQL_UPDATE_USERUMKM="UPDATE `user` SET "
            + "`username` = ?, `id_umkm` = ?, `password` = ?, `terakhir_login` = ?, `aktif` = ? "
            + "WHERE `id` = ?";
    
    private static final String SQL_INSERT_USERUMKM="INSERT INTO `user`(`username`,`id_umkm`,`password`,`peran`,`aktif`)VALUES(?,?,?,?,?)";
    
    private JdbcTemplate jdbcTemplate;
    @Autowired private UmkmDao umkmDao;

    private final class UserUmkmParameterizedRowMapper implements ParameterizedRowMapper<UserUmkm>{

        public UserUmkm mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserUmkm userUmkm = new UserUmkm();
            userUmkm.setId(rs.getInt("id"));
            userUmkm.setUsername(rs.getString("username"));
            userUmkm.setPassword(rs.getString("password"));
            userUmkm.setPeran(rs.getString("peran"));
            userUmkm.setTerakhirLogin(rs.getDate("terakhir_login"));
            userUmkm.setUmkm(umkmDao.getUmkmById(rs.getInt("id_umkm")));
            userUmkm.setAktif(rs.getInt("aktif"));
            
            return userUmkm;
        }
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    

    public List<UserUmkm> getAllUser(Integer aktif) {
        List<UserUmkm> userUmkms = new ArrayList<UserUmkm>();
        userUmkms = jdbcTemplate.query(SQL_GETALL_USERUMKM_BYAKTIF, new UserUmkmParameterizedRowMapper(),aktif);
        return  userUmkms;
    }

    public void saveUserUmkm(UserUmkm userUmkm) {
        if(userUmkm.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_USERUMKM, new Object[]{
                userUmkm.getUsername(),
                userUmkm.getUmkm().getId(),
                userUmkm.getPassword(),
                new java.sql.Date(userUmkm.getTerakhirLogin().getTime()),
                userUmkm.getAktif(),
                userUmkm.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_USERUMKM, new Object[]{
                userUmkm.getUsername(),
                userUmkm.getUmkm().getId(),
                userUmkm.getPassword(),
                userUmkm.getPeran(),
                userUmkm.getAktif()
            });
        }
    }

    public UserUmkm getUserById(Integer id) {
        if(id==null){
            return null;
        }else{
            UserUmkm userUmkm = jdbcTemplate.queryForObject(SQL_GETUSERUMKM_BYID, new UserUmkmParameterizedRowMapper(),id);
            return userUmkm;
        }
    }

    public void deleteUserUmkm(Integer id) {
        jdbcTemplate.update(SQL_DELETEUSERUMKM_BYID,id);
    }
    
}
