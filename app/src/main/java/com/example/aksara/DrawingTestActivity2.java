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

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.opencv.core.Core.minMaxLoc;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.TM_CCOEFF_NORMED;
import static org.opencv.imgproc.Imgproc.TM_CCORR_NORMED;
import static org.opencv.imgproc.Imgproc.TM_SQDIFF;
import static org.opencv.imgproc.Imgproc.TM_SQDIFF_NORMED;
import static org.opencv.imgproc.Imgproc.matchTemplate;
import static org.opencv.imgproc.Imgproc.rectangle;
import static org.opencv.imgproc.Imgproc.threshold;

public class DrawingTestActivity2 extends Activity {
    int gb[];
    String karakter[];
    String huruf;
    int jd,ke;

    private static final int threshold = 20;

    String sNama = "Fulan", sUsia = "xx",  sCatatan = "" ,nn,uu;

    db_hasil helper=null;
    String hasil,gab="",waktu;
    String[] arrGB;
    String[] arrNama;

    //myDbGB helper=null;
    private DrawingArea drawingArea;
    private Button btnCek, btnBersih;

    //=======================================
    Scalar RED = new Scalar(255, 0, 0);
    Scalar GREEN = new Scalar(0, 255, 0);
    FeatureDetector detector;
    DescriptorExtractor descriptor;
    DescriptorMatcher matcher;

    Mat descriptors2;
    MatOfKeyPoint keypoints2;

    MatOfKeyPoint [] key1;
    Mat desc1[],citra1[];
    private static final String TAG = "OCVSample::Activity";

