package com.example.aksara;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class LatihanFragment extends Fragment {
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    String nama,usia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latihan, container, false);

        CardView mnu1 = (CardView) view.findViewById(R.id.btnlat1);
        mnu1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), InputNama.class);
                intent.putExtra("latihan","Latihan Soal");
                startActivity(intent);

            }
        });

        CardView mnu2 = (CardView) view.findViewById(R.id.btnlat2);
        mnu2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), InputNama.class);
                intent.putExtra("latihan","Latihan Menulis");
                startActivity(intent);
            }
        });

        return view;
    }



}
