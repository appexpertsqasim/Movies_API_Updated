package com.example.tae.justeatapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tae.justeatapi.model.Restaurant;

import java.util.List;

/**
 * Created by TAE on 04/10/2017.
 */

public class JEatAdapter extends RecyclerView.Adapter<JEatAdapter.RestaurantViewHolder> {

    List<Restaurant> restaurants;
    int row;
    Context applicationContext;

    public JEatAdapter(List<Restaurant> restaurants, int row, Context applicationContext) {
        this.restaurants = restaurants;
        this.row = row;
        this.applicationContext = applicationContext;
    }


    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, null);
        return new RestaurantViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {

//        holder.name.setText(restaurants.get(position).getName());
//        holder.description.setText(restaurants.get(position).getAddress());
//        holder.rating.setText(restaurants.get(position).getRatingAverage().toString());
//        Picasso.with(applicationContext)
//                .load(restaurants.get(position).getLogo().get(0).getStandardResolutionURL())
//                .resize(500, 500)
//                .centerCrop()
//                .into(holder.logo);
        Log.i("test",restaurants.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView name,description,rating;
        ImageView logo;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.name_tv);
//            description= (TextView) itemView.findViewById(R.id.title);
//            rating = (TextView) itemView.findViewById(R.id.subtitle);
//            logo= (ImageView) itemView.findViewById(R.id.movie_iv);
        }
    }
}