    //==========================================

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincp);
        final ImageView myGambar;
        detector = FeatureDetector.create(FeatureDetector.ORB);
        descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        helper=new db_hasil(this);
        Intent i=getIntent();
        sNama=i.getStringExtra("nama");
        sUsia=i.getStringExtra("usia");
        Log.v("NAMAA::",sNama);
        Log.v("USIAA::",sUsia);

        File folder = new File(Environment.getExternalStorageDirectory() +
        File.separator + "TollCulator");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }

        //gambar soal;
        gb = new int[20];
        karakter = new String[20];
        getAksara();
        Random rand = new Random();
        ke = rand.nextInt(19);
       // ke=1;

        myGambar = (ImageView) findViewById(R.id.myGambar);
        myGambar.setImageResource(gb[ke]);
        huruf=karakter[ke];

        //



        jd = 5;
        arrGB = new String[jd];
        arrNama = new String[jd];
        desc1=new Mat[jd];
        citra1=new Mat[jd];
        key1=new MatOfKeyPoint[jd];

        if(huruf.equalsIgnoreCase("KA")){getdata1();}
        else if(huruf.equalsIgnoreCase("GA")){getdata2();}
        else if(huruf.equalsIgnoreCase("NGA")){getdata3();}
        else if(huruf.equalsIgnoreCase("PA")){getdata4();}
        else if(huruf.equalsIgnoreCase("BA")){getdata5();}
        else if(huruf.equalsIgnoreCase("MA")){getdata6();}
        else if(huruf.equalsIgnoreCase("TA")){getdata7();}
        else if(huruf.equalsIgnoreCase("DA")){getdata8();}
        else if(huruf.equalsIgnoreCase("NA")){getdata9();}
        else if(huruf.equalsIgnoreCase("CA")){getdata10();}
        else if(huruf.equalsIgnoreCase("JA")){getdata11();}
        else if(huruf.equalsIgnoreCase("NYA")){getdata12();}
        else if(huruf.equalsIgnoreCase("YA")){getdata13();}
        else if(huruf.equalsIgnoreCase("A")){getdata14();}
        else if(huruf.equalsIgnoreCase("LA")){getdata15();}
        else if(huruf.equalsIgnoreCase("RA")){getdata16();}
        else if(huruf.equalsIgnoreCase("SA")){getdata17();}
        else if(huruf.equalsIgnoreCase("WA")){getdata18();}
        else if(huruf.equalsIgnoreCase("HA")){getdata19();}
        else if(huruf.equalsIgnoreCase("GHA")){getdata20();}

        //================cvvb ======================================
        AssetManager assetManager = getAssets();

        for(int k=0;k<1;k++) {
            citra1[k] = new Mat();
            InputStream istr1 = null;//a.jpeg
            try {
                istr1 = assetManager.open(arrGB[k]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap1 = BitmapFactory.decodeStream(istr1);
            Utils.bitmapToMat(bitmap1, citra1[k] );
           // Imgproc.cvtColor(citra1[k] , citra1[k] , Imgproc.COLOR_RGB2GRAY);
          //  threshold( citra1[k] , citra1[k] , 100,255,THRESH_BINARY );


            citra1[k].convertTo(citra1[k] , 0);
            desc1[k] = new Mat();
            key1[k] = new MatOfKeyPoint();
            detector.detect(citra1[k] , key1[k]);
            descriptor.compute(citra1[k] , key1[k], desc1[k]);
        }


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 00);
        btnBersih = (Button) findViewById(R.id.clear_btn);
        btnCek = (Button) findViewById(R.id.check_btn);

        btnCek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vi) {
               // myGambar.setVisibility(View.INVISIBLE);
               // btnCek.setVisibility(View.INVISIBLE);
               // btnBersih.setVisibility(View.INVISIBLE);

                View v = drawingArea;
                v.setDrawingCacheEnabled(true);
                Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
                v.setDrawingCacheEnabled(false);
                try {
                    //String NF=getNF();// /storage/emulated/0
                    String NF = "da" + getNFS() + ".png";
                    String AL = Environment.getExternalStorageDirectory().toString() + "/Aksara/";
                    String ADD = AL + NF;
                    FileOutputStream fos = new FileOutputStream(new File(ADD));//System.currentTimeMillis()
                     Bitmap bmp2 = Bitmap.createScaledBitmap(bmp, 800, 600, true);
                     bmp2.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();

                    Bitmap myBitmap1 = null;
                    File imgFile1 = new File(ADD);//AL1
                    if (imgFile1.exists()) {
                        Log.d("ada1", "ADA1");
                        myBitmap1 = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
                        //Drawable d = new BitmapDrawable(getResources(), myBitmap);
                    }
                    Mat imginput = new Mat();
                    Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
                    Utils.bitmapToMat(myBitmap1, imginput);
                    Deteksi(imginput);


                    nn=sNama;
                    uu=sUsia;
                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => "+c.getTime());

                    SimpleDateFormat df = new SimpleDateFormat("dd MMMMM yyyy HH:mm:ss");
                    waktu = df.format(c.getTime());
                    // formattedDate have current date/time
                    //Toast.makeText(this, waktu, Toast.LENGTH_SHORT).show();


                    helper.insertDb(nn, uu, "Latihan Menulis",String.valueOf(hasil),waktu,gab);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
//======================================
    public void onResume() {
        super.onResume();
        initDrawingArea();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, DrawingTestActivity2.this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }

    }
    static {
        if (!OpenCVLoader.initDebug())
            Log.d("ERROR", "Unable to load OpenCV");
        else
            Log.d("SUCCESS", "OpenCV loaded");
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                   // imageMat=new Mat();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        drawingArea.trimMemory();
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


    public void Deteksi(Mat aInputFrame) {//aInputFrame=objek dari Kamera
        Mat img = aInputFrame;

        Mat templ = citra1[0];

        //Create the result matrix
        int match_method = Imgproc.TM_SQDIFF_NORMED;
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_8SC3);

        //Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);
        int type = Imgproc.THRESH_TOZERO;
        Imgproc.threshold(result, result, 0.8, 1., type);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // / Localizing the best match with minMaxLoc
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        Point matchLoc;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }

        // / Show me what you got
        rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),matchLoc.y + templ.rows()), new Scalar(255, 0, 0));

        // Save the visualized detection.
        String AL = Environment.getExternalStorageDirectory().toString() + "/Aksara/";

        imwrite(AL+"hasil.jpg", img);

        Mat image = imread("/mnt/sdcard/img_result/img_result.jpg");
        Mat android_image = Mat.zeros(image.cols(), image.rows(), CvType.CV_8SC3);

        Imgproc.cvtColor(image, android_image, Imgproc.COLOR_BGR2RGB);

        Bitmap bm = Bitmap.createBitmap(android_image.cols(),android_image.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(android_image, bm);

    //    ImageView iv = (ImageView) findViewById(R.id.image);
      //  iv.setImageBitmap(bm);

    }



