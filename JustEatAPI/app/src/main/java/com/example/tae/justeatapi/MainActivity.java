package com.example.tae.justeatapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tae.justeatapi.maps.RestaurantMarkers;
import com.example.tae.justeatapi.model.Restaurants_Model;
import com.example.tae.justeatapi.services.ApiConstants;
import com.example.tae.justeatapi.services.ConnectionService;
import com.example.tae.justeatapi.services.reqInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private reqInterface varReqInter;
    private RecyclerView recyclerView;
    Button maps_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseRecyclerView();
        connectionStuff();
        maps_btn=(Button)findViewById(R.id.button2);
        maps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, RestaurantMarkers.class);
                startActivity(intent);
            }
        });

    }

    public void initialiseRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
    public void connectionStuff(){
        varReqInter = ConnectionService.getConnectionService();
        varReqInter.getRestaurantList(ApiConstants.VALUE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(this::onSuccess, this::onError);
        Log.i("~~~~~~~~~~~~~~~", "Connection Stuff");

    }

    private void onError(Throwable throwable) {
        Log.i("______ERROR", throwable.getMessage());
    }

    private void onSuccess(Restaurants_Model restaurants_model) {
        Log.i("onSuccess()", "Success");
        recyclerView.setAdapter(new JEatAdapter(restaurants_model.getRestaurants(), R.layout.row,getApplicationContext()));

    }
}
