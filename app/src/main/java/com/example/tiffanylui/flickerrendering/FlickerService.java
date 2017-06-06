package com.example.tiffanylui.flickerrendering;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by tiffanylui on 2017-06-06.
 */

public interface FlickerService {

    @GET("rest?method=flickr.photos.search&api_key=ece2e8dfc3b0dcb883577e3d6bad6290&format=json&nojsoncallback=1&extras=url_s")
    Observable<GetPhotosResponse> getResponse(@Query("text") String searchText);
}
