package com.example.mvvmretrofitmovies.service;

import com.example.mvvmretrofitmovies.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("3/movie/popular?api_key=c9d48bea066a39e1ccce61b2cd2da8cb&language=en-US&page=1")
    Call<Movies> getMovieCall();
}
