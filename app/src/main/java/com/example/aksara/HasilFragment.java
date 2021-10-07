package com.example.aksara;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HasilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hasil, container, false);

        CardView mnu1 = (CardView) view.findViewById(R.id.btnhsl1);
        mnu1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), listdataDB.class);
                intent.putExtra("jenis","Latihan Soal");
                startActivity(intent);
            }
        });

        CardView mnu2 = (CardView) view.findViewById(R.id.btnhsl2);
        mnu2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), listdataDB.class);
                intent.putExtra("jenis","Latihan Menulis");
                startActivity(intent);
            }
        });
        return view;
    }


}