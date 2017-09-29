package com.example.tae.moview_api.Services;

import com.example.tae.moview_api.model.Movie_Description_Model;
import com.example.tae.moview_api.model.Movie_List_Model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TheAppExperts on 27/09/2017.
 */

public interface reqInterface {


    @GET("movie/top_rated")
    Observable<Movie_Description_Model> getMoviesList(@Query("api_key") String apikey);

    @GET("movie/{id}")
    Observable<Movie_List_Model> getMovie(@Path("id") int id, @Query("api_key") String apikey);
}
