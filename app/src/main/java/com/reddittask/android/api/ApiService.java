package com.reddittask.android.api;

import com.reddittask.android.model.GameData;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marinaracu on 30.11.2017.
 */

public interface ApiService {

    @GET("gaming/top.json?")
    Observable<GameData> getMoviesData(@Query("limit") int limit);
}
