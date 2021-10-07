package com.example.aksara;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class Deteksi extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{


    String gab="";
    int jd=10;
    Button btnCek;
    String[]arDaun;
    String[]arNama;

String hasildaun="";
    private static final String TAG = "OCVSample::Activity";
    private static final int REQUEST_PERMISSION = 100;
    private int w, h;
    private CameraBridgeViewBase mOpenCvCameraView;
    TextView tvName;
    Scalar RED = new Scalar(255, 0, 0);
    Scalar GREEN = new Scalar(0, 255, 0);
    FeatureDetector detector;
    DescriptorExtractor descriptor;
    DescriptorMatcher matcher;

    Mat descriptors2;
    MatOfKeyPoint keypoints2;

    MatOfKeyPoint [] key1;
    Mat desc1[],citra1[];

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
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                    // mOpenCvCameraView.enableView();
                    try {
                        initializeOpenCVDependencies();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    private void initializeOpenCVDependencies() throws IOException {
        mOpenCvCameraView.enableView();
        detector = FeatureDetector.create(FeatureDetector.ORB);
        descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

//        Button btnCek=(Button)findViewById(R.id.btnCek);
//        btnCek.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                Intent i = new Intent(Deteksi.this, HasilKlasifikasi.class);
//                i.putExtra("hasildaun", hasildaun);
//                startActivity(i);
//            }});



        arDaun=new String[jd];
        arNama=new String[jd];
        desc1=new Mat[jd];
        citra1=new Mat[jd];
        key1=new MatOfKeyPoint[jd];

        arDaun[0]="daunpepaya4.jpg";
        arDaun[1]="daunjambu1.jpg";
        arDaun[2]="daunjarak1.jpg";
        arDaun[3]="daunjarak4.jpg";
        arDaun[4]="daunjeruk2.jpg";
        arDaun[5]="daunsingkong2.jpg";
        arDaun[6]="daunjambu6.jpg";
        arDaun[7]="daunjeruk7.jpg";
        arDaun[8]="daunsingkong6.jpg";
        arDaun[9]="daunpepaya5.jpg";

        arNama[0]="daunpepaya";
        arNama[1]="daunjambu";
        arNama[2]="daunjarak";
        arNama[3]="daunjarak";
        arNama[4]="daunjeruk";
        arNama[5]="daunsingkong";
        arNama[6]="daunjambu";
        arNama[7]="daunjeruk";
        arNama[8]="daunsingkong";
        arNama[9]="daunpepaya";

        AssetManager assetManager = getAssets();

        for(int k=0;k<jd;k++) {
            citra1[k] = new Mat();
            InputStream istr1 = assetManager.open(arDaun[k]);//a.jpeg
            Bitmap bitmap1 = BitmapFactory.decodeStream(istr1);
            Utils.bitmapToMat(bitmap1, citra1[k] );
            Imgproc.cvtColor(citra1[k] , citra1[k] , Imgproc.COLOR_RGB2GRAY);


            citra1[k] .convertTo(citra1[k] , 0);
            desc1[k] = new Mat();
            key1[k] = new MatOfKeyPoint();
            detector.detect(citra1[k] , key1[k]);
            descriptor.compute(citra1[k] , key1[k], desc1[k]);
        }
        //============================================1
    }
    public Deteksi() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //setContentView(R.layout.activity_deteksi);


//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
//        }
//        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.deteksi);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, Deteksi.this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }

        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }
    @Override
    public void onCameraViewStarted(int width, int height) {
        w = width;
        h = height;
    }

    @Override
    public void onCameraViewStopped() {

    }

    public Mat recognize(Mat aInputFrame) {//aInputFrame=objek dari Kamera
        double nmin = 10000000;
        int indexKe=0;

        Imgproc.cvtColor(aInputFrame, aInputFrame, Imgproc.COLOR_RGB2GRAY);
        descriptors2 = new Mat();
        keypoints2 = new MatOfKeyPoint();

        detector.detect(aInputFrame, keypoints2);
        descriptor.compute(aInputFrame, keypoints2, descriptors2);


        MatOfDMatch matches = null;
        Double max_dist = 0.0;
        Double min_dist = 100.0;
        int JM = 0;

        matches = new MatOfDMatch();
        for(int k=0;k<jd;k++){
        if (citra1[k].type() == aInputFrame.type()) {
            try {
                matcher.match(desc1[k], descriptors2, matches);

                List<DMatch> matchesList1 = matches.toList();

                max_dist = 0.0;
                min_dist = 100.0;

                JM = matchesList1.size();
                for (int i = 0; i < JM; i++) {
                    Double dist = (double) matchesList1.get(i).distance;
                    if (dist < min_dist)
                        min_dist = dist;
                    if (dist > max_dist)
                        max_dist = dist;
                }
                Log.d("MAXMIN15", "@DAUN1=" + max_dist + "@" + min_dist + "@" + (1.2 * min_dist));
                if (min_dist < nmin) {
                    indexKe = k;
                    nmin = min_dist;
                }

            } catch (Exception ee) {
            }
        } else {
            return aInputFrame;
        }

    }//loop


        //=================================================================================3
        matches = new MatOfDMatch();
        if (citra1[indexKe].type() == aInputFrame.type()) {
            try {
                matcher.match(desc1[indexKe], descriptors2, matches);
            } catch (Exception ee) {
            }

        } else {
            return aInputFrame;
        }

        List<DMatch> matchesListOut = matches.toList();
        JM = matchesListOut.size();
        List<DMatch>  listBest=null;
        Mat imgBest=new Mat();
        MatOfKeyPoint keyBest=null;

            listBest=matchesListOut;
            imgBest=citra1[indexKe];
            keyBest=key1[indexKe];
        hasildaun=arNama[indexKe];

        //=================================================================================================LAST
        int mm=0;
        LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
        for (int i = 0; i < JM; i++) {
            if (listBest.get(i).distance <= (1.2 * min_dist)) {//1.5 * min_dist
                good_matches.addLast(listBest.get(i));
                mm++;
            }
        }
        gab=gab+String.valueOf(mm)+"#";
        Log.d("GAB","#"+gab+"+++++++++++++++++++++++++++++++++++++++++++++");

        MatOfDMatch goodMatches = new MatOfDMatch();
        goodMatches.fromList(good_matches);
        Mat outputImg = new Mat();


        MatOfByte drawnMatches = new MatOfByte();
        if (aInputFrame.empty() || aInputFrame.cols() < 1 || aInputFrame.rows() < 1) {
            return aInputFrame;
        }
        Features2d.drawMatches(imgBest, keyBest, aInputFrame, keypoints2, goodMatches, outputImg, GREEN, RED, drawnMatches, Features2d.NOT_DRAW_SINGLE_POINTS);
        Imgproc.resize(outputImg, outputImg, aInputFrame.size());


        return outputImg;
    }


        @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return recognize(inputFrame.rgba());
    }
}
