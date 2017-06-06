package com.example.tiffanylui.flickerrendering;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiffanylui on 2017-06-06.
 */

public class MainPresenter {

    private MainView mView;
    private List<GalleryItem> mGalleryItems;
    private PhotoMeta mPhotoMeta;

    public MainPresenter(MainView mainView) {
        this.mView = mainView;
        mGalleryItems = new ArrayList<>();
    }

    public void showResponse(GetPhotosResponse getPhotosResponse) {
        mPhotoMeta = getPhotosResponse.getPhotoMeta();
        mGalleryItems = mPhotoMeta.getGalleryItems();
        mView.showPhotos(mGalleryItems);
    }
}
