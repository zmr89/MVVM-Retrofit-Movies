package com.example.mvvmretrofitmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String POSTER = "posterDetail";
    public static final String TITLE = "titleDetail";
    public static final String VOTE_COUNT = "voteCountDetail";
    public static final String OVERVIEW = "overviewDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getExtras();
    }

    private void getExtras() {
        Intent intent = getIntent();
        String poster = intent.getExtras().getString(POSTER);
        String title = intent.getExtras().getString(TITLE);
        String vote = intent.getExtras().getString(VOTE_COUNT);
        String overview = intent.getExtras().getString(OVERVIEW);

        ImageView posterDetail = findViewById(R.id.posterDetail);
        TextView titleDetail = findViewById(R.id.titleDetail);
        TextView voteCountDetail = findViewById(R.id.voteCountDetail);
        TextView overviewDetail = findViewById(R.id.overviewDetail);

        String posterUrl = "https://image.tmdb.org/t/p/original/" + poster;
        Picasso.get().load(posterUrl)
                .resize(800, 800).centerInside().into(posterDetail);
        titleDetail.setText(title);
        voteCountDetail.setText(vote);
        overviewDetail.setText(overview);
    }
}