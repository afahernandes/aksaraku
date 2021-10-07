package com.example.aksara;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomeFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        TextView txtMarquee=(TextView)view.findViewById(R.id.txtMarquee);
        txtMarquee.setSelected(true);
        String kata="Aplikasi pengenalan aksara Lampung berbasis Android menggunakan metode Template Matching... ";
        String kalimat=String.format("%1$s", TextUtils.htmlEncode(kata));
        txtMarquee.setText(Html.fromHtml(kalimat+kalimat+kalimat));


        CardView mnu1 = (CardView) view.findViewById(R.id.mnu1);
        mnu1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               Intent intent = new Intent(getContext(), DaftarAksara.class);
               startActivity(intent);
            }
        });

        CardView mnu2 = (CardView) view.findViewById(R.id.mnu2);
        mnu2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), DaftarAnakHuruf.class);
                startActivity(intent);
            }
        });
        CardView mnu3 = (CardView) view.findViewById(R.id.mnu3);
        mnu3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), search.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
