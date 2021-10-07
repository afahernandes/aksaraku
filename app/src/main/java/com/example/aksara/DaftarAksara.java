package com.example.aksara;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DaftarAksara extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;

    int jd=0;

    String[]arnama;
    String[]ardeskripsi;
    int[]arsuara;
    Bitmap []arBitmap;

   TextView txtjudul1,txtjudul2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_main);


        txtjudul1=(TextView)findViewById(R.id.spp1);txtjudul1.setText("Induk Huruf");
        txtjudul2=(TextView)findViewById(R.id.txtjudul);txtjudul2.setText("Induk Huruf Akasara Lampung");

        baca();



        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(DaftarAksara.this, R.layout.grid_item, getData());
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
        dialogBuilder = new AlertDialog.Builder(DaftarAksara.this);
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

    jd=20;
    arnama=new String[jd];
    ardeskripsi=new String[jd];
    arsuara=new int[jd];
    arBitmap=new Bitmap[jd];


        arnama[0]="Ka";
        ardeskripsi[0]= "-";
        arsuara[0]=R.raw.sa_1;
        arBitmap[0]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_1);

        arnama[1]="Ga";
        ardeskripsi[1]= "-";
        arsuara[1]=R.raw.sa_2;
        arBitmap[1]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_2);

        arnama[2]="Nga";
        ardeskripsi[2]= "-";
        arsuara[2]= R.raw.sa_3;
        arBitmap[2]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_3);

        arnama[3]="Pa";
        ardeskripsi[3]= "-";
        arsuara[3]= R.raw.sa_4;
        arBitmap[3]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_4);

        arnama[4]="Ba";
        ardeskripsi[4]= "-";
        arsuara[4]= R.raw.sa_5;
        arBitmap[4]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_5);

        arnama[5]="Ma";
        ardeskripsi[5]= "-";
        arsuara[5]= R.raw.sa_6;
        arBitmap[5]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_6);

        arnama[6]="Ta";
        ardeskripsi[6]= "-";
        arsuara[6]= R.raw.sa_7;
        arBitmap[6]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_7);

        arnama[7]="Da";
        ardeskripsi[7]= "-";
        arsuara[7]= R.raw.sa_8;
        arBitmap[7]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_8);

        arnama[8]="Na";
        ardeskripsi[8]= "-";
        arsuara[8]=R.raw.sa_9;
        arBitmap[8]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_9);

        arnama[9]="Ca";
        ardeskripsi[9]= "-";
        arsuara[9]=R.raw.sa_10;
        arBitmap[9]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_10);

        arnama[10]="Ja";
        ardeskripsi[10]= "-";
        arsuara[10]= R.raw.sa_11;
        arBitmap[10]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_11);

        arnama[11]="Nya";
        ardeskripsi[11]= "-";
        arsuara[11]= R.raw.sa_12;
        arBitmap[11]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_12);

        arnama[12]="Ya";
        ardeskripsi[12]= "-";
        arsuara[12]=R.raw.sa_13;
        arBitmap[12]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_13);

        arnama[13]="A";
        ardeskripsi[13]= "-";
        arsuara[13]= R.raw.sa_14;
        arBitmap[13]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_14);

        arnama[14]="La";
        ardeskripsi[14]= "-";
        arsuara[14]= R.raw.sa_15;
        arBitmap[14]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_15);

        arnama[15]="Ra";
        ardeskripsi[15]= "-";
        arsuara[15]=R.raw.sa_16;
        arBitmap[15]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_16);

        arnama[16]="Sa";
        ardeskripsi[16]= "-";
        arsuara[16]= R.raw.sa_17;
        arBitmap[16]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_17);

        arnama[17]="Wa";
        ardeskripsi[17]= "-";
        arsuara[17]= R.raw.sa_18;
        arBitmap[17]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_18);

        arnama[18]="Ha";
        ardeskripsi[18]= "-";
        arsuara[18]= R.raw.sa_19;
        arBitmap[18]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_19);

        arnama[19]="Gha";
        ardeskripsi[19]= "-";
        arsuara[19]=R.raw.sa_20;
        arBitmap[19]=BitmapFactory.decodeResource(this.getResources(),R.drawable.ak_20);
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