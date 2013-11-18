package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.Produk;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("ProdukDao")
public class ProdukDaoImpl implements ProdukDao{

    private static final String SQL_GETALL_PRODUK="SELECT * FROM `PRODUK`";
    private static final String SQL_GETPRODUK_BYID="SELECT * FROM `PRODUK` WHERE ID_PRODUK=?";
    private static final String SQL_DELETE_PRODUK="DELETE FROM `PRODUK` WHERE ID_PRODUK=?";
    private static final String SQL_UPDATE_PRODUK="UPDATE `PRODUK` SET `ID_UMKM` = ?, `KODE_PRODUK` = ?, `NAMA_PRODUK` = ?, `HARGA` = ?, `DESKRIPSI` = ? "
            + "WHERE `ID_PRODUK` = ?";
    private static final String SQL_INSERT_PRODUK="INSERT INTO `solusi-umkm`.`PRODUK`"
            + "(`ID_UMKM`,`KODE_PRODUK`,`NAMA_PRODUK`,`HARGA`,`DESKRIPSI`)VALUES(?,?,?,?,?);";

    @Autowired private UmkmDao umkmDao;
    
    private JdbcTemplate jdbcTemplate;
    
    public final class ProdukParameterizedRowMapper implements ParameterizedRowMapper<Produk>{

        public Produk mapRow(ResultSet rs, int i) throws SQLException {
            Produk produk = new Produk();
            produk.setIdProduk(rs.getInt("ID_PRODUK"));
            produk.setUmkm(umkmDao.getUmkmById(rs.getInt("ID_UMKM")));
            produk.setKodeProduk(rs.getString("KODE_PRODUK"));
            produk.setNamaProduk(rs.getString("NAMA_PRODUK"));
            produk.setHarga(rs.getBigDecimal("HARGA"));
            produk.setDeskripsi(rs.getString("DESKRIPSI"));
            
            return produk;
        }
    
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Produk> getAllProduk() {
        List<Produk> produks = jdbcTemplate.query(SQL_GETALL_PRODUK,new ProdukParameterizedRowMapper());
        return produks;
    }

    public void saveProduk(Produk produk) throws DuplicateKeyException {
        if(produk.getIdProduk()!=null){
            jdbcTemplate.update(SQL_UPDATE_PRODUK, new Object[]{
                produk.getUmkm().getIdUmkm(),
                produk.getKodeProduk(),
                produk.getNamaProduk(),
                produk.getHarga(),
                produk.getDeskripsi(),
                produk.getIdProduk()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_PRODUK, new Object[]{
                produk.getUmkm().getIdUmkm(),
                produk.getKodeProduk(),
                produk.getNamaProduk(),
                produk.getHarga(),
                produk.getDeskripsi()
            });
        }
    }

    public Produk getProdukById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteProduk(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
