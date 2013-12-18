//package com.sidratul.solusiumkm.dao.impl;
//
//import com.sidratul.solusiumkm.dao.PermasalahanDao;
//import com.sidratul.solusiumkm.model.Permasalahan;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
//
//public class PermasalahanDaoImpl implements PermasalahanDao{
//    
//    private static final String SQL_GETALL_PERMASALAHAN ="SELECT * FROM `PERMASALAHAN_UMKM`";
//    private static final String SQL_GETPERMASALAHAN_BYID ="SELECT * FROM `PERMASALAHAN`";
//    private static final String SQL_DELETE_PERMASALAHAN ="DELETE FROM `PERMASALAHAN` WHERE ID_PERMASALAHAN";
//    private static final String SQL_UPDATE_PERMASALAHAN_BYID ="UPDATE `PERMASALAHAN` SET `PERMASALAHAN` = ? WHERE `ID_PERMASALAHAN` = ?";
//    private static final String SQL_ISNERT_PERMASALAHAN ="INSERT INTO `PERMASALAHAN`(`PERMASALAHAN`)VALUES(?)";
//    
//    private JdbcTemplate jdbcTemplate;
//    
//    public static final class PermasalahanParameterizeRowMapper implements 
//            ParameterizedRowMapper<Permasalahan>{
//
//        public Permasalahan mapRow(ResultSet rs, int i) throws SQLException {
//            Permasalahan permasalahan = new Permasalahan();
//            permasalahan.setIdPermasalahan(rs.getInt("ID_PERMASALAHAN"));
//            permasalahan.setIsiPermasalahan(rs.getString("Permasalahan"));
//            
//            return permasalahan;
//        }
//        
//    }
//    
//    @Autowired
//    public void setDataSource(DataSource dataSource){
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//    
//    public List<Permasalahan> getAllPermasalahan(Permasalahan permasalahan) {
//        List<Permasalahan> permasalahans = jdbcTemplate.query(SQL_GETALL_PERMASALAHAN, new PermasalahanParameterizeRowMapper());
//        return permasalahans;
//    }
//
//    public void savePermasalahan(Permasalahan permasalahan) {
//        if(permasalahan.getIdPermasalahan()!=null){
//            jdbcTemplate.update(SQL_UPDATE_PERMASALAHAN_BYID,permasalahan.getIsiPermasalahan(),permasalahan.getIdPermasalahan());
//        }else{
//            jdbcTemplate.update(SQL_ISNERT_PERMASALAHAN,permasalahan.getIsiPermasalahan());
//        }
//    }
//
//    public Permasalahan getPermasalahanById(Integer id) {
//        if(id==null){
//            return null;
//        }else{
//            Permasalahan permasalahan = jdbcTemplate.queryForObject(SQL_GETPERMASALAHAN_BYID, new PermasalahanParameterizeRowMapper(), id);
//            return permasalahan;
//        }
//    }
//
//    public void deletePermasalahan(Integer id) {
//        jdbcTemplate.update(SQL_DELETE_PERMASALAHAN,id);
//    }
//    
//}
