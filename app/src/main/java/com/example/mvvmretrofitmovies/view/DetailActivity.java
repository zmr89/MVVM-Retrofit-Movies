package com.example.mvvmretrofitmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvvmretrofitmovies.R;
import com.example.mvvmretrofitmovies.databinding.ActivityDetailBinding;
import com.example.mvvmretrofitmovies.model.Result;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String MOVIE_DATA = "movieData";
    private Result result;
    ActivityDetailBinding activityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        getExtras();
    }

    //    private void getExtras() {
//        Intent intent = getIntent();
//        if (intent != null && intent.hasExtra(MOVIE_DATA)) {
//            result = intent.getParcelableExtra(MOVIE_DATA);
//        }
//
//            ImageView posterDetail = findViewById(R.id.posterDetail);
//            TextView titleDetail = findViewById(R.id.titleDetail);
//            TextView voteCountDetail = findViewById(R.id.voteCountDetail);
//            TextView overviewDetail = findViewById(R.id.overviewDetail);
//
//            String posterUrl = "https://image.tmdb.org/t/p/original/" + result.getBackdropPath();
//            Picasso.get().load(posterUrl).resize(800, 800).centerInside().into(posterDetail);
////            String posterUrl = "https://image.tmdb.org/t/p/original/" + result.getBackdropPath();
////            Glide.with(this).load(posterUrl).placeholder(R.drawable.progress_circle).into(posterDetail);
//            titleDetail.setText(result.getTitle());
//            voteCountDetail.setText(result.getVoteCount().toString());
//            overviewDetail.setText(result.getOverview());
//    }
    private void getExtras() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MOVIE_DATA)) {
            result = intent.getParcelableExtra(MOVIE_DATA);

            String posterUrl = "https://image.tmdb.org/t/p/original/" + result.getBackdropPath();
            result.setPosterPath(posterUrl);

            activityDetailBinding.setResultBindingDetail(result);
        }



//        Picasso.get().load(posterUrl).resize(800, 800).centerInside().into(activityDetailBinding.posterDetail);
//            String posterUrl = "https://image.tmdb.org/t/p/original/" + result.getBackdropPath();
//            Glide.with(this).load(posterUrl).placeholder(R.drawable.progress_circle).into(posterDetail);
    }


}