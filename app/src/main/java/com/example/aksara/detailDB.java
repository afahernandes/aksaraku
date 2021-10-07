package com.example.aksara;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class detailDB extends Activity{
	String _id=null;
	EditText nama=null;
	EditText usia=null;
	EditText jenis=null;
	EditText hasil=null;
	EditText waktu=null;
	EditText keterangan=null;
	db_hasil helper=null;
		
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detaildb);
		helper=new db_hasil(this);
		nama=(EditText)findViewById(R.id.dnama);
		usia=(EditText)findViewById(R.id.dusia);
		jenis=(EditText)findViewById(R.id.djenis);
		hasil=(EditText)findViewById(R.id.dhasil);
		waktu=(EditText)findViewById(R.id.dwaktu);
		keterangan=(EditText)findViewById(R.id.dketerangan);
		

		Button delete=(Button)findViewById(R.id.ddelete);
		delete.setOnClickListener(onDelete);
		
		_id=getIntent().getStringExtra(listdataDB.ID_EXTRA);
		if (_id!=null){load();}		
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		helper.close();
	}

	private void load(){
		Cursor c=helper.getBy_id(_id);
		c.moveToFirst();
		nama.setText(helper.getnama(c));
		usia.setText(helper.getusia(c));
		jenis.setText(helper.getjenis(c));
		hasil.setText(helper.gethasil(c));
		waktu.setText(helper.getwaktu(c));
		keterangan.setText(helper.getketerangan(c));
		c.close();
	}
	private View.OnClickListener onSave=new View.OnClickListener() {
		public void onClick(View v) {        
			String ceknama = nama.getText().toString();
    		String cekusia= usia.getText().toString();
    		String cekhasil = hasil.getText().toString();
    			if (ceknama.trim().length() ==0||cekusia.trim().length() ==0||cekhasil.trim().length() ==0){	
    				setAlert();
    			}	
    			else if (_id==null){
		    		helper.insertDb(nama.getText().toString(), usia.getText().toString(),jenis.getText().toString(),hasil.getText().toString(),waktu.getText().toString(),keterangan.getText().toString());				
		    		finish();
				}
				else{
					helper.updateDb(_id, nama.getText().toString(), usia.getText().toString(), jenis.getText().toString(),hasil.getText().toString(),waktu.getText().toString(),keterangan.getText().toString());
					finish();
				}
		}
	};
	
	private View.OnClickListener onDelete=new View.OnClickListener() {
		public void onClick(View v) {			
				helper.deleteDb(_id);
				finish();
		}
	};
	
	private void setAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);   
        builder.setMessage("nama, usia, hasil  harap diisi")
        .setCancelable(false)
        .setNeutralButton("Ok",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	dialog.cancel();
            }
       });
        AlertDialog alert = builder.create();
        alert.show();
	}

	
}
