//package com.sidratul.solusiumkm.dao.impl;
//
//import com.sidratul.solusiumkm.dao.DetailPermasalahanDao;
//import com.sidratul.solusiumkm.dao.PermasalahanDao;
//import com.sidratul.solusiumkm.model.DetailPermasalahan;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
//import org.springframework.stereotype.Repository;
//
//@Repository("detailPermasalahanDao")
//public class DetailPermasalahanDaoImpl implements DetailPermasalahanDao{
//    
//    private static final String SQL_GETALL_DETAILPERMASALAHAN="SELECT * FROM DETAIL_PERMASALAHAN";
//    
//    private static final String SQL_GEDETAILPERMASALAHAN_BYID="SELECT * FROM DETAIL_PERMASALAHAN WHERE ID_DETAIL_PERMASALAHAN=?";
//    private static final String SQL_DELETE_GEDETAILPERMASALAHAN="DELETE FROM DETAIL_PERMASALAHAN WHERE ID_DETAIL_PERMASALAHAN=?";
//    private static final String SQL_UPDATE_GEDETAILPERMASALAHAN="UPDATE `DETAIL_PERMASALAHAN` SET "
//            + "`ID_PERMASALAHAN` = ? WHERE `ID_DETAIL_PERMASALAHAN` = ?";
//    private static final String SQL_INSERT_GEDETAILPERMASALAHAN="INSERT INTO `DETAIL_PERMASALAHAN` (`ID_PERMASALAHAN`)VALUES(?)";
//    
//    @Autowired PermasalahanDao permasalahanDao;
//    private JdbcTemplate jdbcTemplate;
//    
//    private final class DetailPermasalahanParameterizedRowMapper implements 
//            ParameterizedRowMapper<DetailPermasalahan>{
//
//        public DetailPermasalahan mapRow(ResultSet rs, int i) throws SQLException {
//            DetailPermasalahan detailPermasalahan= new DetailPermasalahan();
//            detailPermasalahan.setIdDetailPermasalahan(rs.getInt("ID_DETAIL_PERMASALAHAN"));
//            detailPermasalahan.setPermasalahan(null);
//            
//            return detailPermasalahan;
//        }
//    
//    }
//    
//
//    public List<DetailPermasalahan> getAllDetailPermasalahan() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void saveDetailPermasalahan(DetailPermasalahan detailPermasalahan) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public DetailPermasalahan getDetailPermasalahanById(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void deleteDetailPermasalahan(Integer id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
