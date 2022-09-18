package com.example.mvvmretrofitmovies;

import com.example.mvvmretrofitmovies.service.MovieService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
//    private static final String baseUrl = "https://api.themoviedb.org/";
private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public MovieService getMovieService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        MovieService movieService = retrofit.create(MovieService.class);
        return movieService;
    }

}
