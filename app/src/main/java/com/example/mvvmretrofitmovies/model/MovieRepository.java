package com.example.mvvmretrofitmovies.model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretrofitmovies.R;
import com.example.mvvmretrofitmovies.service.MovieService;
import com.example.mvvmretrofitmovies.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private List<Result> resultArrayList = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        MovieService movieService = retrofitInstance.getMovieService();
        Call<Movies> moviesCall = movieService.getMovieCall(application.getApplicationContext().getString(R.string.api_key));

        moviesCall.enqueue(new Callback<Movies>() {

            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();

                if (movies != null && movies.getResults() != null){
                    resultArrayList = (ArrayList<Result>) movies.getResults();
                    mutableLiveData.setValue(resultArrayList);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
