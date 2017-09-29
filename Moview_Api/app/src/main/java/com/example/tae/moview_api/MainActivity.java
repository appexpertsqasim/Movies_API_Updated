package com.example.tae.moview_api;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.tae.moview_api.Services.ConnectionService;
import com.example.tae.moview_api.Services.Description;
import com.example.tae.moview_api.Services.reqInterface;
import com.example.tae.moview_api.model.Movie_Description_Model;
import com.example.tae.moview_api.model.OnItemClickListener;
import com.example.tae.moview_api.model.Result;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private reqInterface varReqInter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseRecyclerView();
        connectionStuff();


    }


    public void connectionStuff(){
        varReqInter = ConnectionService.getConnectionService();
        varReqInter.getMoviesList(API_Constants.API_Key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(this::onSuccess, this::onError);

    }


    private void onSuccess(Movie_Description_Model movie_desc_model) {
        recyclerView.setAdapter(new MovieAdapter(movie_desc_model.getResults(), R.layout.list_item_movie, new OnItemClickListener() {
            @Override
            public void onItemClick(Result item) {
                Toast.makeText(getApplicationContext(),item.getId().toString(),Toast.LENGTH_LONG).show();
                Intent intent= new Intent(MainActivity.this, Description.class)
                        .putExtra("id",item.getId());
                startActivity(intent);
            }
        }, getApplicationContext()));

    }


    private void onError(Throwable throwable) {
        Log.i("MovieList", throwable.getMessage());
    }



    public void initialiseRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

}
