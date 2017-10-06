package com.example.tae.justeatapi.maps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.tae.justeatapi.R;
import com.example.tae.justeatapi.model.Restaurant;
import com.example.tae.justeatapi.model.Restaurants_Model;
import com.example.tae.justeatapi.services.ApiConstants;
import com.example.tae.justeatapi.services.ConnectionService;
import com.example.tae.justeatapi.services.reqInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RestaurantMarkers extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private reqInterface varReqInter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_markers);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady (GoogleMap googleMap){
        mMap = googleMap;

        locationOfRestaurants();
    }


    public void locationOfRestaurants() {
        varReqInter = ConnectionService.getConnectionService();
        varReqInter.getRestaurantList(ApiConstants.VALUE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(this::onSuccess, this::onError);



    }

    private void onError(Throwable throwable) {

        Log.i("error",throwable.getMessage());
    }

    private void onSuccess(Restaurants_Model restaurants_model) throws IOException {

        List<Restaurant> restaurants=restaurants_model.getRestaurants();
        Restaurant r;
        PicassoMarker pm;
        Marker marker;
        for (int i = 0; i <restaurants.size(); i++)
        {

            r=restaurants.get(i);
            LatLng location= new LatLng(r.getLatitude(), r.getLongitude());
            marker=mMap.addMarker(new MarkerOptions()
                    .position(location).title(r.getName())
                    .snippet(r.getRatingAverage().toString()));
            pm=new PicassoMarker(marker);
            Picasso.with(getApplicationContext()).load(r.getLogo().get(0).
                getStandardResolutionURL())
                    .resize(40,50)
                    .into(pm);
     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,11));

        }

    }
}