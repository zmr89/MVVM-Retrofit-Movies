package com.example.mvvmretrofitmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.mvvmretrofitmovies.R;
import com.example.mvvmretrofitmovies.service.RetrofitInstance;
import com.example.mvvmretrofitmovies.adapter.MovieAdapter;
import com.example.mvvmretrofitmovies.model.Movies;
import com.example.mvvmretrofitmovies.model.Result;
import com.example.mvvmretrofitmovies.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Result> resultArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);

        getMovies();

        swipeRefresh();
    }


    private void swipeRefresh() {
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });
    }


    public void getMovies(){
        mainActivityViewModel.getListLiveDataResults().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultArrayList = (ArrayList<Result>) results;
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
        movieAdapter = new MovieAdapter(resultArrayList, MainActivity.this);
        recyclerView = findViewById(R.id.recyclerview);

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