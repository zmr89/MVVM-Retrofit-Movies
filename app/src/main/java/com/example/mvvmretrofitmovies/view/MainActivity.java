package com.example.mvvmretrofitmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.mvvmretrofitmovies.R;
import com.example.mvvmretrofitmovies.databinding.ActivityMainBinding;
import com.example.mvvmretrofitmovies.adapter.MovieAdapter;
import com.example.mvvmretrofitmovies.model.Result;
import com.example.mvvmretrofitmovies.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PagedList<Result> resultPagedList;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainActivityViewModel mainActivityViewModel;

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);

//        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        getMovies();

        swipeRefresh();
    }


    private void swipeRefresh() {
        swipeRefreshLayout = activityMainBinding.swiperefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });
    }


    public void getMovies(){
//        mainActivityViewModel.getListLiveDataResults().observe(this, new Observer<List<Result>>() {
//            @Override
//            public void onChanged(List<Result> results) {
//                resultPagedList = (ArrayList<Result>) results;
//                fillRecyclerView();
//            }
//        });
        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                resultPagedList = results;
                fillRecyclerView();
            }
        });

//        RetrofitInstance retrofitInstance = new RetrofitInstance();
//        retrofitInstance.getMovieService().getMovieCall(getString(R.string.api_key)).enqueue(new Callback<Movies>() {
//            @Override
//            public void onResponse(Call<Movies> call, Response<Movies> response) {
//
//                if (response != null && response.body() != null){
//                    Movies movies = response.body();
//                    resultArrayList = (ArrayList<Result>) movies.getResults();
//
//                    fillRecyclerView();
//
//                    swipeRefreshLayout.setRefreshing(false);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Movies> call, Throwable t) {
//
//            }
//        });
    }

    private void fillRecyclerView() {
        recyclerView = activityMainBinding.recyclerview;
        movieAdapter = new MovieAdapter(this);
        movieAdapter.submitList(resultPagedList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            RecyclerView.LayoutManager gridLayoutManager= new GridLayoutManager(MainActivity.this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            RecyclerView.LayoutManager gridLayoutManager= new GridLayoutManager(MainActivity.this, 4);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);
    }
}