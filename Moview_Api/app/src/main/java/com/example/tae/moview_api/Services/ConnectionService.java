package com.example.tae.moview_api.Services;

import com.example.tae.moview_api.API_Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TheAppExperts on 27/09/2017.
 */

    public class ConnectionService {

    static Retrofit retrofit;


        public static reqInterface getConnectionService(){

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(reqInterface.class);
        }

    }
