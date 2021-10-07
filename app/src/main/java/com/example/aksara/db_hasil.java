package com.example.aksara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db_hasil extends SQLiteOpenHelper {
	private static final String DATABASE_NAME="hasil.db";
	private static final int SCHEMA_VERSION=1;	
	public db_hasil(Context context){super(context, DATABASE_NAME, null, SCHEMA_VERSION);}
	

//----------------------------------------------------------------------------------------------------------------------COPY	
	private static final String tb_hasil="hsl";
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("CREATE TABLE "+tb_hasil+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, usia TEXT, jenis TEXT,hasil TEXT, waktu TEXT, keterangan TEXT)");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
	public Cursor getAll(){
		return (getReadableDatabase().rawQuery("SELECT _id, nama, usia, jenis, hasil, waktu, keterangan FROM "+tb_hasil+" order by nama asc", null));
	}	
	public Cursor gCount(){
		return (getReadableDatabase().rawQuery("SELECT COUNT(*) as `row` FROM "+tb_hasil+"", null));		
	}
	public Cursor getBy_id(String id){
		String[] args={id};
		return (getReadableDatabase().rawQuery("SELECT _id, nama, usia, jenis,hasil, waktu, keterangan FROM "+tb_hasil+" WHERE _id=?", args));
	}
	public Cursor getBy_jenis(String jenis){
		String[] args={jenis};
		return (getReadableDatabase().rawQuery("SELECT _id, nama, usia, jenis,hasil, waktu, keterangan FROM "+tb_hasil+" WHERE jenis=? order by _id desc", args));
	}
	public void insertDb(String nama, String usia, String jenis,String hasil, String waktu, String keterangan){
		ContentValues cv=new ContentValues();
		cv.put("nama", nama);
		cv.put("usia", usia);
		cv.put("jenis", jenis);
		cv.put("hasil", hasil);
		cv.put("waktu", waktu);
		cv.put("keterangan", keterangan);
		getWritableDatabase().insert(tb_hasil,"nama", cv);
	}
	public void updateDb(String id, String nama, String usia, String jenis,String hasil, String waktu, String keterangan){
		ContentValues cv=new ContentValues();
		String[] args={id};
		cv.put("nama", nama);
		cv.put("usia", usia);
		cv.put("jenis", jenis);
		cv.put("hasil", hasil);
		cv.put("waktu", waktu);
		cv.put("keterangan", keterangan);
		getWritableDatabase().update(tb_hasil,cv,"_id=?", args);
	}
	public void deleteDb(String id){
		String[] args={id};
		getWritableDatabase().delete(tb_hasil,"_id=?", args);
	}
	public String getnama(Cursor c){return(c.getString(1));}
	public String getusia(Cursor c){return(c.getString(2));}
	public String getjenis(Cursor c){return(c.getString(3));}
	public String gethasil(Cursor c){return(c.getString(4));}
	public String getwaktu(Cursor c){return(c.getString(5));}
	public String getketerangan(Cursor c){return(c.getString(6));}
//-------------------------------------------------------------------------------------------------------	COPY
	
	
}
