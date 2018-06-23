package com.example.flickerimgdownload.api;


import com.example.flickerimgdownload.entity.PhotoData;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiInterface {

    @Headers({
            "Cache-max-age: 172800"
    })
    @GET("?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&per_page=20")
    Observable<PhotoData> getImagesData(@Query("text") String text, @Query("page") int page);
}
