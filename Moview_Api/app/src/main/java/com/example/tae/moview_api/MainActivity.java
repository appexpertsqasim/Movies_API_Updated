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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private reqInterface varReqInter;
    private RecyclerView recyclerView;
    List <Result> filterResult;
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
                .filter(new Predicate<Movie_Description_Model>() {

                    @Override
                    public boolean test(@NonNull Movie_Description_Model movie_description_model) throws Exception {
                          List <Result> result =movie_description_model.getResults();
                          filterResult=new ArrayList<Result>();
                            for (int i=0;i<result.size();i++){
                             if (result.get(i).getTitle().equals("The Shawshank Redemption")){
                             filterResult.add(result.get(i));
                                 Log.i("++++++++++++  Good Ratings",result.get(i).getTitle());
                             }
                             else{
                                 Log.i("++++++++++++ Bad Ratings",result.get(i).getTitle());
                             }


                            }
                        return true;
                    }

                })
                .subscribe(this::onSuccess, this::onError);

    }



    private void onSuccess(Movie_Description_Model movie_desc_model) {
        recyclerView.setAdapter(new MovieAdapter(filterResult, R.layout.list_item_movie, new OnItemClickListener() {
            @Override
            public void onItemClick(Result item) {
                Toast.makeText(getApplicationContext(),item.getId().toString(),Toast.LENGTH_LONG).show();
                Intent intent= new Intent(MainActivity.this, Description.class)
                        .putExtra("id",item.getId());
                startActivity(intent);
            }
        }, getApplicationContext()));

    }


    public void getResults(){


    }


    private void onError(Throwable throwable) {
        Log.i("MovieList", throwable.getMessage());
    }



    public void initialiseRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

}
