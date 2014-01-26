package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.PermasalahanDao;
import com.sidratul.solusiumkm.model.Permasalahan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("permasalahanDao")
public class PermasalahanDaoImpl implements PermasalahanDao{
    
    private static final String SQL_GETALL_PERMASALAHAN ="SELECT * FROM permasalahan";
    private static final String SQL_GETPERMASALAHAN_BYID ="SELECT * FROM permasalahan WHERE id=?";
    private static final String SQL_GETPERMASALAHAN_BYIDKASUS ="SELECT p*,dk.bobot FROM permasalahan p, detail_kasus dk WHERE"
            + " dk.id_kasus=? and dk.id_permasalahan = p.id";
//    private static final String SQL_DELETE_PERMASALAHAN ="DELETE FROM `PERMASALAHAN` WHERE ID_PERMASALAHAN";
//    private static final String SQL_UPDATE_PERMASALAHAN_BYID ="UPDATE `PERMASALAHAN` SET `PERMASALAHAN` = ? WHERE `ID_PERMASALAHAN` = ?";
//    private static final String SQL_ISNERT_PERMASALAHAN ="INSERT INTO `PERMASALAHAN`(`PERMASALAHAN`)VALUES(?)";
//    
    private JdbcTemplate jdbcTemplate;

    
    public static final class PermasalahanParameterizeRowMapper implements 
            ParameterizedRowMapper<Permasalahan>{

        public Permasalahan mapRow(ResultSet rs, int i) throws SQLException {
            Permasalahan permasalahan = new Permasalahan();
            permasalahan.setId(rs.getInt("id"));
            permasalahan.setIsiPermasalahan(rs.getString("isi"));
            
            return permasalahan;
        }
        
    }
    
    public static final class PermasalahanBobotParameterizeRowMapper implements 
            ParameterizedRowMapper<Permasalahan>{

        public Permasalahan mapRow(ResultSet rs, int i) throws SQLException {
            Permasalahan permasalahan = new Permasalahan();
            permasalahan.setId(rs.getInt("id"));
            permasalahan.setIsiPermasalahan(rs.getString("isi"));
            permasalahan.setBobot(rs.getInt("bobot"));
            
            return permasalahan;
        }
        
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    
    public List<Permasalahan> getAllPermasalahan() {
        List<Permasalahan> permasalahans = jdbcTemplate.query(SQL_GETALL_PERMASALAHAN, new PermasalahanParameterizeRowMapper());
        return permasalahans;
    }

    public List<Permasalahan> getAllPermasalahanByIdKasus(Integer idKasus) {
        List<Permasalahan> permasalahans = jdbcTemplate.query(SQL_GETPERMASALAHAN_BYIDKASUS, new PermasalahanBobotParameterizeRowMapper());
        return permasalahans;
    }

    public Permasalahan getPermasalahanById(Integer id) {
        if(id==null){
            return null;
        }else{
            Permasalahan permasalahan = jdbcTemplate.queryForObject(SQL_GETPERMASALAHAN_BYID, new PermasalahanParameterizeRowMapper(), id);
            return permasalahan;
        }
    }
    
}
