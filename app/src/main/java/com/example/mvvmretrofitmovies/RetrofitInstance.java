package com.example.mvvmretrofitmovies;

import com.example.mvvmretrofitmovies.model.Movies;
import com.example.mvvmretrofitmovies.service.MovieService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private Retrofit retrofit = null;
    private static final String baseUrl = "https://api.themoviedb.org/";

    public MovieService getMovieService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        MovieService movieService = retrofit.create(MovieService.class);
        return movieService;
    }

}
