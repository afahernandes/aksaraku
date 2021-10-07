package com.example.aksara;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class search extends Activity{
EditText edCari;
ListView listview;
Button btnCari;

int jd;
String[]arrNama;
String[]arrDes;
String[]arrTittle;
int[]arrGbr;

String[]arrNama2;
String[]arrDes2;
String[]arrTittle2;
int[]arrGbr2;

String[]arrNama3;
String[]arrDes3;
String[]arrTittle3;
int[]arrGbr3;

int textlength = 0;
ArrayList<String> text_sort = new ArrayList<String>();
ArrayList<Integer> image_sort = new ArrayList<Integer>();

public void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
setContentView(R.layout.listviewcolorcari);

jd=7;
arrNama=new String[jd];
arrDes=new String[jd];
arrTittle=new String[jd];
arrGbr=new int[jd];

arrNama2=new String[jd];
arrDes2=new String[jd];
arrTittle2=new String[jd];
arrGbr2=new int[jd];

arrTittle[0]="Selamat Datang di Kota Bandar Lampung";
arrNama[0]="Selamat Datang di Kota Bandar Lampung";
arrDes[0]="--";
arrGbr[0]=R.drawable.selamat_datang;

arrTittle[1]="Selamat Jalan";
arrNama[1]="Selamat Jalan";
arrDes[1]="--";
arrGbr[1]=R.drawable.selamat_jalan;

arrTittle[2]="Beguai Jejama";
arrNama[2]="Gotong Royong/ Bekerja Bersama";
arrDes[2]="--";
arrGbr[2]=R.drawable.beguai_jejama;

arrTittle[3]="KRS004/Rp.500.000;";
arrNama[3]="Ragom Mufakat";
arrDes[3]="Bermusyawarah Untuk Mufakat ";
arrGbr[3]=R.drawable.khagom_mufakat;

arrTittle[4]="Jurai Siwo";
arrNama[4]="Bekerjasama Mewujudkan Kepentingan Masyarakat Menuju Kebaikan";
arrDes[4]="--";
arrGbr[4]=R.drawable.jurai_siwo;


arrTittle[5]="Bumei Tuwah Bepadan ";
arrNama[5]="Daerah Yang Memberikan Kemakmuran";
arrDes[5]="--";
arrGbr[5]=R.drawable.bumei_tuwah_bepadan;

arrTittle[6]="Ragem Tunas Lampung";
arrNama[6]="Masyarakat Yang Menerima Kebergaman ";
arrDes[6]="--";
arrGbr[6]=R.drawable.ragem_tunas_lampung;

btnCari = (Button) findViewById(R.id.btnCari);
edCari = (EditText) findViewById(R.id.edCari);
listview = (ListView) findViewById(R.id.listCari);

listview.setAdapter(new MyCustomAdapter(arrNama, arrGbr));
listview.setOnItemClickListener(new OnItemClickListener() {
	   @Override
	   public void onItemClick(AdapterView<?> a, View v, int p, long id) { 
	        		//Toast.makeText(getBaseContext(), "Anda telah memilih no: "+p+"="+ arrNama[p], Toast.LENGTH_LONG).show();
	        	}  
	        });

btnCari.setOnClickListener(new OnClickListener(){
public void onClick(View v){textlength = edCari.getText().length();
text_sort.clear();
image_sort.clear();
String scari=edCari.getText().toString().toLowerCase();

int ada=0;
for (int i = 0; i < jd; i++){
String snama=arrNama[i].toLowerCase();
	if (textlength <= arrNama[i].length()){
	if (snama.indexOf(scari)>=0){	//if (edCari.getText().toString().equalsIgnoreCase((String) arrNama[i].subSequence(0, textlength))){//huruf yg awalannya sama
		text_sort.add(arrNama[i]);
		image_sort.add(arrGbr[i]);
			arrNama2[ada]=arrNama[i];
			arrDes2[ada]=arrDes[i];
			arrTittle2[ada]=arrTittle[i];
			arrGbr2[ada]=arrGbr[i];
			ada=ada+1;
	}
  }
}

arrNama3=new String[ada];
arrDes3=new String[ada];
arrTittle3=new String[ada];
arrGbr3=new int[ada];

for (int i = 0; i < ada; i++){
	arrNama3[i]=arrNama2[i];
	arrDes3[i]=arrNama2[i];
	arrTittle3[i]=arrTittle2[i];
	arrGbr3[i]=arrGbr2[i];
}

	listview.setAdapter(new MyCustomAdapter(text_sort, image_sort));
	listview.setOnItemClickListener(new OnItemClickListener() {
		   @Override
		   public void onItemClick(AdapterView<?> a, View v, int p, long id) { 
		        		//Toast.makeText(getBaseContext(), "Anda telah memilih no "+p+"="+ arrNama3[p], Toast.LENGTH_LONG).show();
		        	}  
		        });
	}});
}

class MyCustomAdapter extends BaseAdapter{
			String[] data_text;
			int[] data_image;
		MyCustomAdapter(){}
		
		MyCustomAdapter(String[] text, int[] image){
			data_text = text;
			data_image = image;
		}
		
		MyCustomAdapter(ArrayList<String> text, ArrayList<Integer> image){
			data_text = new String[text.size()];
			data_image = new int[image.size()];
				for (int i = 0; i < text.size(); i++) {
					data_text[i] = text.get(i);
					data_image[i] = image.get(i);
				}
		}
		
		public int getCount(){return data_text.length;}
		public String getItem(int position){return null;}
		public long getItemId(int position){return position;}
		public View getView(int p, View convertView, ViewGroup parent){
			LayoutInflater inflater = getLayoutInflater();
			View row;
			row = inflater.inflate(R.layout.listviewcolordetail, parent, false);
			TextView textview = (TextView) row.findViewById(R.id.txtCari);
			ImageView imageview = (ImageView) row.findViewById(R.id.imgCari);
			textview.setText(data_text[p]);
			imageview.setImageResource(data_image[p]);
			return (row);
			}
	}

}
