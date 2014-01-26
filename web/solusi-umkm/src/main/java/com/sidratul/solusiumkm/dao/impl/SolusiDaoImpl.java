/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.KasusDao;
import com.sidratul.solusiumkm.dao.SolusiDao;
import com.sidratul.solusiumkm.model.Kasus;
import com.sidratul.solusiumkm.model.Solusi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("solusiDao")
public class SolusiDaoImpl implements SolusiDao{
    
    @Autowired private KasusDao kasusDao;
    
    private static final String SQL_GETALL_SOLUSI ="SELECT * FROM solusi";
    private static final String SQL_GET_SOLUSIBYID ="SELECT * FROM solusi WHERE id_kasus=?";
    
    private JdbcTemplate jdbcTemplate;

    public final class SolusiParameterizeRowMapper implements 
            ParameterizedRowMapper<Solusi>{

        public Solusi mapRow(ResultSet rs, int i) throws SQLException {
            Solusi solusi = new Solusi();
            solusi.setId(rs.getInt("id"));
            solusi.setIsiSolusi(rs.getString("isi_solusi"));
            solusi.setKasus(kasusDao.getKasusById(rs.getInt("id_kasus")));
            
            return solusi;
        }
        
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Solusi> getAllSolusi() {
        List<Solusi> solusis = jdbcTemplate.query(SQL_GETALL_SOLUSI, new SolusiParameterizeRowMapper());
        return solusis ;
    }

    public Solusi getSolusiByIdKasus(Integer idKasus) {
         if(idKasus==null){
            return null;
        }else{
            Solusi solusi = jdbcTemplate.queryForObject(SQL_GET_SOLUSIBYID, new SolusiParameterizeRowMapper(), idKasus);
            return solusi;
        }
    }
    
    
    
}
