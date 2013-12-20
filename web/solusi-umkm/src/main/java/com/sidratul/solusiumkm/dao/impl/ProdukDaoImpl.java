package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.FotoDao;
import com.sidratul.solusiumkm.dao.KategoriProdukDao;
import com.sidratul.solusiumkm.dao.ProdukDao;
import com.sidratul.solusiumkm.dao.UmkmDao;
import com.sidratul.solusiumkm.model.KategoriProduk;
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

    private static final String SQL_GETALL_PRODUK="SELECT * FROM produk";
    private static final String SQL_GETPRODUK_BYID="SELECT * FROM produk WHERE id=?";
    private static final String SQL_DELETE_PRODUK="DELETE FROM produk WHERE id=?";
    private static final String SQL_UPDATE_PRODUK="UPDATE `produk` SET `id_umkm` = ?,`id_kategori_produk` = ?,"
            + "`kode_produk` = ?,`nama_produk` = ?,`harga` = ?,`keterangan_produk` = ?,`tgl_update_produk` = ? "
            + "WHERE `id` = ?";
    private static final String SQL_INSERT_PRODUK="INSERT INTO `produk`"
            + "(`id_umkm`,`id_kategori_produk`,`kode_produk`,`nama_produk`,`harga`,`keterangan_produk`,`tgl_update_produk`)"
            + "VALUES(?,?,?,?,?,?,?)";
    
    private static final String SQL_DELETE_FOTOBYID="DELETE FROM foto WHERE id=?";
    private static final String SQL_DELETE_DISTRIBUSIFOTOBYIDPRODUK="DELETE FROM distribusi_foto WHERE id_produk=?";
    private static final String SQL_DELETE_DISTRIBUSIFOTOBYIDFOTO="DELETE FROM distribusi_foto WHERE id_foto=?";

    @Autowired private UmkmDao umkmDao;
    @Autowired private KategoriProdukDao kategoriProdukDao;
    @Autowired private FotoDao fotoDao;
    
    private JdbcTemplate jdbcTemplate;
    
    public final class ProdukParameterizedRowMapper implements ParameterizedRowMapper<Produk>{

        public Produk mapRow(ResultSet rs, int i) throws SQLException {
            Produk produk = new Produk();
            produk.setId(rs.getInt("id"));
            produk.setUmkm(umkmDao.getUmkmById(rs.getInt("id_umkm")));
            produk.setKategoriProduk(kategoriProdukDao.getKategoriProdukById(rs.getInt("id_kategori_produk")));
            produk.setKodeProduk(rs.getString("kode_produk"));
            produk.setNamaProduk(rs.getString("nama_produk"));
            produk.setHarga(rs.getBigDecimal("harga"));
            produk.setKeteranganProduk(rs.getString("keterangan_produk"));
            produk.setTglUpdateProduk(rs.getDate("tgl_update_produk"));
            produk.setFotos(fotoDao.getAllFotoByIdProduk(rs.getInt("id")));
            
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
        if(produk.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_PRODUK, new Object[]{
                produk.getUmkm().getId(),
                produk.getKategoriProduk().getId(),
                produk.getKodeProduk(),
                produk.getNamaProduk(),
                produk.getHarga(),
                produk.getKeteranganProduk(),
                produk.getTglUpdateProduk(),
                produk.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_PRODUK, new Object[]{
                produk.getUmkm().getId(),
                produk.getKategoriProduk().getId(),
                produk.getKodeProduk(),
                produk.getNamaProduk(),
                produk.getHarga(),
                produk.getKeteranganProduk(),
                new java.sql.Date(produk.getTglUpdateProduk().getTime())
            });
        }
    }

    public Produk getProdukById(Integer id) {
        if(id==null){
            return null;
        }else{
            Produk produk = jdbcTemplate.queryForObject(SQL_GETPRODUK_BYID, new ProdukParameterizedRowMapper(), id);
            return produk;
        }
    }

    public void deleteProduk(Integer id) {
        jdbcTemplate.update(SQL_DELETE_PRODUK,id);
    }
    
    public void deleteFotoById(Integer id) {
        jdbcTemplate.update(SQL_DELETE_FOTOBYID,id);
    }

    public void deleteDistribusiFotoByIdFoto(Integer idFoto) {
        jdbcTemplate.update(SQL_DELETE_DISTRIBUSIFOTOBYIDFOTO,idFoto);
    }

    public void deleteDistribusiFotoByIdProduk(Integer idProduk) {
        jdbcTemplate.update(SQL_DELETE_DISTRIBUSIFOTOBYIDPRODUK,idProduk);
    }
    
}