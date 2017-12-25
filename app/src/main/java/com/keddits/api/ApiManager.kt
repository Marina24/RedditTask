package com.keddits.api

import com.keddits.BuildConfig
import com.keddits.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by marinaracu on 21.12.2017.
 */
class ApiManager {

    companion object {
        fun getClient(): Retrofit {
            val logginIntercptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG)
                logginIntercptor.level = HttpLoggingInterceptor.Level.BODY


            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

            httpClient.addInterceptor(logginIntercptor)

            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()

            return retrofit
        }
    }
}

// to read:
// 1. Repository pattern