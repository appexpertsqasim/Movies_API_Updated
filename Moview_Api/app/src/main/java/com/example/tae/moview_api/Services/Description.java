package com.example.tae.moview_api.Services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tae.moview_api.API_Constants;
import com.example.tae.moview_api.R;
import com.example.tae.moview_api.model.Movie_List_Model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TAE on 28/09/2017.
 */

public class Description extends AppCompatActivity{
    private reqInterface varReqInter;
    TextView tvTitle;
    int itemId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_desc);
        tvTitle= (TextView) findViewById(R.id.title);
        itemId = getIntent().getIntExtra("id",0);

        connectionStuff();
    }
    public void connectionStuff(){
        varReqInter = ConnectionService.getConnectionService();
        Toast.makeText(getApplicationContext(),String.valueOf(itemId),Toast.LENGTH_LONG).show();
        varReqInter.getMovie(itemId,API_Constants.API_Key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(this::onSuccess, this::onError);

    }

    private void onError(Throwable throwable) {
        Log.i("TEST_ERROR",throwable.getMessage());
    }

    private void onSuccess(Movie_List_Model movie_list_model) {
        Log.i("TEST_SUCCESS","onSuccess");

        tvTitle.setText(movie_list_model.getTitle());
    }
}