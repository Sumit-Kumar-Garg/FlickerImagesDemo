
package com.example.flickerimgdownload.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoData {

    @SerializedName("photos")
    @Expose
    public PhotoModelList photos;
    @SerializedName("stat")
    @Expose
    public String stat;

}
