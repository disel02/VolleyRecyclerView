package com.disel.volleyrecyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

public class ViewItemActivity extends AppCompatActivity {

    String name,description,studio,category,rating,image_url;
    TextView tv_name,tv_studio,tv_categorie,tv_description,tv_rating;
    ImageView img;
    int nb_episode;
    Button btntrack;
    double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        // hide the default actionbar
        getSupportActionBar().hide();

        // Recieve data from list activity ( click item )
        name  = getIntent().getExtras().getString("anime_name");
        description = getIntent().getExtras().getString("anime_description");
        studio = getIntent().getExtras().getString("anime_studio") ;
        category = getIntent().getExtras().getString("anime_category");
        nb_episode = getIntent().getExtras().getInt("anime_nb_episode") ;
        rating = getIntent().getExtras().getString("anime_rating") ;
        image_url = getIntent().getExtras().getString("anime_img") ;

        // ini views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        // define
        tv_name = findViewById(R.id.aa_anime_name);
        tv_studio = findViewById(R.id.aa_studio);
        tv_categorie = findViewById(R.id.aa_categorie) ;
        tv_description = findViewById(R.id.aa_description);
        tv_rating  = findViewById(R.id.aa_rating) ;
        img = findViewById(R.id.aa_thumbnail);
        btntrack=(Button)findViewById(R.id.btntrack);

        // setting values to each view
        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);

        // set title
        collapsingToolbarLayout.setTitle(name);

        // set image using Glide
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        Glide.with(this).load(image_url).apply(requestOptions).into(img);

        // open external map activity
        latitude=18.5204;
        longitude=73.8567;

        btntrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

    }
}
