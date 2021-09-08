package com.example.task_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    ImageButton camerabtn;
    Bitmap bitmap;
    EditText etname,etemail,etpass;
    Button submitbtn;
    byte[] byteg;
    public static final String EXTRA_NAME="extra.name";
    public static final String EXTRA_Email="extra.email";
    public static final String EXTRA_Pass="extra.pass";
    //public static final String EXTRA_Img="extra.img";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camerabtn=findViewById(R.id.camerabtn);
        etname=findViewById(R.id.name);
        etemail=findViewById(R.id.email);
        etpass=findViewById(R.id.password);
        submitbtn=findViewById(R.id.submit);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewProfile.class);

                String nametxt=etname.getText().toString();
                String emailtxt=etemail.getText().toString();
                String passtxt=etpass.getText().toString();


                intent.putExtra(EXTRA_NAME,nametxt);
                intent.putExtra(EXTRA_Email,emailtxt);
                intent.putExtra(EXTRA_Pass,passtxt);
                intent.putExtra("img",toByte(bitmap));
                startActivity(intent);
            }
        });

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });


    }
    //set Bitmap for images
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null) {
            Toast.makeText(this, "no image selected", Toast.LENGTH_SHORT).show();
        } else {

            bitmap = (Bitmap) data.getExtras().get("data");
            camerabtn.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public byte[] toByte(Bitmap bitmap1){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,40,byteArrayOutputStream);
         byteg = byteArrayOutputStream.toByteArray();
        return byteg;
    }


}