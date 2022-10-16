package com.example.mvvmretrofitmovies.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.mvvmretrofitmovies.R;
import com.example.mvvmretrofitmovies.service.MovieService;
import com.example.mvvmretrofitmovies.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {
    private MovieService movieService;
    private Application application;

    public MovieDataSource(MovieService movieService, Application application) {
        this.movieService = movieService;
        this.application = application;
    }


    @Override
    public void loadBefore(@NonNull LoadParams<Long> loadParams, @NonNull LoadCallback<Long, Result> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> loadInitialParams, @NonNull LoadInitialCallback<Long, Result> loadInitialCallback) {

        movieService = RetrofitInstance.getMovieService();
        Call<Movies> call = movieService.getMovieCallWithPaging(
                application.getApplicationContext().getString(R.string.api_key), 1);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movies != null && movies.getResults() != null){
                    results = (ArrayList<Result>) movies.getResults();
                    loadInitialCallback.onResult(results, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

    }


    @Override
    public void loadAfter(@NonNull LoadParams<Long> loadParams, @NonNull final LoadCallback<Long, Result> loadCallback) {

        movieService = RetrofitInstance.getMovieService();
        Call<Movies> call = movieService.getMovieCallWithPaging(
                application.getApplicationContext().getString(R.string.api_key), loadParams.key);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movies != null && movies.getResults() != null){
                    results = (ArrayList<Result>) movies.getResults();
                    loadCallback.onResult(results, loadParams.key + 1);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

    }
}
