package com.example.aksara;


import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;

public class soalsoal extends Activity {
	RadioButton radA, radB, radC, radD;
	String sNama = "Fulan", sUsia = "xx",  sCatatan = "" ,nn,uu;
	int ke = 0, ike = 0, jumsoal = 20, jumBenar = 0,jumSalah = 0;
	int jd;
	String[] arr_pertanyaan;
	String[] arr_jawabA;
	String[] arr_jawabB;
	String[] arr_jawabC;
	String[] arr_jawabD;
	String[] arr_jawabBenar;
	int[] arr_gambar;
	db_hasil helper=null;


	static final String KEY_record = "soal";
	public static final String KEY_pertanyaan = "pertanyaan";
	public static final String KEY_jawabA = "jawabA";
	public static final String KEY_jawabB = "jawabB";
	public static final String KEY_jawabC = "jawabC";
	public static final String KEY_jawabD = "jawabD";
	public static final String KEY_jawabBenar = "jawabBenar";
	public static final String KEY_gambar = "gambar";
	public static final String KEY_idsoal = "idsoal";


	androidx.appcompat.app.AlertDialog.Builder dialogBuilder;
	androidx.appcompat.app.AlertDialog alertDialog;

	ImageView imgGambar;
	TextView txtTanya;
	TextView txtjudul;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soal);
		helper=new db_hasil(this);

		Intent i=getIntent();
		sNama=i.getStringExtra("nama");
		sUsia=i.getStringExtra("usia");
		Log.v("NAMAA::",sNama);
		Log.v("USIAA::",sUsia);


		txtTanya = (TextView) findViewById(R.id.txtTanya);

		imgGambar = (ImageView) findViewById(R.id.myGambar);
		setData();

		radA = (RadioButton) findViewById(R.id.radA);
		radA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cekJawaban("A");
				ke = ke + 1;
				if (ke >= jumsoal) {
					selesai(R.layout.custompopup_dialog,sNama,sUsia);
				} else {
					lihat();
				}
			}
		});

		radB = (RadioButton) findViewById(R.id.radB);
		radB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cekJawaban("B");
				ke = ke + 1;
				if (ke >= jumsoal) {
					selesai(R.layout.custompopup_dialog,sNama,sUsia);
				} else {
					lihat();
				}
			}
		});
		radC = (RadioButton) findViewById(R.id.radC);
		radC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cekJawaban("C");
				ke = ke + 1;
				if (ke >= jumsoal) {
					selesai(R.layout.custompopup_dialog,sNama,sUsia);
				} else {
					lihat();
				}
			}
		});
		radD = (RadioButton) findViewById(R.id.radD);
		radD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cekJawaban("D");
				ke = ke + 1;
				if (ke >= jumsoal) {
					selesai(R.layout.custompopup_dialog,sNama,sUsia);
				} else {
					lihat();
				}
			}
		});
			lihat();
	}

	void cekJawaban(String pil) {
		if (arr_jawabBenar[ike].equalsIgnoreCase(pil)) {
			jumBenar = jumBenar + 1;
			sCatatan = sCatatan + "Soal ke-" + (ke + 1) + " Benar\n";
		//	Toast.makeText(getBaseContext(), " Pilihan Anda " + pil + " : Benar!", Toast.LENGTH_LONG).show();
		} else {
            jumSalah= jumSalah+ 1;
			sCatatan = sCatatan + "Soal ke-" + (ke + 1) + " Salah\n";
		//	Toast.makeText(getBaseContext(), " Pilihan Anda " + pil + " : Salah!", Toast.LENGTH_LONG).show();
		}
	}

	void lihat() {
		Random rand = new Random();
		radA.setChecked(false);
		radB.setChecked(false);
		radC.setChecked(false);
		ike = rand.nextInt(19);
		txtTanya.setText((ke + 1) + "." + arr_pertanyaan[ike]);
		radA.setText(arr_jawabA[ike]);
		radB.setText(arr_jawabB[ike]);
		radC.setText(arr_jawabC[ike]);
		radD.setText(arr_jawabD[ike]);
		//	txtTanyake.setText("Pertanyaan ke "+(ke+1) +" dari "+jumsoal+" pertanyaan !");
  		imgGambar.setImageResource(arr_gambar[ike]);

	}


