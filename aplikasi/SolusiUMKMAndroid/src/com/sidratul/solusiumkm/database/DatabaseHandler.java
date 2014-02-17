package com.sidratul.solusiumkm.database;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sidratul.solusiumkm.model.KategoriUmkm;
import com.sidratul.solusiumkm.model.Umkm;
import com.sidratul.solusiumkm.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler.Value;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION=1;
	
	private static final String DATABASE_NAME="toko-belajar";
	
	//table kategori umkm
	public static final String TABLE_KATEGORI_UMKM="kategori_umkm";
	
	private static final String KKU_ID="id";
	private static final String KKU_JENIS_UMKM="jenis_umkm";
	
	
	//table umkm
	public static final String TABLE_UMKM="umkm";
	
	private static final String KU_ID="id";
	private static final String KU_KODE_UMKM="kode_umkm";
	private static final String KU_NAMA_UMKM="nama_umkm";
	private static final String KU_PEMILIK_UMKM="pemilik_umkm";
	private static final String KU_ID_KATEGORI_UMKM="id_kategori_umkm";
	private static final String KU_KETERANGAN_UMKM="keterangan_umkm";
	private static final String KU_VISI="visi";
	private static final String KU_MISI="misi";
	private static final String KU_ALAMAT="alamat";
	private static final String KU_NO_TELPON="no_telpon";
	private static final String KU_EMAIL="email";
	
		
	//table user
	public static final String TABLE_USER="user";
	
	private static final String KUS_ID="id";
	private static final String KUS_USERNAME="username";
	private static final String KUS_PASSWORD="password";
	private static final String KUS_PERAN="peran";
	private static final String KUS_ID_UMKM="id_umkm";
	private static final String KUS_TERAKHIR_LOGIN="terakhir_login";
	private static final String KUS_AKTIF="aktif";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	String CREATE_KATEGORI_UMKM="create table "+TABLE_KATEGORI_UMKM+"("
			+ KKU_ID+" INTEGER primary key,"
			+ KKU_JENIS_UMKM+" TEXT"
			+ ")";
	
	String CREATE_UMKM="create table "+TABLE_UMKM+"("
			+KU_ID+" INTEGER primary key,"
			+KU_KODE_UMKM+" TEXT ,"
			+KU_NAMA_UMKM+" TEXT ,"
			+KU_ID_KATEGORI_UMKM+" INTEGER,"
			+KU_KETERANGAN_UMKM+" TEXT ,"
			+KU_VISI+" TEXT ,"
			+KU_MISI+" TEXT ,"
			+KU_ALAMAT+" TEXT ,"
			+KU_NO_TELPON+" TEXT ,"
			+KU_EMAIL+" TEXT ,"			
			+"Foreign key("+KU_ID_KATEGORI_UMKM+") references "+TABLE_KATEGORI_UMKM+"("+KKU_ID+")"
			+ ")";
	
	String CREATE_USER="create table "+TABLE_USER+"("
			+ KUS_ID+" INTEGER PRIMARY KEY ,"
			+ KUS_USERNAME+" TEXT ,"
			+ KUS_ID_UMKM+" INTEGER,"
			+ KUS_PASSWORD+" TEXT ,"
			+ KUS_PERAN+" TEXT ,"
			+ KUS_TERAKHIR_LOGIN+" DATETIME ,"
			+"Foreign key("+KUS_ID_UMKM+") references "+TABLE_UMKM+"("+KU_ID+")"
			+ ")";

	
	@Override
	public void onCreate(SQLiteDatabase db) {
				
		db.execSQL(CREATE_KATEGORI_UMKM);
		db.execSQL(CREATE_UMKM);
		db.execSQL(CREATE_USER);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exsits "+TABLE_UMKM);
		db.execSQL("drop table if exsits "+TABLE_KATEGORI_UMKM);
		
		db.execSQL("drop table if exsits "+TABLE_USER);
		
		onCreate(db);
		
	}
	
	public void resetTable(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("drop table if exists "+TABLE_UMKM);
		db.execSQL("drop table if exists "+TABLE_KATEGORI_UMKM);
		
		Log.d("hapus","berhasil menghapus");
		
		db.execSQL(CREATE_KATEGORI_UMKM);
		db.execSQL(CREATE_UMKM);
		
	}
	
	
	//kategori umkm
	public void tambahKategoriUmkm(KategoriUmkm kategoriUmkm){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KKU_ID, kategoriUmkm.getId());
		values.put(KKU_JENIS_UMKM, kategoriUmkm.getJenisUmkm());
		
		db.insert(TABLE_KATEGORI_UMKM, null, values);
		
		Log.i("insert","berhasil menambahkan");
		db.close();
	}
	
	public List<KategoriUmkm> getAllKategoriUmkm(){
		List<KategoriUmkm> kategoriUmkms = new ArrayList<KategoriUmkm>();
		
		String selectSql = "select * from "+TABLE_KATEGORI_UMKM;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectSql, null);
		
		if(cursor.moveToFirst()){
			do{
				KategoriUmkm kategoriUmkm = new KategoriUmkm();
				kategoriUmkm.setId(Integer.parseInt(cursor.getString(0)));
				kategoriUmkm.setJenisUmkm(cursor.getString(1));
				
				kategoriUmkms.add(kategoriUmkm);
			}while(cursor.moveToNext());
		}
		
		return kategoriUmkms; 
	}
	
	public KategoriUmkm getKategoriUmkm(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_KATEGORI_UMKM, new String[]{KKU_ID, KKU_JENIS_UMKM}, 
				KKU_ID+"=?",new String[]{String.valueOf(id)},
				null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		
		KategoriUmkm kategoriUmkm = new KategoriUmkm();
		kategoriUmkm.setId(Integer.parseInt(cursor.getString(0)));
		kategoriUmkm.setJenisUmkm(cursor.getString(1));
		return kategoriUmkm;
	}
	
	// umkm
	public void tambahUmkm(Umkm umkm){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KU_ID, umkm.getId());
		values.put(KU_KODE_UMKM, umkm.getKodeUmkm());
		values.put(KU_NAMA_UMKM, umkm.getNamaUmkm());
		values.put(KU_PEMILIK_UMKM, umkm.getPemilikUmkm());
		values.put(KU_ID_KATEGORI_UMKM, umkm.getKategoriUmkm().getId());
		values.put(KU_KETERANGAN_UMKM, umkm.getKeteranganUmkm());
		values.put(KU_VISI, umkm.getVisi());
		values.put(KU_MISI, umkm.getMisi());
		values.put(KU_ALAMAT, umkm.getAlamat());
		values.put(KU_NO_TELPON, umkm.getNoTelp());
		values.put(KU_EMAIL, umkm.getEmail());
		
		
		db.insert(TABLE_UMKM, null, values);
		
		Log.i("insert","berhasil menambahkan");
		db.close();
	}
	
	public List<Umkm> getAllUmkm(){
		List<Umkm> umkms = new ArrayList<Umkm>();
		
		String selectSql = "select * from "+TABLE_UMKM;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectSql, null);
		
		if(cursor.moveToFirst()){
			do{
				Umkm umkm = new Umkm();
				umkm.setId(Integer.parseInt(cursor.getString(0)));
				umkm.setKodeUmkm(cursor.getString(1));
				umkm.setNamaUmkm(cursor.getString(2));
				umkm.setPemilikUmkm(cursor.getString(3));
				umkm.setKategoriUmkm(getKategoriUmkm(Integer.parseInt(cursor.getString(4))));
				umkm.setKeteranganUmkm(cursor.getString(5));
				umkm.setVisi(cursor.getString(6));
				umkm.setMisi(cursor.getString(7));
				umkm.setAlamat(cursor.getString(8));
				umkm.setNoTelp(cursor.getString(9));
				umkm.setEmail(cursor.getString(10));
				
				umkms.add(umkm);
			}while(cursor.moveToNext());
		}
		
		return umkms; 
	}
