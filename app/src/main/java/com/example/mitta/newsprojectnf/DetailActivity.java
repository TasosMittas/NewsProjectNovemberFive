package com.example.mitta.newsprojectnf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.master.glideimageview.GlideImageView;
/*

 */
public class DetailActivity extends AppCompatActivity{

    private String title;
    private String text;
    private String imageUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("News");

        Intent intent  = getIntent();
        title = intent.getStringExtra("title");
        text = intent.getStringExtra("text");
        imageUrl = intent.getStringExtra("imageUrl");


        // Set article title
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(title);

        // Set article text
        TextView articleTextView = findViewById(R.id.articleTextView);
        articleTextView.setText(text);
        articleTextView.setMovementMethod(new ScrollingMovementMethod());

        // Set glide image view
        GlideImageView glideImageView = findViewById(R.id.detailGlideImageView);
        glideImageView.loadImageUrl(imageUrl);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
