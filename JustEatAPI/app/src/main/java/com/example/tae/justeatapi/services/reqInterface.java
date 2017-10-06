package com.example.tae.justeatapi.services;

import com.example.tae.justeatapi.model.Restaurants_Model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by TAE on 04/10/2017.
 */

public interface reqInterface {
    @Headers({
            ApiConstants.HEADER_ACCEPT_TENANT,
            ApiConstants.HEADER_ACCEPT_LANGUAGE,
            ApiConstants.HEADER_AUTHORIZATION,
            ApiConstants.HEADER_HOST
    })
    @GET(ApiConstants.BASE_URL)
    Observable<Restaurants_Model> getRestaurantList(@Query(ApiConstants.QUERY) String location);
}
