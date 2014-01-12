package com.sidratul.solusiumkm.dao.impl;

import com.sidratul.solusiumkm.dao.FotoDao;
import com.sidratul.solusiumkm.model.Foto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;


@Repository("FotoDao")
public class FotoDaoImpl implements FotoDao{
    
    private static final String SQL_GETALLFOTO_BYIDPRODUK="select f.* from foto f, distribusi_foto d "
            + "where d.id_produk=? and d.id_foto = f.id";
    private static final String SQL_GETFOTO_BYID="SELECT * FROM foto WHERE id=?";
    private static final String SQL_GETFOTO_NAMAFILE="SELECT * FROM foto WHERE nama_file=?";
    private static final String SQL_DELETE_FOTO="DELETE FROM foto WHERE id=?";
    private static final String SQL_DELETE_FOTO_BYID_PRODUK="DELETE FROM distribusi_foto WHERE id_produk=?";
    private static final String SQL_UPDATE_FOTO="UPDATE `foto` SET "
            + "`nama_file` = ?, `tgl_upload` = ?, `keterangan_foto` = ? "
            + "WHERE `id` = ?";
    private static final String SQL_INSERT_FOTO="INSERT INTO `foto`(`nama_file`,`tgl_upload`,`keterangan_foto`)VALUES(?,?,?)";
    
    private static final String SQL_INSERT_DISTRIBUSIFOTO="INSERT INTO `distribusi_foto`(`id_produk`,`id_foto`)VALUES(?,?)";
    
    
    
    private JdbcTemplate jdbcTemplate;

    public final class FotoParameterizedRowMapper implements ParameterizedRowMapper<Foto>{

        public Foto mapRow(ResultSet rs, int i) throws SQLException {
            Foto foto = new Foto();
            
            foto.setId(rs.getInt("id"));
            foto.setNamaFile(rs.getString("nama_file"));
            foto.setKeteranganFoto(rs.getString("keterangan_foto"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            try{
                date = format.parse(rs.getString("tgl_upload"));
            }catch(Exception e){
                date = null;
            }
            foto.setTglUpload(date);
            return foto;
        }
    
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Foto> getAllFotoByIdProduk(Integer idProduk) {
        List<Foto> fotos = jdbcTemplate.query(SQL_GETALLFOTO_BYIDPRODUK, new FotoParameterizedRowMapper(),idProduk);
        return fotos;
    }

    public void saveFoto(Foto foto) {
        if(foto.getId()!=null){
            jdbcTemplate.update(SQL_UPDATE_FOTO, new Object[]{
                foto.getNamaFile(),
                foto.getTglUpload(),
                foto.getKeteranganFoto(),
                foto.getId()
            });
        }else{
            jdbcTemplate.update(SQL_INSERT_FOTO, new Object[]{
                foto.getNamaFile(),
                foto.getTglUpload(),
                foto.getKeteranganFoto()
            });
        }
    }

    public Foto getFotoByid(Integer id) {
        if(id==null){
            return null;
        }else{
            Foto foto = jdbcTemplate.queryForObject(SQL_GETFOTO_BYID, new FotoParameterizedRowMapper(), id);
            return foto;
        }
    }
    
    public Foto getFotoByNamaFile(String namaFile){
        Foto foto = jdbcTemplate.queryForObject(SQL_GETFOTO_NAMAFILE, new FotoParameterizedRowMapper(), namaFile);
        return foto;
    }

    public void DeleteFotoByid(Integer id) {
        jdbcTemplate.update(SQL_DELETE_FOTO,id);
    }
    
    public void saveDistribusiFoto(Integer idProduk, Integer idFoto) {
        jdbcTemplate.update(SQL_INSERT_DISTRIBUSIFOTO, new Object[]{idProduk,idFoto});
    }
    
    public void DeleteDistribusiFotoByIdProduk(Integer id) {
        jdbcTemplate.update(SQL_DELETE_FOTO_BYID_PRODUK,id);
    }
}