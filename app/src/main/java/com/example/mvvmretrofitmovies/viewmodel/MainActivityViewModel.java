package com.example.mvvmretrofitmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.mvvmretrofitmovies.model.MovieDataSource;
import com.example.mvvmretrofitmovies.model.MovieDataSourceFactory;
import com.example.mvvmretrofitmovies.model.MovieRepository;
import com.example.mvvmretrofitmovies.model.Result;
import com.example.mvvmretrofitmovies.service.MovieService;
import com.example.mvvmretrofitmovies.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Result>> listLiveDataResults;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Result>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        this.movieRepository = new MovieRepository(application);

        MovieService movieService = RetrofitInstance.getMovieService();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(application, movieService);
        movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(3)
                .build();

        executor = Executors.newCachedThreadPool();

        pagedListLiveData =
                new LivePagedListBuilder<Long, Result>(movieDataSourceFactory, config).setFetchExecutor(executor).build();
    }

    public LiveData<List<Result>> getListLiveDataResults() {
        if (listLiveDataResults == null){
            listLiveDataResults = movieRepository.getMutableLiveData();
        }
        return listLiveDataResults;
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