//	public void selesaiss() {
//        int nilai=0;
//	    nilai=jumBenar*5;
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Hasil Nilai");
//        builder.setMessage("Total Soal : "+ jumsoal+"\n" +
//                "Benar : "+ jumBenar+"\n" +
//                "Salah : "+jumSalah+"\n" +
//                "Nilai : "+nilai);
//        builder.setPositiveButton("Ok", new OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//finish();
//            }
//        });
//        builder.show();
//	}

	int nilai=0;

String waktu,keterangan;
	private void selesai(int layout,String nama,String usia){

		nilai=jumBenar*5;
		if(nilai<60){
			keterangan="Mengecewakan";
		}else if(nilai >60 && nilai <80){
			keterangan="lumayan";
		}else{
			keterangan="Memuaskan";
		}


		dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(soalsoal.this);
		View layoutView = getLayoutInflater().inflate(layout, null);
		Button btntutup = layoutView.findViewById(R.id.btntutup);
		TextView txtbenar = layoutView.findViewById(R.id.txtbenar);
		TextView txtsalah = layoutView.findViewById(R.id.txtsalah);
		TextView txtscore = layoutView.findViewById(R.id.txtscore);
		TextView txthuruf = layoutView.findViewById(R.id.txthuruf);txthuruf.setText("NAMA :"+sNama);

		txtbenar.setText(jumBenar+" dari "+jumsoal+" Soal");
		txtsalah.setText(String.valueOf(jumSalah));
		txtscore.setText(String.valueOf(nilai));

		nn=sNama;
		uu=sUsia;
		Calendar c = Calendar.getInstance();
		System.out.println("Current time => "+c.getTime());

		SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
		 waktu = df.format(c.getTime());
		// formattedDate have current date/time
		//Toast.makeText(this, waktu, Toast.LENGTH_SHORT).show();


		dialogBuilder.setView(layoutView);
		alertDialog = dialogBuilder.create();
		alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		alertDialog.show();

		btntutup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				alertDialog.dismiss();
				helper.insertDb(nn, uu, "Latihan Soal",String.valueOf(nilai),waktu,keterangan);
				finish();
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	void setData() {
		jd = 30;
  		arr_pertanyaan = new String[jd];
		arr_jawabA = new String[jd];
		arr_jawabB = new String[jd];
		arr_jawabC = new String[jd];
		arr_jawabD = new String[jd];
		arr_jawabBenar = new String[jd];
		arr_gambar = new int[jd];


		arr_pertanyaan[0] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[0] = "Ka";
		arr_jawabB[0] = "Ma";
		arr_jawabC[0] = "Ba";
		arr_jawabD[0] = "Kha";
		arr_jawabBenar[0] = "A";
		arr_gambar[0] =R.drawable.da_1;


		arr_pertanyaan[1] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[1] = "Ta";
		arr_jawabB[1] = "Nga";
		arr_jawabC[1] = "La";
		arr_jawabD[1] = "Ga";
		arr_jawabBenar[1] = "D";
		arr_gambar[1] =R.drawable.da_2;

		arr_pertanyaan[2] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[2] = "Na";
		arr_jawabB[2] = "A";
		arr_jawabC[2] = "Nga";
		arr_jawabD[2] = "La";
		arr_jawabBenar[2] = "C";
		arr_gambar[2] =R.drawable.da_3;


		arr_pertanyaan[3] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[3] = "Pa";
		arr_jawabB[3] = "Ca";
		arr_jawabC[3] = "Ra";
		arr_jawabD[3] = "Nya";
		arr_jawabBenar[3] = "A";
		arr_gambar[3] =R.drawable.da_4;

		arr_pertanyaan[4] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[4] = "Ga";
		arr_jawabB[4] = "Ma";
		arr_jawabC[4] = "Pa";
		arr_jawabD[4] = "Ba";
		arr_jawabBenar[4] = "D";
		arr_gambar[4] =R.drawable.da_5;

		arr_pertanyaan[5] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[5] = "Ha";
		arr_jawabB[5] = "Ga";
		arr_jawabC[5] = "Ma";
		arr_jawabD[5] = "La";
		arr_jawabBenar[5] = "C";
		arr_gambar[5] =R.drawable.da_6;

		arr_pertanyaan[6] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[6] = "Ba";
		arr_jawabB[6] = "Ta";
		arr_jawabC[6] = "Nga";
		arr_jawabD[6] = "Pa";
		arr_jawabBenar[6] = "B";
		arr_gambar[6] =R.drawable.da_7;

		arr_pertanyaan[7] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[7] = "Da";
		arr_jawabB[7] = "La";
		arr_jawabC[7] = "Ra";
		arr_jawabD[7] = "Na";
		arr_jawabBenar[7] = "A";
		arr_gambar[7] =R.drawable.da_8;

		arr_pertanyaan[8] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[8] = "Ha";
		arr_jawabB[8] = "Na";
		arr_jawabC[8] = "Gha";
		arr_jawabD[8] = "A";
		arr_jawabBenar[8] = "B";
		arr_gambar[8] =R.drawable.da_9;

		arr_pertanyaan[9] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[9] = "Ca";
		arr_jawabB[9] = "Na";
		arr_jawabC[9] = "Ba";
		arr_jawabD[9] = "Ja";
		arr_jawabBenar[9] = "A";
		arr_gambar[9] =R.drawable.da_10;

		arr_pertanyaan[10] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[10] = "Ra";
		arr_jawabB[10] = "Nya";
		arr_jawabC[10] = "Ja";
		arr_jawabD[10] = "Ma";
		arr_jawabBenar[10] = "C";
		arr_gambar[10] =R.drawable.da_11;

		arr_pertanyaan[11] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[11] = "Nya";
		arr_jawabB[11] = "La";
		arr_jawabC[11] = "Ja";
		arr_jawabD[11] = "Ya";
		arr_jawabBenar[11] = "A";
		arr_gambar[11] =R.drawable.da_12;

		arr_pertanyaan[12] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[12] = "Ya";
		arr_jawabB[12] = "Ma";
		arr_jawabC[12] = "Na";
		arr_jawabD[12] = "nya";
		arr_jawabBenar[12] = "A";
		arr_gambar[12] =R.drawable.da_13;

		arr_pertanyaan[13] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[13] = "Ra";
		arr_jawabB[13] = "Ga";
		arr_jawabC[13] = "Ka";
		arr_jawabD[13] = "A";
		arr_jawabBenar[13] = "C";
		arr_gambar[13] =R.drawable.da_14;

		arr_pertanyaan[14] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[14] = "Nya";
		arr_jawabB[14] = "Gha";
		arr_jawabC[14] = "Ga";
		arr_jawabD[14] = "La";
		arr_jawabBenar[14] = "D";
		arr_gambar[14] =R.drawable.da_15;

		arr_pertanyaan[15] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[15] = "Sa";
		arr_jawabB[15] = "Ra";
		arr_jawabC[15] = "Wa";
		arr_jawabD[15] = "Ya";
		arr_jawabBenar[15] = "B";
		arr_gambar[15] =R.drawable.da_16;

		arr_pertanyaan[16] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[16] = "Pa";
		arr_jawabB[16] = "Ja";
		arr_jawabC[16] = "Sa";
		arr_jawabD[16] = "Ra";
		arr_jawabBenar[16] = "C";
		arr_gambar[16] =R.drawable.da_17;

		arr_pertanyaan[17] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[17] = "Wa";
		arr_jawabB[17] = "Ma";
		arr_jawabC[17] = "La";
		arr_jawabD[17] = "Sa";
		arr_jawabBenar[17] = "A";
		arr_gambar[17] =R.drawable.da_18;

		arr_pertanyaan[18] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[18] = "Ka";
		arr_jawabB[18] = "Ta";
		arr_jawabC[18] = "Sa";
		arr_jawabD[18] = "Ha";
		arr_jawabBenar[18] = "D";
		arr_gambar[18] =R.drawable.da_19;

		arr_pertanyaan[19] = "Dibaca apakah aksara tersebut..?";
		arr_jawabA[19] = "Ra";
		arr_jawabB[19] = "Ma";
		arr_jawabC[19] = "Gha";
		arr_jawabD[19] = "Nya";
		arr_jawabBenar[19] = "C";
		arr_gambar[19] =R.drawable.da_20;

		arr_pertanyaan[20] = "Dibaca apakah anak huruf tersebut..?";
		arr_jawabA[20] = "Bitan i";
		arr_jawabB[20] = "Bitan O";
		arr_jawabC[20] = "Rejunjung";
		arr_jawabD[20] = "Bicek";
		arr_jawabBenar[20] = "B";
		arr_gambar[20] =R.drawable.da_20;




	

	}
}
