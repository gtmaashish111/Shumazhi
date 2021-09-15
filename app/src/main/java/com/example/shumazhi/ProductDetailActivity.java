package com.example.shumazhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URL;

public class ProductDetailActivity extends AppCompatActivity {
    public static TextView name;
    public static TextView description;
    public static TextView model;
    public static TextView height;
    public static TextView width;
    public static TextView price;
    public static TextView rating;
    public static ImageView imgView;
    TextView btnCode;
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        name = (TextView) findViewById(R.id.txtName);
        description = (TextView) findViewById(R.id.txtDescription);
        model = (TextView) findViewById(R.id.txtModel);
        height = (TextView) findViewById(R.id.txtHeight);
        width = (TextView) findViewById(R.id.txtWidth);
        price = (TextView) findViewById(R.id.txtPrice);
        rating = (TextView) findViewById(R.id.txtRating);
        imgView = (ImageView) findViewById(R.id.imgProduct);
        btnCode = findViewById(R.id.btnCode);



        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, ScanCode.class);
                startActivity(intent);

            }
        });

        fetchData process = new fetchData();


        process.execute();
    }

}
