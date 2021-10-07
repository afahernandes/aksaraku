package com.example.aksara;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class    listdataDB extends ListActivity {
	public final static String ID_EXTRA="OK";
	Cursor model=null;
	drvAdapter adapter=null;
	db_hasil helper=null;
	String jns="";
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdb);
        helper=new db_hasil(this);
        Intent i=getIntent();
        jns=i.getStringExtra("jenis");
      //  cekDatabase(jns);
        
        model=helper.getBy_jenis(jns);
        startManagingCursor(model);
        adapter=new drvAdapter(model);
        setListAdapter(adapter);
    }
	@Override
	public void onDestroy(){
		super.onDestroy();
		helper.close();
	}
	@Override
	public void onListItemClick(ListView list, View view, int position, long id){
		Intent i=new Intent(listdataDB.this, detailDB.class);
		i.putExtra(ID_EXTRA, String.valueOf(id));
		startActivity(i);
	}

	class drvAdapter extends CursorAdapter{
		drvAdapter (Cursor c){super(listdataDB.this, c);}
		@Override
		public void bindView(View row, Context ctxt, Cursor c){
			drvHolder holder=(drvHolder)row.getTag();
			holder.populateFrom(c, helper);
		}
		@Override
		public View newView(Context ctxt, Cursor c, ViewGroup parent){
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.rowdb, parent, false);
			drvHolder holder=new drvHolder(row);
			row.setTag(holder);
			return(row);
		}
	}
	static class drvHolder{
		private TextView txt_nama=null;
		private TextView txt_usia=null;
		private TextView txt_hasil=null;
		private TextView txt_keterangan=null;
		private ImageView icon=null;
		private View row=null;
		
		drvHolder(View row){
			this.row=row;
			txt_nama=(TextView)row.findViewById(R.id.lnama);
			txt_usia=(TextView)row.findViewById(R.id.lusia);
			txt_hasil=(TextView)row.findViewById(R.id.lhasil);
			txt_keterangan=(TextView)row.findViewById(R.id.lketerangan);
			icon=(ImageView)row.findViewById(R.id.icon);
		}
		void populateFrom(Cursor c, db_hasil helper){
			txt_nama.setText("Nama : "+helper.getnama(c));
			txt_usia.setText("Usia : "+helper.getusia(c)+" Tahun");
			String jenis=helper.getjenis(c);
			if(jenis.equalsIgnoreCase("Latihan Soal")){
				txt_hasil.setText("Nilai "+helper.gethasil(c)+" Point");
			}else{
				txt_hasil.setText(helper.gethasil(c));

			}
			txt_keterangan.setText("Tgl: "+helper.getwaktu(c));

			}
	}	
	
	
	public void cekDatabase(String jenis) {
		helper = new db_hasil(this);
		Cursor c = helper.getBy_jenis("Latihan Menulis");
		c.moveToFirst();
		c.close();
//		int row = c.getCount();
//		if (row == 0) {
//			helper.insertDb("Budi Hermawan", "17", "Latihan Soal", "65", "20-01-01-2013", "Memuaskan");
//
//		}
	}
}


