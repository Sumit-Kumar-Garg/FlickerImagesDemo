package com.example.flickerimgdownload.imageSearch;

import rx.Observer;


public interface PhotoInteractorContract {

    void searchPic(String text, final int page, Observer observer);

    void unbind();
}
