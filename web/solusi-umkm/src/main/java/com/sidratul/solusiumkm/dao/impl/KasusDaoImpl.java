package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.KasusDao;
import com.sidratul.solusiumkm.dao.PermasalahanDao;
import com.sidratul.solusiumkm.model.Kasus;
import com.sidratul.solusiumkm.model.Permasalahan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("kasusDao")
public class KasusDaoImpl implements KasusDao{
    private static final String SQL_GETALL_KASUS ="SELECT * FROM kasus";
    private static final String SQL_GET_KASUSBYID ="SELECT * FROM kasus WHERE id=?";
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired private PermasalahanDao permasalahanDao;

    public final class KasusParameterizeRowMapper implements 
            ParameterizedRowMapper<Kasus>{

        public Kasus mapRow(ResultSet rs, int i) throws SQLException {
            Kasus kasus = new Kasus();
            kasus.setId(rs.getInt("id"));
            kasus.setIsiKasus(rs.getString("isi_kasus"));
            kasus.setPermasalahans(permasalahanDao.getAllPermasalahanByIdKasus(rs.getInt("id")));
            
            return kasus;
        }
        
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Kasus> getAllKasus() {
        List<Kasus> kasuses = jdbcTemplate.query(SQL_GETALL_KASUS, new KasusParameterizeRowMapper());
        return kasuses;
    }

    public Kasus getKasusById(Integer id) {
        if(id==null){
            return null;
        }else{
            Kasus kasus = jdbcTemplate.queryForObject(SQL_GET_KASUSBYID, new KasusParameterizeRowMapper(), id);
            return kasus;
        }
    }
    
    
}
