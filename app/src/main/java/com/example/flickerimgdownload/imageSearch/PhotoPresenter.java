package com.example.flickerimgdownload.imageSearch;

import android.util.Log;

import com.example.flickerimgdownload.entity.PhotoData;

import rx.Subscriber;


public class PhotoPresenter implements PresenterContract {
    public static final String TAG = "PhotoPresenter";

    private PhotoViewContract view;
    private PhotoInteractorContract interactor;

    public PhotoPresenter(PhotoViewContract photoViewContract) {
        this.view = photoViewContract;
        interactor = new PhotoPhotoInteractor();
    }

    @Override
    public void searchImagesResult(String text) {

        view.showProgress(true);
        interactor.searchPic(text, 1, new Subscriber<PhotoData>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
                e.printStackTrace();
                view.showError();
                view.showProgress(false);
            }

            @Override
            public void onNext(PhotoData userData) {
                Log.d(TAG, "onNext: " + userData);

                view.showImages(userData.photos.photo);
                view.showProgress(false);
            }
        });

    }

    @Override
    public void loadMoreImages(String text, int page) {

        interactor.searchPic(text, page, new Subscriber<PhotoData>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
                e.printStackTrace();
                view.showError();
            }

            @Override
            public void onNext(PhotoData userData) {
                Log.d(TAG, "onNext: " + userData);

                view.showMore(userData.photos.photo);
            }
        });

    }

    @Override
    public void unbind() {
        interactor.unbind();
    }

}
