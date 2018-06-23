package com.example.flickerimgdownload.imageSearch;

import com.example.flickerimgdownload.entity.PhotoModel;

import java.util.List;


public interface PhotoViewContract {

    void showImages(List<PhotoModel> photoModels);

    void showError();

    void showMore(List<PhotoModel> photoModels);

    void showProgress(boolean show);

}
