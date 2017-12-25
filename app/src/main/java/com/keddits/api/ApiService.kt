package com.keddits.api

import com.keddits.model.GameData
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by marinaracu on 04.12.2017.
 */
interface ApiService {
    @GET("gaming/top.json?")
    fun getGameData(@Query("limit") limit: Int) : Observable<GameData>
}