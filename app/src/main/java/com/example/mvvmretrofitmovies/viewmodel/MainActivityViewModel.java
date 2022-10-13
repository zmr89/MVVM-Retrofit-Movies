package com.example.mvvmretrofitmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmretrofitmovies.model.MovieRepository;
import com.example.mvvmretrofitmovies.model.Result;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    MovieRepository movieRepository;
    private LiveData<List<Result>> listLiveDataResults;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        this.movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Result>> getListLiveDataResults() {
        if (listLiveDataResults == null){
            listLiveDataResults = movieRepository.getMutableLiveData();
        }
        return listLiveDataResults;
    }

}
