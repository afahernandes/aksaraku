package com.example.aksara;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
Loading();
    }


    public void Loading(){
        new Thread() {
            public void run() {
                try{Thread.sleep(5000);}
                catch (Exception e) {}
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.finish();
                startActivity(i);
            } }.start();
    }
}
