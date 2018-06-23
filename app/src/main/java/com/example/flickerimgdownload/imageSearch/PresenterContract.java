package com.example.flickerimgdownload.imageSearch;


public interface PresenterContract {

    void searchImagesResult(String text);
    void loadMoreImages(String text, int page);
    void unbind();
}
