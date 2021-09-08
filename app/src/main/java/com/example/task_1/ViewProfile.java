package com.example.task_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {
    TextView tvname,tvemail,tvpass;
    ImageView mImageView;
    Bitmap bit;
    Button backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        tvname=findViewById(R.id.viewname);
        tvemail=findViewById(R.id.viewemail);
        tvpass=findViewById(R.id.viewpassword);
        mImageView=findViewById(R.id.viewimg);
        backbtn=findViewById(R.id.backbtn);

        Intent intent=getIntent();
        String strname= intent.getStringExtra(MainActivity.EXTRA_NAME);
        String stremail= intent.getStringExtra(MainActivity.EXTRA_Email);
        String strpass= intent.getStringExtra(MainActivity.EXTRA_Pass);

        tvname.setText(strname);
        tvemail.setText(stremail);
        tvpass.setText(strpass);


        mImageView.setImageBitmap(toBitmap(getIntent().getByteArrayExtra("img")));

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ViewProfile.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
    public Bitmap toBitmap(byte[] bytteimg){
        Bitmap mbitmap= BitmapFactory.decodeByteArray(bytteimg,0,bytteimg.length);
        return mbitmap;
    }
}