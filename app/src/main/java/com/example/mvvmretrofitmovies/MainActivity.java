package com.example.mvvmretrofitmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mvvmretrofitmovies.model.Movies;
import com.example.mvvmretrofitmovies.model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Result> resultArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMovies();
    }


    public void getMovies(){
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.getMovieService().getMovieCall().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response != null){
                    resultArrayList = (ArrayList<Result>) response.body().getResults();
                    for (Result r : resultArrayList){
                        Log.d("getOriginalTitle", r.getOriginalTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }


}