//	
	public Umkm getUmkm(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_UMKM, new String[]{
					KU_ID,
					KU_KODE_UMKM,
					KU_NAMA_UMKM,
					KU_PEMILIK_UMKM,
					KU_ID_KATEGORI_UMKM,
					KU_KETERANGAN_UMKM,
					KU_VISI,
					KU_MISI,
					KU_ALAMAT,
					KU_NO_TELPON,
					KU_EMAIL
				}, 
				KU_ID+"=?",new String[]{String.valueOf(id)},
				null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		
			Umkm umkm = new Umkm();
			umkm.setId(Integer.parseInt(cursor.getString(0)));
			umkm.setKodeUmkm(cursor.getString(1));
			umkm.setNamaUmkm(cursor.getString(2));
			umkm.setPemilikUmkm(cursor.getString(3));
			umkm.setKategoriUmkm(getKategoriUmkm(Integer.parseInt(cursor.getString(4))));
			umkm.setKeteranganUmkm(cursor.getString(5));
			umkm.setVisi(cursor.getString(6));
			umkm.setMisi(cursor.getString(7));
			umkm.setAlamat(cursor.getString(8));
			umkm.setNoTelp(cursor.getString(9));
			umkm.setEmail(cursor.getString(10));
		
		return umkm;
	}
	
	//kategori user
	public void tambahUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KUS_ID, user.getId());
		values.put(KUS_USERNAME, user.getUsername());
		values.put(KUS_ID_UMKM, user.getUmkm().getId());
		values.put(KUS_PERAN, user.getPeran());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tglString;
		try{
			tglString = dateFormat.format(user.getTerakhirLogin());
		}catch(Exception e){
			tglString = null;
		}
		
		values.put(KUS_TERAKHIR_LOGIN, tglString);
		values.put(KUS_AKTIF, user.getAktif());
		
		
		
		db.insert(TABLE_USER, null, values);
		
		Log.i("insert","berhasil menambahkan");
		db.close();
	}
	
	public List<User> getAllUser(){
		List<User> users = new ArrayList<User>();
		
		String selectSql = "select * from "+TABLE_USER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectSql, null);
		
		if(cursor.moveToFirst()){
			do{
				User user = new User();
				user.setId(Integer.parseInt(cursor.getString(0)));
				user.setUsername(cursor.getString(1));
				user.setUmkm(getUmkm(Integer.parseInt(cursor.getString(2))));
				user.setPassword(cursor.getString(3));
				user.setPeran(cursor.getString(4));
				
				Date tglUp = null;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					tglUp = dateFormat.parse(cursor.getString(5));
				} catch (Exception e) {
					tglUp = null;
					e.printStackTrace();
				}
				
				user.setTerakhirLogin(tglUp);
				user.setAktif(Integer.parseInt(cursor.getString(6)));
				
				users.add(user);
				
			}while(cursor.moveToNext());
		}
		
		return users; 
	}
	
	public User getUser(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_USER, new String[]{
					KUS_ID, 
					KUS_USERNAME,
					KUS_ID_UMKM,
					KUS_PASSWORD,
					KUS_PERAN,
					KUS_PERAN,
					KUS_TERAKHIR_LOGIN,
					KUS_AKTIF
				}, 
				KUS_ID+"=?",new String[]{String.valueOf(id)},
				null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		
		User user = new User();
		user.setId(Integer.parseInt(cursor.getString(0)));
		user.setUsername(cursor.getString(1));
		user.setUmkm(getUmkm(Integer.parseInt(cursor.getString(2))));
		user.setPassword(cursor.getString(3));
		user.setPeran(cursor.getString(4));
		
		Date tglUp = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			tglUp = dateFormat.parse(cursor.getString(5));
		} catch (Exception e) {
			tglUp = null;
			e.printStackTrace();
		}
		
		user.setTerakhirLogin(tglUp);
		user.setAktif(Integer.parseInt(cursor.getString(6)));
		
		return user;
		
	}
	
}
