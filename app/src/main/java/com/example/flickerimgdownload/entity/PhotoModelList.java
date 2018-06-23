
package com.example.flickerimgdownload.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PhotoModelList {

    @SerializedName("page")
    @Expose
    public int page;
    @SerializedName("pages")
    @Expose
    public int pages;
    @SerializedName("perpage")
    @Expose
    public int perpage;
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("photo")
    @Expose
    public List<PhotoModel> photo = new ArrayList<>();

}
