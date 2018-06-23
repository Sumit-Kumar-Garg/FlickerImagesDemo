package com.example.flickerimgdownload.imageSearch;


import com.example.flickerimgdownload.api.RetrofitInterface;
import com.example.flickerimgdownload.entity.PhotoData;
import com.example.flickerimgdownload.utils.Util;

import rx.Observer;
import rx.Subscription;


public class PhotoPhotoInteractor implements PhotoInteractorContract {

    private Subscription imageListSubscription;
    @Override
    public void searchPic(String text, final int page, Observer observer) {

        imageListSubscription = RetrofitInterface.getInstance().getApiInterface().getImagesData(text, page)
                .compose(Util.<PhotoData>applySchedulers())
                .subscribe(observer);
    }

    @Override
    public void unbind() {
        if (imageListSubscription != null) {
            imageListSubscription.unsubscribe();
        }
    }
}
