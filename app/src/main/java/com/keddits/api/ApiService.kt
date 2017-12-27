package com.keddits.api

import com.keddits.model.GameData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by marinaracu on 04.12.2017.
 */
interface ApiService {
    @GET("gaming/top.json?")
    fun getGameData(@Query("limit") limit: Int) : Single<GameData>
}