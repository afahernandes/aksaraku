package com.example.aksara;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InputNama extends AppCompatActivity {
String nama,usia, latihan;
EditText txtnama,txtusia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custompopup_nama);

        Intent io=getIntent();
        latihan=io.getStringExtra("latihan");

        Button btntutup = (Button) findViewById(R.id.btnmulai);
         txtnama =(EditText) findViewById(R.id.txtnama);
         txtusia = (EditText)findViewById(R.id.txtusia);

        btntutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama=txtnama.getText().toString();
                usia=txtusia.getText().toString();
             if(latihan.equalsIgnoreCase("Latihan Soal")) {
                 Intent intent = new Intent(InputNama.this, soalsoal.class);intent.putExtra("nama",nama);
                 intent.putExtra("usia",usia);
                 Log.v("NAMAA1::",nama);
                 Log.v("USIAA1::",usia);
                 finish();
                 startActivity(intent);
             }else {
                Intent intent = new Intent(InputNama.this, DrawingTestActivity.class);intent.putExtra("nama",nama);
                intent.putExtra("usia",usia);
                Log.v("NAMAA1::",nama);
                Log.v("USIAA1::",usia);
                finish();
                startActivity(intent);
             }
            }
        });
    }



}
