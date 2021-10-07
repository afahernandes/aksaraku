package com.example.aksara;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DaftarAnakHuruf extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;

 TextView txtjudul1,txtjudul2;
    int jd=0;

    String[]arnama;
    String[]ardeskripsi;
    int[]arsuara;
    Bitmap []arBitmap;

    String id_kota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_main);



        txtjudul1=(TextView)findViewById(R.id.spp1);txtjudul1.setText("Anak Huruf");
        txtjudul2=(TextView)findViewById(R.id.txtjudul);txtjudul2.setText("Anak Huruf Akasara Lampung");


        baca();



        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(DaftarAnakHuruf.this, R.layout.grid_item, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                showAlertDialog(R.layout.popup_dialog,position);
//                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
//                Intent intent = new Intent(Daftar_florist.this, Detail_florist.class);
//                intent.putExtra("pk", arnama[position]);//item.getTitle()
////                            intent.putExtra("title2", ardeskripsi[position]);
////                            intent.putExtra("title3", arsuara[position]);
////                            intent.putExtra("title4", arstatus[position]);
////                            intent.putExtra("gambar", argambar[position]);
//
//                //intent.putExtra("image", item.getImage());
//                startActivity(intent);
            }
        });
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        //TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < jd; i++) {//jd/imgs.length()
            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),arBitmap[i]);// imgs.getResourceId(i, -1)
            imageItems.add(new ImageItem(arBitmap[i], arnama[i]));
        }
        return imageItems;
    }


    private void showAlertDialog(int layout,int v){
        dialogBuilder = new AlertDialog.Builder(DaftarAnakHuruf.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        ImageButton btnsuara = layoutView.findViewById(R.id.btnsuara);
        Button btntutup = layoutView.findViewById(R.id.btntutup);
        ImageView txtgambar = layoutView.findViewById(R.id.txtgambar);
        TextView txthuruf = layoutView.findViewById(R.id.txthuruf);
        TextView txtdeskripsi = layoutView.findViewById(R.id.txtdeskripsi);
        final int suara=arsuara[v];
        txtgambar.setImageBitmap(arBitmap[v]);
        txthuruf.setText(arnama[v]);
        txtdeskripsi.setText(ardeskripsi[v]);

        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        btnsuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), suara);
                if(!mp.isPlaying()) {
                    mp.start();
                    //Toast.makeText(Bulan.this, arDes[selectedImagePosition], Toast.LENGTH_LONG).show();
                }
            }
        });

        btntutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }
    void baca () {

    jd=12;
    arnama=new String[jd];
    ardeskripsi=new String[jd];
    arsuara=new int[jd];
    arBitmap=new Bitmap[jd];


        arnama[0]="Datas";
        ardeskripsi[0]= "Bunyi : an";
        arsuara[0]= R.raw.san_1;
        arBitmap[0]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_1);


        arnama[1]="Tekelingai";
        ardeskripsi[1]= "Bunyi : ai";
        arsuara[1]= R.raw.san_2;
        arBitmap[1]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_2);

        arnama[2]="Ulan e";
        ardeskripsi[2]= "Bunyi : e`";
        arsuara[2]= R.raw.san_3;
        arBitmap[2]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_3);

        arnama[3]="Nengen";
        ardeskripsi[3]= "Dibaca Mati";
        arsuara[3]= R.raw.san_4;
        arBitmap[3]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_4);

        arnama[4]="Rejunjung";
        ardeskripsi[4]= "Bunyi : ar";
        arsuara[4]= R.raw.san_5;
        arBitmap[4]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_5);

        arnama[5]="Tekelungau";
        ardeskripsi[5]= "Bunyi : au";
        arsuara[5]= R.raw.san_6;
        arBitmap[5]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_6);

        arnama[6]="Tekelubang";
        ardeskripsi[6]= "Bunyi : ang";
        arsuara[6]= R.raw.san_7;
        arBitmap[6]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_7);

        arnama[7]="Bicek";
        ardeskripsi[7]= "Bunyi : E";
        arsuara[7]= R.raw.san_8;
        arBitmap[7]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_8);

        arnama[8]="Ulan i";
        ardeskripsi[8]= "Bunyi : i";
        arsuara[8]= R.raw.san_9;
        arBitmap[8]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_9);

        arnama[9]="Bitan o";
        ardeskripsi[9]= "Bunyi : o";
        arsuara[9]= R.raw.san_10;
        arBitmap[9]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_10);

        arnama[10]="Bitan U";
        ardeskripsi[10]= "Bunyi : u";
        arsuara[10]=R.raw.san_11;
        arBitmap[10]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_11);

        arnama[11]="Keleniah";
        ardeskripsi[11]= "Bunyi : ah";
        arsuara[11]= R.raw.san_12;
        arBitmap[11]=BitmapFactory.decodeResource(this.getResources(),R.drawable.an_12);


    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}