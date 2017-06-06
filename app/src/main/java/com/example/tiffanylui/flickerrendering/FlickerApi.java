package com.example.tiffanylui.flickerrendering;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by tiffanylui on 2017-06-06.
 */

public class FlickerApi {
    String baseURL = "https://api.flickr.com/services/";

    private Retrofit mRestAdapter;
    private FlickerService mService;

    private static FlickerApi sInstance;

    private FlickerApi() {
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static FlickerApi getInstance() {
        if (sInstance == null) {
            sInstance = new FlickerApi();
        }
        return sInstance;
    }

    FlickerService getService() {
        if (mService == null) {
            mService = mRestAdapter.create(FlickerService.class);
        }
        return mService;
    }

    public Observable<GetPhotosResponse> getResponse(String searchText) {
        return getService().getResponse(searchText);
    }
}