public void cekSama2(Mat im1,Mat im2){




}



  Bitmap cekSama(Bitmap firstImage, Bitmap secondImage) {
    if (firstImage.getHeight() != secondImage.getHeight() || firstImage.getWidth() != secondImage.getWidth())
      Toast.makeText(this, "Images size are not same", Toast.LENGTH_LONG).show();

    boolean isSame = true;
      int total=0;
      int beda=0;
    for (int i = 0; i < firstImage.getWidth(); i++) {
      for (int j = 0; j < firstImage.getHeight(); j++){
        total=total+1;
        int pixel = firstImage.getPixel(i,j);
        int redValue = Color.red(pixel);
        int blueValue = Color.blue(pixel);
        int greenValue = Color.green(pixel);

        int pixel2 = secondImage.getPixel(i,j);
        int redValue2 = Color.red(pixel2);
        int blueValue2 = Color.blue(pixel2);
        int greenValue2 = Color.green(pixel2);

        if (Math.abs(redValue2 - redValue) + Math.abs(blueValue2 - blueValue) + Math.abs(greenValue2 - greenValue) <= threshold)
//                if (firstImage.getPixel(i,j) == secondImage.getPixel(i,j))
        {
        }
        else
        {
          beda=beda+1;
          //differentPixels.add(new Pixel(i,j));
          //secondImage.setPixel(i,j, Color.YELLOW); //for now just changing difference to yello color
          isSame = false;
        }
      }
    }
    //imgOutput.setImageBitmap(secondImage);


    Log.d("BEDA",""+beda);
    Log.d("Total",""+total);
    float persen = (beda / total) * 100;
    Log.d("PROSEN", "PROSEN: =" + String.valueOf(persen) + "#" + beda + "#" + total);


      return secondImage;
  }

    void getdata1(){
        arrGB[0] = "da1.png";
        arrGB[1] = "da2.png";
        arrGB[2] = "da3.png";
        arrGB[3] = "da4.png";
        arrGB[4] = "da5.png";


        arrNama[0] = "KA";
        arrNama[1] = "KA";
        arrNama[2] = "KA";
        arrNama[3] = "KA";
        arrNama[4] = "KA";

    }

    void getdata2(){
        arrGB[0] = "da2.png";
        arrGB[1] = "da7.png";
        arrGB[2] = "da8.png";
        arrGB[3] = "da9.png";
        arrGB[4] = "da10.png";


        arrNama[0] = "GA";
        arrNama[1] = "GA";
        arrNama[2] = "GA";
        arrNama[3] = "GA";
        arrNama[4] = "GA";

    }
    void getdata3(){
        arrGB[0] = "da3.png";
        arrGB[1] = "da12.png";
        arrGB[2] = "da13.png";
        arrGB[3] = "da14.png";
        arrGB[4] = "da15.png";


        arrNama[0] = "NGA";
        arrNama[1] = "NGA";
        arrNama[2] = "NGA";
        arrNama[3] = "NGA";
        arrNama[4] = "NGA";

    }
    void getdata4(){
        arrGB[0] = "da4.png";
        arrGB[1] = "da17.png";
        arrGB[2] = "da18.png";
        arrGB[3] = "da19.png";
        arrGB[4] = "da20.png";


        arrNama[0] = "PA";
        arrNama[1] = "PA";
        arrNama[2] = "PA";
        arrNama[3] = "PA";
        arrNama[4] = "PA";

    }
    void getdata5(){
        arrGB[0] = "da5.png";
        arrGB[1] = "da22.png";
        arrGB[2] = "da23.png";
        arrGB[3] = "da24.png";
        arrGB[4] = "da25.png";


        arrNama[0] = "BA";
        arrNama[1] = "BA";
        arrNama[2] = "BA";
        arrNama[3] = "BA";
        arrNama[4] = "BA";

    }
    void getdata6(){
        arrGB[0] = "da6.png";
        arrGB[1] = "da27.png";
        arrGB[2] = "da28.png";
        arrGB[3] = "da29.png";
        arrGB[4] = "da30.png";


        arrNama[0] = "MA";
        arrNama[1] = "MA";
        arrNama[2] = "MA";
        arrNama[3] = "MA";
        arrNama[4] = "MA";

    }
    void getdata7(){
        arrGB[0] = "da7.png";
        arrGB[1] = "da32.png";
        arrGB[2] = "da33.png";
        arrGB[3] = "da34.png";
        arrGB[4] = "da35.png";


        arrNama[0] = "TA";
        arrNama[1] = "TA";
        arrNama[2] = "TA";
        arrNama[3] = "TA";
        arrNama[4] = "TA";

    }
    void getdata8(){
        arrGB[0] = "da8.png";
        arrGB[1] = "da37.png";
        arrGB[2] = "da38.png";
        arrGB[3] = "da39.png";
        arrGB[4] = "da40.png";


        arrNama[0] = "DA";
        arrNama[1] = "DA";
        arrNama[2] = "DA";
        arrNama[3] = "DA";
        arrNama[4] = "DA";

    }
    void getdata9(){
        arrGB[0] = "da9.png";
        arrGB[1] = "da42.png";
        arrGB[2] = "da43.png";
        arrGB[3] = "da44.png";
        arrGB[4] = "da45.png";


        arrNama[0] = "NA";
        arrNama[1] = "NA";
        arrNama[2] = "NA";
        arrNama[3] = "NA";
        arrNama[4] = "NA";

    }
    void getdata10(){
        arrGB[0] = "da10.png";
        arrGB[1] = "da47.png";
        arrGB[2] = "da48.png";
        arrGB[3] = "da49.png";
        arrGB[4] = "da50.png";


        arrNama[0] = "CA";
        arrNama[1] = "CA";
        arrNama[2] = "CA";
        arrNama[3] = "CA";
        arrNama[4] = "CA";

    }

    void getdata11(){
        arrGB[0] = "da11.png";
        arrGB[1] = "da52.png";
        arrGB[2] = "da53.png";
        arrGB[3] = "da54.png";
        arrGB[4] = "da55.png";


        arrNama[0] = "JA";
        arrNama[1] = "JA";
        arrNama[2] = "JA";
        arrNama[3] = "JA";
        arrNama[4] = "JA";

    }

    void getdata12(){
        arrGB[0] = "da12.png";
        arrGB[1] = "da57.png";
        arrGB[2] = "da58.png";
        arrGB[3] = "da59.png";
        arrGB[4] = "da60.png";


        arrNama[0] = "NYA";
        arrNama[1] = "NYA";
        arrNama[2] = "NYA";
        arrNama[3] = "NYA";
        arrNama[4] = "NYA";

    }
    void getdata13(){
        arrGB[0] = "da13.png";
        arrGB[1] = "da62.png";
        arrGB[2] = "da63.png";
        arrGB[3] = "da64.png";
        arrGB[4] = "da65.png";


        arrNama[0] = "YA";
        arrNama[1] = "YA";
        arrNama[2] = "YA";
        arrNama[3] = "YA";
        arrNama[4] = "YA";

    }
    void getdata14(){
        arrGB[0] = "da14.png";
        arrGB[1] = "da67.png";
        arrGB[2] = "da68.png";
        arrGB[3] = "da69.png";
        arrGB[4] = "da70.png";


        arrNama[0] = "A";
        arrNama[1] = "A";
        arrNama[2] = "A";
        arrNama[3] = "A";
        arrNama[4] = "A";

    }
    void getdata15(){
        arrGB[0] = "da15.png";
        arrGB[1] = "da72.png";
        arrGB[2] = "da73.png";
        arrGB[3] = "da74.png";
        arrGB[4] = "da75.png";


        arrNama[0] = "LA";
        arrNama[1] = "LA";
        arrNama[2] = "LA";
        arrNama[3] = "LA";
        arrNama[4] = "LA";

    }
    void getdata16(){
        arrGB[0] = "da16.png";
        arrGB[1] = "da77.png";
        arrGB[2] = "da78.png";
        arrGB[3] = "da79.png";
        arrGB[4] = "da80.png";


        arrNama[0] = "RA";
        arrNama[1] = "RA";
        arrNama[2] = "RA";
        arrNama[3] = "RA";
        arrNama[4] = "RA";

    }
    void getdata17(){
        arrGB[0] = "da17.png";
        arrGB[1] = "da82.png";
        arrGB[2] = "da83.png";
        arrGB[3] = "da84.png";
        arrGB[4] = "da85.png";


        arrNama[0] = "SA";
        arrNama[1] = "SA";
        arrNama[2] = "SA";
        arrNama[3] = "SA";
        arrNama[4] = "SA";

    }
    void getdata18(){
        arrGB[0] = "da18.png";
        arrGB[1] = "da87.png";
        arrGB[2] = "da88.png";
        arrGB[3] = "da89.png";
        arrGB[4] = "da90.png";


        arrNama[0] = "WA";
        arrNama[1] = "WA";
        arrNama[2] = "WA";
        arrNama[3] = "WA";
        arrNama[4] = "WA";

    }
    void getdata19(){
        arrGB[0] = "da19.png";
        arrGB[1] = "da92.png";
        arrGB[2] = "da93.png";
        arrGB[3] = "da94.png";
        arrGB[4] = "da95.png";


        arrNama[0] = "HA";
        arrNama[1] = "HA";
        arrNama[2] = "HA";
        arrNama[3] = "HA";
        arrNama[4] = "HA";

    }
    void getdata20(){
        arrGB[0] = "da20.png";
        arrGB[1] = "da97.png";
        arrGB[2] = "da98.png";
        arrGB[3] = "da99.png";
        arrGB[4] = "da100.png";


        arrNama[0] = "GHA";
        arrNama[1] = "GHA";
        arrNama[2] = "GHA";
        arrNama[3] = "GHA";
        arrNama[4] = "GHA";

    }

    void getAksara() {
        gb[0] = R.drawable.da_1;
        gb[1] = R.drawable.da_2;
        gb[2] = R.drawable.da_3;
        gb[3] = R.drawable.da_4;
        gb[4] = R.drawable.da_5;
        gb[5] = R.drawable.da_6;
        gb[6] = R.drawable.da_7;
        gb[7] = R.drawable.da_8;
        gb[8] = R.drawable.da_9;
        gb[9] = R.drawable.da_10;
        gb[10] = R.drawable.da_11;
        gb[11] = R.drawable.da_12;
        gb[12] = R.drawable.da_13;
        gb[13] = R.drawable.da_14;
        gb[14] = R.drawable.da_15;
        gb[15] = R.drawable.da_16;
        gb[16] = R.drawable.da_17;
        gb[17] = R.drawable.da_18;
        gb[18] = R.drawable.da_19;
        gb[19] = R.drawable.da_20;

        karakter[0]="Ka";
        karakter[1]="Ga";
        karakter[2]="Nga";
        karakter[3]="Pa";
        karakter[4]="Ba";
        karakter[5]="Ma";
        karakter[6]="Ta";
        karakter[7]="Da";
        karakter[8]="Na";
        karakter[9]="Ca";
        karakter[10]="Ja";
        karakter[11]="Nya";
        karakter[12]="Ya";
        karakter[13]="A";
        karakter[14]="La";
        karakter[15]="Ra";
        karakter[16]="Sa";
        karakter[17]="Wa";
        karakter[18]="Ha";
        karakter[19]="Gha";

    }
    //==============================================================================

    void getdata(){
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
    }
}