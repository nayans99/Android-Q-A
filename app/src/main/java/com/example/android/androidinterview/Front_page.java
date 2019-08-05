package com.example.android.androidinterview;

import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Front_page extends AppCompatActivity implements View.OnClickListener{

    Button bs, bd, bo, br;

    FirebaseDatabase db;
    DatabaseReference mref, mchild;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        LinearLayout fll = (LinearLayout)findViewById(R.id.fronttitle) ;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.front_title);

        bs = (Button)findViewById(R.id.bsq);
        bd = (Button)findViewById(R.id.bdq);
        bo = (Button)findViewById(R.id.bso);
        br = (Button)findViewById(R.id.brate);

        bs.setOnClickListener(this);
        bd.setOnClickListener(this);
        bo.setOnClickListener(this);
        br.setOnClickListener(this);


        db = FirebaseDatabase.getInstance();
        mref = db.getReference();
        mchild = mref.child("name");

        mchild.setValue("nayan");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bsq : {
                Intent i = new Intent(Front_page.this, simple_questions.class);
                startActivity(i);
                break;
            }
            case R.id.bdq : {
                Intent j = new Intent(Front_page.this, Tough_questions.class);
                startActivity(j);
                break;
            }
            case R.id.bso : {
                break;
            }
            case R.id.brate : {

                Uri uri1 = Uri.parse("market://details?id");

                break;
            }
        }
    }
}
