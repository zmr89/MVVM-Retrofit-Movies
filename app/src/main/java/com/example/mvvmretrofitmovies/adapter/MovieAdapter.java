package com.example.mvvmretrofitmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmretrofitmovies.DetailActivity;
import com.example.mvvmretrofitmovies.R;
import com.example.mvvmretrofitmovies.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Result> resultArrayList;
    private Context context;

    public MovieAdapter(ArrayList<Result> resultArrayList, Context context) {
        this.resultArrayList = resultArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Result result = resultArrayList.get(position);
//        String posterUrl = "https://image.tmdb.org/t/p/original/" + result.getBackdropPath();
//        Picasso.get().load(posterUrl)
//                .resize(500, 500).centerInside().into(holder.poster);
        String posterUrl = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();
        Glide.with(context).load(posterUrl).into(holder.poster);
        holder.title.setText(result.getTitle());
        holder.popularity.setText(result.getPopularity().toString());

    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;
        TextView title;
        TextView popularity;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            this.poster = itemView.findViewById(R.id.poster);
            this.title = itemView.findViewById(R.id.title);
            this.popularity = itemView.findViewById(R.id.popularity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Result result = resultArrayList.get(getAdapterPosition());
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.POSTER, result.getBackdropPath());
            intent.putExtra(DetailActivity.TITLE, result.getTitle());
            intent.putExtra(DetailActivity.VOTE_COUNT, result.getVoteCount().toString());
            intent.putExtra(DetailActivity.OVERVIEW, result.getOverview());
            context.startActivity(intent);
        }
    }


}
