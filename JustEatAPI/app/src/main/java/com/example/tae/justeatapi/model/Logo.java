package com.example.tae.justeatapi.model;

/**
 * Created by TAE on 04/10/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logo {

    @SerializedName("StandardResolutionURL")
    @Expose
    private String standardResolutionURL;

    public String getStandardResolutionURL() {
        return standardResolutionURL;
    }

    public void setStandardResolutionURL(String standardResolutionURL) {
        this.standardResolutionURL = standardResolutionURL;
    }

}



