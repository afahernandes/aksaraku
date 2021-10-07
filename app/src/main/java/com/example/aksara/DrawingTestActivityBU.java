package com.example.aksara;//

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;

import com.example.aksara.view.DrawingArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class DrawingTestActivityBU extends Activity {

 // myDbGB helper=null;
  private DrawingArea drawingArea;
  private Button btnCek, btnBersih;
  private final int threashold=20;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.maincp);
 //   helper=new myDbGB(this);
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},00);

    btnBersih = (Button) findViewById(R.id.clear_btn);
    btnCek = (Button) findViewById(R.id.check_btn);

    btnCek.setOnClickListener(new View.OnClickListener() {
      public void onClick(View vi)
      {
        btnCek.setVisibility(View.INVISIBLE);
        btnBersih.setVisibility(View.INVISIBLE);

        View v = getWindow().getDecorView().getRootView();
        v.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        try {
          //String NF=getNF();// /storage/emulated/0
          String NF="BM"+ getNFS() + ".png";
          String AL=Environment.getExternalStorageDirectory().toString()+"/BahasaMandarin/";
          Log.d("Baca",AL);
          String ADD=AL+NF;
          Log.d("Baca2",ADD);
          FileOutputStream fos = new FileOutputStream(new File(ADD));//System.currentTimeMillis()

          bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
          fos.flush();
          fos.close();
          //=================================


          //=================================
          Random r=new Random();
          double bobot=r.nextDouble();
   //       helper.insertDb(AL,NF,String.valueOf(bobot),"Meragukan",getWaktu(),"Cek Huruf A");


        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
//       Intent i = new Intent (DrawingTestActivityBU.this, listDBGB.class);
//      startActivity(i);
//      finish();

      }
    });


  }

  @Override
  public void onPause() {
    super.onPause();
    drawingArea.trimMemory();
  }

  @Override
  public void onResume() {
    super.onResume();
    initDrawingArea();
  }

  private void initDrawingArea() {
    if (drawingArea == null) {
      drawingArea = (DrawingArea) findViewById(R.id.drawing_area);
      drawingArea.initTrailDrawer();
      drawingArea.onMarkerSelected();
    }
  }

  /** on click, see layout.xml */
  public void onQuillSelected(View view) {
    drawingArea.onQuillSelected();
  }

  /** on click, see layout.xml */
  public void onMarkerSelected(View view) {
    drawingArea.onMarkerSelected();
  }

  /** on click, see layout.xml */
  public void onClearSelected(View view) {
    drawingArea.onClearSelected();
  }


  /** on click, see layout.xml */
  public void onMultistrokeSwitchToggled(View view) {
    Switch toggle = (Switch) view;
    drawingArea.onMultistrokeSwitchToggled(toggle.isChecked());
  }


  String getWaktu(){
    Calendar cal = Calendar.getInstance();
    int jam = cal.get(Calendar.HOUR);
    int menit= cal.get(Calendar.MINUTE);
    int detik= cal.get(Calendar.SECOND);

    int tgl= cal.get(Calendar.DATE);
    int bln= cal.get(Calendar.MONTH)+1;
    int thn= cal.get(Calendar.YEAR);

    String stgl=String.valueOf(tgl)+"-"+String.valueOf(bln)+"-"+String.valueOf(thn);
    String sjam=String.valueOf(jam)+":"+String.valueOf(menit)+":"+String.valueOf(detik);
    String gb=stgl+" "+sjam+" WIB";
    return gb;
  }

  String getNFS(){
    Calendar cal = Calendar.getInstance();
    int jam = cal.get(Calendar.HOUR);
    int menit= cal.get(Calendar.MINUTE);
    int detik= cal.get(Calendar.SECOND);

    int tgl= cal.get(Calendar.DATE);
    int bln= cal.get(Calendar.MONTH)+1;
    int thn= cal.get(Calendar.YEAR);

    String stgl=String.valueOf(tgl)+"-"+String.valueOf(bln)+"-"+String.valueOf(thn);
    String sjam=String.valueOf(jam)+":"+String.valueOf(menit)+":"+String.valueOf(detik);
    String gb=stgl+""+sjam;
    return gb;
  }


//
//  public static boolean compareImages(Bitmap bitmap1, Bitmap bitmap2) {
//    if (bitmap1.getWidth() != bitmap2.getWidth() ||
//            bitmap1.getHeight() != bitmap2.getHeight()) {
//      return false;
//    }
//
//    for (int y = 0; y < bitmap1.getHeight(); y++) {
//      for (int x = 0; x < bitmap1.getWidth(); x++) {
//        if (bitmap1.getPixel(x, y) != bitmap2.getPixel(x, y)) {
//          return false;
//        }
//      }
//    }
//
//    return true;
//  }
//
//
//  private static void persistImage(Bitmap bitmap, String name) {
//    File imageFile = new File(name);
//
//    OutputStream os;
//    try {
//      os = new FileOutputStream(imageFile);
//      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
//      os.flush();
//      os.close();
//    } catch (Exception e) {
//      //Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
//    }
//  }
//
//
//

  void findDifference(Bitmap firstImage, Bitmap secondImage)
  {
    if (firstImage.getHeight() != secondImage.getHeight() || firstImage.getWidth() != secondImage.getWidth())
      Toast.makeText(this, "Images size are not same", Toast.LENGTH_LONG).show();

    boolean isSame = true;

    for (int i = 0; i < firstImage.getWidth(); i++)
    {
      for (int j = 0; j < firstImage.getHeight(); j++)
      {
        int pixel = firstImage.getPixel(i,j);
        int redValue = Color.red(pixel);
        int blueValue = Color.blue(pixel);
        int greenValue = Color.green(pixel);

        int pixel2 = secondImage.getPixel(i,j);
        int redValue2 = Color.red(pixel2);
        int blueValue2 = Color.blue(pixel2);
        int greenValue2 = Color.green(pixel2);

        if (Math.abs(redValue2 - redValue) + Math.abs(blueValue2 - blueValue) + Math.abs(greenValue2 - greenValue) <= threashold)
//                if (firstImage.getPixel(i,j) == secondImage.getPixel(i,j))
        {
        }
        else
        {
//          differentPixels.add(new Pixel(i,j));
//          secondImage.setPixel(i,j, Color.YELLOW); //for now just changing difference to yello color
          isSame = false;
        }
      }
    }
    //imgOutput.setImageBitmap(secondImage);
  }

}
