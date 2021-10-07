package com.example.aksara;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.aksara.view.DrawingArea;

import org.opencv.android.Utils;
import org.opencv.core.CvException;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.threshold;

public class DrawingTestActivitybuu extends Activity {
    int gb;
    int jd;
    String[] arrGB;
    String[] arrNama;

    //myDbGB helper=null;
    private DrawingArea drawingArea;
    private Button btnCek, btnBersih;
    String karakter = "";
    double beda = 0;
    double total = 0;
    int deret=0;
    private static final int threashold = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincp);
        final ImageView myGambar;


        Intent i = this.getIntent();
        gb = R.drawable.da_1;
        karakter = "karakter";

        jd = 60;
        arrGB = new String[jd];
        arrNama = new String[jd];


        arrGB[0] = "da1.png";
        arrGB[1] = "da2.png";
        arrGB[2] = "da3.png";

        arrGB[3] = "da4.png";
        arrGB[4] = "da5.png";
        arrGB[5] = "da6.png";

        arrGB[6] = "da7.png";
        arrGB[7] = "da8.png";
        arrGB[8] = "da9.png";

        arrGB[9] = "da10.png";
        arrGB[10] = "da11.png";
        arrGB[11] = "da12.png";

        arrGB[12] = "da13.png";
        arrGB[13] = "da14.png";
        arrGB[14] = "da15.png";

        arrGB[15] = "da16.png";
        arrGB[16] = "da17.png";
        arrGB[17] = "da18.png";

        arrGB[18] = "da19.png";
        arrGB[19] = "da20.png";
        arrGB[20] = "da21.png";

        arrGB[21] = "da22.png";
        arrGB[22] = "da23.png";
        arrGB[23] = "da24.png";

        arrGB[24] = "da25.png";
        arrGB[25] = "da26.png";
        arrGB[26] = "da27.png";

        arrGB[27] = "da28.png";
        arrGB[28] = "da29.png";
        arrGB[29] = "da30.png";

        arrGB[30] = "da31.png";
        arrGB[31] = "da32.png";
        arrGB[32] = "da33.png";

        arrGB[33] = "da34.png";
        arrGB[34] = "da35.png";
        arrGB[35] = "da36.png";

        arrGB[36] = "da37.png";
        arrGB[37] = "da38.png";
        arrGB[38] = "da39.png";

        arrGB[39] = "da40.png";
        arrGB[40] = "da41.png";
        arrGB[41] = "da42.png";

        arrGB[42] = "da43.png";
        arrGB[43] = "da44.png";
        arrGB[44] = "da45.png";

        arrGB[45] = "da46.png";
        arrGB[46] = "da47.png";
        arrGB[47] = "da48.png";

        arrGB[48] = "da49.png";
        arrGB[49] = "da50.png";
        arrGB[50] = "da51.png";

        arrGB[51] = "da52.png";
        arrGB[52] = "da53.png";
        arrGB[53] = "da54.png";

        arrGB[54] = "da55.png";
        arrGB[55] = "da56.png";
        arrGB[56] = "da57.png";

        arrGB[57] = "da58.png";
        arrGB[58] = "da59.png";
        arrGB[59] = "da60.png";


        arrNama[0] = "KA";
        arrNama[1] = "KA";
        arrNama[2] = "KA";

        arrNama[3] = "GA";
        arrNama[4] = "GA";
        arrNama[5] = "GA";

        arrNama[6] = "NGA";
        arrNama[7] = "NGA";
        arrNama[8] = "NGA";

        arrNama[9] = "PA";
        arrNama[10] = "PA";
        arrNama[11] = "PA";

        arrNama[12] = "BA";
        arrNama[13] = "BA";
        arrNama[14] = "BA";

        arrNama[15] = "MA";
        arrNama[16] = "MA";
        arrNama[17] = "MA";

        arrNama[18] = "TA";
        arrNama[19] = "TA";
        arrNama[20] = "TA";

        arrNama[21] = "DA";
        arrNama[22] = "DA";
        arrNama[23] = "DA";

        arrNama[24] = "NA";
        arrNama[25] = "NA";
        arrNama[26] = "NA";

        arrNama[27] = "CA";
        arrNama[28] = "CA";
        arrNama[29] = "CA";

        arrNama[30] = "JA";
        arrNama[31] = "JA";
        arrNama[32] = "JA";

        arrNama[33] = "NYA";
        arrNama[34] = "NYA";
        arrNama[35] = "NYA";

        arrNama[36] = "YA";
        arrNama[37] = "YA";
        arrNama[38] = "YA";

        arrNama[39] = "A";
        arrNama[40] = "A";
        arrNama[41] = "A";

        arrNama[42] = "LA";
        arrNama[43] = "LA";
        arrNama[44] = "LA";

        arrNama[45] = "RA";
        arrNama[46] = "RA";
        arrNama[47] = "RA";

        arrNama[48] = "SA";
        arrNama[49] = "SA";
        arrNama[50] = "SA";

        arrNama[51] = "WA";
        arrNama[52] = "WA";
        arrNama[53] = "WA";

        arrNama[54] = "HA";
        arrNama[55] = "HA";
        arrNama[56] = "HA";

        arrNama[57] = "GHA";
        arrNama[58] = "GHA";
        arrNama[59] = "GHA";


        myGambar = (ImageView) findViewById(R.id.myGambar);
        myGambar.setImageResource(gb);

        //cekSama
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 00);

        btnBersih = (Button) findViewById(R.id.clear_btn);
        btnCek = (Button) findViewById(R.id.check_btn);

        btnCek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vi) {
                deret=deret+1;
               // myGambar.setVisibility(View.INVISIBLE);
               // btnCek.setVisibility(View.INVISIBLE);
               // btnBersih.setVisibility(View.INVISIBLE);
                View v = drawingArea;
                v.setDrawingCacheEnabled(true);
                Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
                v.setDrawingCacheEnabled(false);
                try {
                    //String NF=getNF();// /storage/emulated/0
                    String NF = "da" + String.valueOf(deret)+ ".png";
                    String AL = Environment.getExternalStorageDirectory().toString() + "/Aksara/";
                    Log.d("Baca", AL);
                    String ADD = AL + NF;
                    Log.d("Baca2", ADD);
                    FileOutputStream fos = new FileOutputStream(new File(ADD));//System.currentTimeMillis()
                    Bitmap bmp2 = Bitmap.createScaledBitmap(bmp, 800, 600, true);
                    bmp2.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                    fos.flush();
                    fos.close();


                    //=================================
//
//                    Random r=new Random();
//                     double bobot=r.nextDouble();
//                   //  helper.insertDb(AL,NF,String.valueOf(bobot),"???",getWaktu(),karakter);
//                     mulai(ADD, NF);
//                     tesCompare();
                    //=================================

//finish();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


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

    /**
     * on click, see layout.xml
     */
    public void onQuillSelected(View view) {
        drawingArea.onQuillSelected();
    }

    /**
     * on click, see layout.xml
     */
    public void onMarkerSelected(View view) {
        drawingArea.onMarkerSelected();
    }

    /**
     * on click, see layout.xml
     */
    public void onClearSelected(View view) {
        drawingArea.onClearSelected();
    }


    /**
     * on click, see layout.xml
     */
    public void onMultistrokeSwitchToggled(View view) {
        Switch toggle = (Switch) view;
        drawingArea.onMultistrokeSwitchToggled(toggle.isChecked());
    }


    String getWaktu() {
        Calendar cal = Calendar.getInstance();
        int jam = cal.get(Calendar.HOUR);
        int menit = cal.get(Calendar.MINUTE);
        int detik = cal.get(Calendar.SECOND);

        int tgl = cal.get(Calendar.DATE);
        int bln = cal.get(Calendar.MONTH) + 1;
        int thn = cal.get(Calendar.YEAR);

        String stgl = String.valueOf(tgl) + "-" + String.valueOf(bln) + "-" + String.valueOf(thn);
        String sjam = String.valueOf(jam) + ":" + String.valueOf(menit) + ":" + String.valueOf(detik);
        String gb = stgl + " " + sjam + " WIB";
        return gb;
    }

    String getNFS() {
        Calendar cal = Calendar.getInstance();
        int jam = cal.get(Calendar.HOUR);
        int menit = cal.get(Calendar.MINUTE);
        int detik = cal.get(Calendar.SECOND);

        int tgl = cal.get(Calendar.DATE);
        int bln = cal.get(Calendar.MONTH) + 1;
        int thn = cal.get(Calendar.YEAR);

        String stgl = String.valueOf(tgl) + "" + String.valueOf(bln) + "" + String.valueOf(thn);
        String sjam = String.valueOf(jam) + "" + String.valueOf(menit) + "" + String.valueOf(detik);
        String gb = stgl + "" + sjam;
        return gb;
    }

    Bitmap cekSama(Bitmap firstImage, Bitmap secondImage) {

        Log.d("firstImage", firstImage + "#" );
        Log.d("secondImage", secondImage + "#" );

        if (firstImage.getHeight() != secondImage.getHeight() || firstImage.getWidth() != secondImage.getWidth())
            Toast.makeText(this, "Images size are not same", Toast.LENGTH_LONG).show();

        boolean isSame = true;

        for (int i = 0; i < firstImage.getWidth(); i++) {
            for (int j = 0; j < firstImage.getHeight(); j++) {
                total = total + 1;
                int pixel = firstImage.getPixel(i, j);
                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);

                int pixel2 = secondImage.getPixel(i, j);
                int redValue2 = Color.red(pixel2);
                int blueValue2 = Color.blue(pixel2);
                int greenValue2 = Color.green(pixel2);

                if (Math.abs(redValue2 - redValue) + Math.abs(blueValue2 - blueValue) + Math.abs(greenValue2 - greenValue) <= threashold)
               if (firstImage.getPixel(i,j) == secondImage.getPixel(i,j))
                {
                } else {
                    beda = beda + 1;
                   // differentPixels.add(new Pixel(i,j));
                   // secondImage.setPixel(i,j, Color.YELLOW); //for now just changing difference to yello color
                    isSame = false;
                }
            }
        }
        //imgOutput.setImageBitmap(secondImage);


        Log.d("BEDA", "" + beda);
        Log.d("SOMAY", "" + total);
//    Toast.makeText(getBaseContext(),"BEDA: " + beda+"#", Toast.LENGTH_LONG).show();
//    Toast.makeText(getBaseContext(),"SOMAY: " + total+"#", Toast.LENGTH_LONG).show();
        return secondImage;
    }

    void mulai(String AL1, String NF1) throws IOException {
        Log.d("Baca", AL1 + "#" + NF1);
        double nmin = 10000000;
        int index = 0;
         Bitmap myBitmap1 = null;
        File imgFile1 = new File(AL1);//AL1
        if (imgFile1.exists()) {
            Log.d("ada1", "ADA1");
            myBitmap1 = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
            //Drawable d = new BitmapDrawable(getResources(), myBitmap);

        }


        for (int j = 0; j < jd; j++) {//jd
            total = 0;
            beda = 0;
            String AL2 = arrGB[j];//"/storage/emulated/0/BahasaMandarin/BM8820184328.png";
            Log.d("Compare", AL1 + "#" + AL2);
            Bitmap myBitmap2 = null;
            Log.d("ada2", "ADA2 =>"+String.valueOf(j));
            AssetManager assetManager = getAssets();
            InputStream istr1 = assetManager.open(arrGB[j]);
            myBitmap2 = BitmapFactory.decodeStream(istr1);

            // Bitmap myBitmap = cekSama(myBitmap1, myBitmap2);
            cekSama(myBitmap1, myBitmap2);
            double persen = (beda / total) * 100;
            Log.d("PROSEN", "PROSEN: " + j + "=" + persen + "#" + beda + "#" + total);

            if (persen < nmin) {
                nmin = persen;
                index = j;
            }
        }//for

//        helper.insertDb(AL1, NF1, String.valueOf(nmin), arrNama[index], getWaktu(), karakter);
//
//        Intent i = new Intent(DrawingTestActivity.this, listDBGB.class);
//        startActivity(i);
//        finish();

    }


//    Bitmap cekSama2(Bitmap firstImage, Bitmap secondImage) {
//        if (firstImage.getHeight() != secondImage.getHeight() || firstImage.getWidth() != secondImage.getWidth())
//            Toast.makeText(this, "Images size are not same", Toast.LENGTH_LONG).show();
//
//        boolean isSame = true;
//
//        for (int i = 0; i < firstImage.getWidth(); i++) {
//            for (int j = 0; j < firstImage.getHeight(); j++) {
//                total = total + 1;
//                int pixel = firstImage.getPixel(i, j);
//                int redValue = Color.red(pixel);
//                int blueValue = Color.blue(pixel);
//                int greenValue = Color.green(pixel);
//
//                int pixel2 = secondImage.getPixel(i, j);
//                int redValue2 = Color.red(pixel2);
//                int blueValue2 = Color.blue(pixel2);
//                int greenValue2 = Color.green(pixel2);
//
//                if (Math.abs(redValue2 - redValue) + Math.abs(blueValue2 - blueValue) + Math.abs(greenValue2 - greenValue) <= threashold)
////                if (firstImage.getPixel(i,j) == secondImage.getPixel(i,j))
//                {
//                } else {
//                    beda = beda + 1;
//                    //differentPixels.add(new Pixel(i,j));
//                    //secondImage.setPixel(i,j, Color.YELLOW); //for now just changing difference to yello color
//                    isSame = false;
//                }
//            }
//        }
//        //imgOutput.setImageBitmap(secondImage);
//
//
//        Log.d("BEDA", "" + beda);
//        Log.d("ZZZZZ", "" + total);
//        Toast.makeText(getBaseContext(), "BEDA: " + beda + "#", Toast.LENGTH_LONG).show();
//        Toast.makeText(getBaseContext(), "ZZZZZ: " + total + "#", Toast.LENGTH_LONG).show();
//        return secondImage;
//    }





//public void Deteksi2(Mat aInputFrame) {//aInputFrame=objek dari Kamera
//    Imgproc.cvtColor(aInputFrame, aInputFrame, Imgproc.COLOR_RGB2GRAY);
//    threshold( aInputFrame , aInputFrame , 100,255,THRESH_BINARY );
//
//    descriptors2 = new Mat();
//    keypoints2 = new MatOfKeyPoint();
//    detector.detect(aInputFrame, keypoints2);
//    descriptor.compute(aInputFrame, keypoints2, descriptors2);
//
//    Log.d("BATAS", "=================================================================================================================");
//    Log.d("HURUF", karakter[ke]);
//
//    int jumbest=0;
//    for(int k=0;k<jd;k++) {
//        // Matching
//        MatOfDMatch matches = new MatOfDMatch();
//        if (citra1[k].type() == aInputFrame.type()) {
//            matcher.match(desc1[k], descriptors2, matches);
//        } else {
//            // return aInputFrame;
//        }
//
//        List<DMatch> matchesList = matches.toList();
//
//        Double max_dist = 0.0;
//        Double min_dist = 100.0;
//
//        for (int i = 0; i < matchesList.size(); i++) {
//            Double dist = (double) matchesList.get(i).distance;
//            if (dist < min_dist)
//                min_dist = dist;
//            if (dist > max_dist)
//                max_dist = dist;
//        }
//        Log.d("Log!", "Template ke- "+k);
//        Log.d("Log!", "Maxdist :" + max_dist);
//        Log.d("Log!", "Mindist :" + min_dist);
//        Log.d("Log!", "1.5 * min_dist :" +  (1.5 * min_dist));
//
//        LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
//        int mm=0;
//        for (int i = 0; i < matchesList.size(); i++) {
//            //  Log.d("LOG1",(" :  matchesList.get(i).distance :" + matchesList.get(i).distance));
//            //Log.d("LOG2",(" :  1.5 * min_dist :" + min_dist));
//            if (matchesList.get(i).distance <= (1.5 * min_dist))
//                good_matches.addLast(matchesList.get(i));
//            mm++;
//        }
//        Log.d("Log!", "Matches List :" + matchesList.size());
//        Log.d("Log!", "Good Matches :" + good_matches.size());
//        Log.d("Log!", "mm :" + mm);
//        Log.d("Log!", "Persentase 1 :" +  (good_matches.size()/ matchesList.size())*100+" %");
//
//        MatOfDMatch goodMatches = new MatOfDMatch();
//        goodMatches.fromList(good_matches);
//        List<DMatch> bestMatches=goodMatches.toList();
//        Collections.sort(bestMatches,new Comparator<DMatch>() {
//            @Override
//            public int compare(DMatch o1, DMatch o2) {
//                if(o1.distance<o2.distance)
//                    return -1;
//                if(o1.distance>o2.distance)
//                    return 1;
//                else {
//                    //  Log.d("LOG!",("Equal i value: "));
//                    return 0;
//                }
//            }
//        });
//
//
//        //save img==========================================================
//        Mat imgBest=new Mat();
//        MatOfKeyPoint keyBest=null;
//
//        imgBest=citra1[k];
//        keyBest=key1[k];
//        hasil=arrNama[k];
//
//        Mat outputImg = new Mat();
//
//
//        MatOfByte drawnMatches = new MatOfByte();
//        if (aInputFrame.empty() || aInputFrame.cols() < 1 || aInputFrame.rows() < 1) {
//        }
//        Features2d.drawMatches(imgBest, keyBest, aInputFrame, keypoints2, goodMatches, outputImg, GREEN, RED, drawnMatches, Features2d.NOT_DRAW_SINGLE_POINTS);
//        Imgproc.resize(outputImg, outputImg, aInputFrame.size());
//
//        Bitmap bmp = null;
//        try {
//            bmp = Bitmap.createBitmap(outputImg.cols(), outputImg.rows(), Bitmap.Config.ARGB_8888);
//            Utils.matToBitmap(outputImg, bmp);
//        } catch (CvException e) {
//            Log.d(TAG, e.getMessage());
//        }
//
//        outputImg.release();
//
//        try {
//            String NF = "aksara" + k + ".png";
//            String AL = Environment.getExternalStorageDirectory().toString() + "/Aksara/";
//            String ADD = AL + NF;
//            FileOutputStream fos = new FileOutputStream(new File(ADD));//System.currentTimeMillis()
//            Bitmap bmp2 = Bitmap.createScaledBitmap(bmp, 500, 500, true);
//            bmp2.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();
//        }catch (Exception ee){}
//
//        //save img==========================================================
//
//        jumbest=jumbest+good_matches.size();
//        Log.d("Log!", "Good :" + goodMatches.size());
//        Log.d("Log!", "Best :" + bestMatches.size());
//        // Log.d("Log!", "Persentase 1 :" +  (bestMatches.size()/goodMatches.size()*100+" %");
//        Log.d("BATAS", "=================================================================================================================");
//
//    }
//    Log.d("BATAS", "######################################################################################################################");
//    Log.d("Log!", "Jumbest :" + jumbest);
//    Log.d("BATAS", "######################################################################################################################");
//
////        List<DMatch> matchesListOut = goodMatches.toList();
////        JM = matchesListOut.size();
////        List<DMatch>  listBest=null;
////        Mat imgBest=new Mat();
////        MatOfKeyPoint keyBest=null;
////
////        listBest=matchesListOut;
////        imgBest=citra1[indexKe];
////        keyBest=key1[indexKe];
////        hasil=arrNama[indexKe];
////
////        //=================================================================================================LAST
////        int mm=0;
////        LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
////        for (int i = 0; i < JM; i++) {
////            if (listBest.get(i).distance <= (1.5 * min_dist)) {//1.5 * min_dist
////                good_matches.addLast(listBest.get(i));
////                mm++;
////            }
////
////        }
//}

    void tesCompare() {
        String AL1 = arrGB[0];//"/storage/emulated/0/BahasaMandarin/BM8820184248.png";
        String AL2 = arrGB[1];//"/storage/emulated/0/BahasaMandarin/BM8820184328.png";

        Bitmap myBitmap1 = null;
        Bitmap myBitmap2 = null;
        File imgFile1 = new File(AL1);
        File imgFile2 = new File(AL2);

        if (imgFile1.exists()) {
            myBitmap1 = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
            //Drawable d = new BitmapDrawable(getResources(), myBitmap);

        }

        if (imgFile2.exists()) {
            myBitmap2 = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
            //Drawable d = new BitmapDrawable(getResources(), myBitmap);
        }


        cekSama(myBitmap1, myBitmap2);
        double persen = (beda / total) * 100;
        Log.d("PROSEN", "" + persen);
        Toast.makeText(getBaseContext(), "PROSEN: " + persen + "#", Toast.LENGTH_LONG).show();
//    ImageView myImage = (ImageView) findViewById(R.id.myGambar);
//
//    myImage.setImageBitmap(myBitmap);


    }


    public static boolean compareImages(Bitmap bitmap1, Bitmap bitmap2) {
        if (bitmap1.getWidth() != bitmap2.getWidth() ||
                bitmap1.getHeight() != bitmap2.getHeight()) {
            return false;
        }

        for (int y = 0; y < bitmap1.getHeight(); y++) {
            for (int x = 0; x < bitmap1.getWidth(); x++) {
                if (bitmap1.getPixel(x, y) != bitmap2.getPixel(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }


    private static void persistImage(Bitmap bitmap, String name) {
        File imageFile = new File(name);

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            //Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }
    }


    void findDifference(Bitmap firstImage, Bitmap secondImage) {
        if (firstImage.getHeight() != secondImage.getHeight() || firstImage.getWidth() != secondImage.getWidth())
            Toast.makeText(this, "Images size are not same", Toast.LENGTH_LONG).show();

        boolean isSame = true;

        for (int i = 0; i < firstImage.getWidth(); i++) {
            for (int j = 0; j < firstImage.getHeight(); j++) {
                int pixel = firstImage.getPixel(i, j);
                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);

                int pixel2 = secondImage.getPixel(i, j);
                int redValue2 = Color.red(pixel2);
                int blueValue2 = Color.blue(pixel2);
                int greenValue2 = Color.green(pixel2);

                if (Math.abs(redValue2 - redValue) + Math.abs(blueValue2 - blueValue) + Math.abs(greenValue2 - greenValue) <= threashold)
//                if (firstImage.getPixel(i,j) == secondImage.getPixel(i,j))
                {
                } else {
//          differentPixels.add(new Pixel(i,j));
//          secondImage.setPixel(i,j, Color.YELLOW); //for now just changing difference to yello color
                    isSame = false;
                }
            }
        }
        //imgOutput.setImageBitmap(secondImage);
    }
}