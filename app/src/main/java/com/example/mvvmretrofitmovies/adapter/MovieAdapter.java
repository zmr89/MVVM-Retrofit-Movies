package com.example.mvvmretrofitmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmretrofitmovies.databinding.MovieItemBinding;
import com.example.mvvmretrofitmovies.view.DetailActivity;
import com.example.mvvmretrofitmovies.R;
import com.example.mvvmretrofitmovies.model.Result;

import java.util.ArrayList;

public class MovieAdapter extends PagedListAdapter<Result, MovieAdapter.MovieViewHolder> {
//    private ArrayList<Result> resultArrayList;
    private Context context;

    public MovieAdapter(Context context) {
        super(Result.CALLBACK);
//        this.resultArrayList = resultArrayList;
        this.context = context;
    }


    @NonNull
    @Override
//    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
//        return new MovieViewHolder(view);
//    }
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_item, parent, false);
        return new MovieViewHolder(movieItemBinding);
    }

    @Override
//    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
//        Result result = resultArrayList.get(position);
////        String posterUrl = "https://image.tmdb.org/t/p/original/" + result.getBackdropPath();
////        Picasso.get().load(posterUrl)
////                .resize(500, 500).centerInside().into(holder.poster);
//        String posterUrl = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();
//        Glide.with(context).load(posterUrl).placeholder(R.drawable.progress_circle).into(holder.poster);
//        holder.title.setText(result.getTitle());
//        holder.popularity.setText(result.getPopularity().toString());
//    }
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Result result = getItem(position);

//        String posterUrl = "https://image.tmdb.org/t/p/original/" + result.getBackdropPath();
//        Picasso.get().load(posterUrl)
//                .resize(500, 500).centerInside().into(holder.poster);
        String posterUrl = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();
        result.setPosterPath(posterUrl);

        holder.movieItemBinding.setResultBinding(result);
//        Glide.with(context).load(posterUrl).placeholder(R.drawable.progress_circle).into(holder.movieItemBinding.poster);
    }

//    @Override
//    public int getItemCount() {
//        return resultArrayList.size();
//    }

//    public class MovieViewHolder extends RecyclerView.ViewHolder {
//        ImageView poster;
//        TextView title;
//        TextView popularity;
//
//        public MovieViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            this.poster = itemView.findViewById(R.id.poster);
//            this.title = itemView.findViewById(R.id.title);
//            this.popularity = itemView.findViewById(R.id.popularity);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        Result result = resultArrayList.get(position);
//                        Intent intent = new Intent(context, DetailActivity.class);
//                        intent.putExtra(DetailActivity.MOVIE_DATA, result);
//                        context.startActivity(intent);
//                    }
//                }
//            });
//        }
//
//    }
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        MovieItemBinding movieItemBinding;

        public MovieViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());

            this.movieItemBinding = movieItemBinding;

            movieItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Result result = getItem(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra(DetailActivity.MOVIE_DATA, result);
                        context.startActivity(intent);
                    }
                }
            });
        }

    }


}
