package com.example.tae.moview_api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tae.moview_api.model.OnItemClickListener;
import com.example.tae.moview_api.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TheAppExperts on 27/09/2017.
 */

class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.MovieViewHolder>{

    List<Result>movieDescModels;
    int row;
    Context applicationContext;
    String url="https://image.tmdb.org/t/p/w100_and_h100_bestv2//";
    private final OnItemClickListener listener;


    public MovieAdapter(List<Result> movieDescModels ,int row,OnItemClickListener listener, Context applicationContext) {
        this.movieDescModels = movieDescModels;
        this.listener = listener;

        this.row=row;
        this.applicationContext=applicationContext;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movieDescModels.get(position), listener);
        holder.tvTitle.setText(movieDescModels.get(position).getTitle());
        holder.tvRating.setText(movieDescModels.get(position).getVoteAverage().toString());
        holder.tvSubtitle.setText(movieDescModels.get(position).getOriginalTitle());
        Picasso.with(applicationContext)
                .load(url+movieDescModels.get(position).getPosterPath())
                .resize(500, 500)
                .centerCrop()
                .into(holder.imgMovie);

    }

    @Override
    public int getItemCount() {
        return movieDescModels.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvSubtitle,tvRating;
        ImageView imgMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);

            tvTitle= (TextView) itemView.findViewById(R.id.title);
            tvSubtitle= (TextView) itemView.findViewById(R.id.subtitle);
            imgMovie = (ImageView) itemView.findViewById(R.id.movie_iv);
            tvRating=(TextView)itemView.findViewById(R.id.rating);
        }
        public void bind(final Result item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
