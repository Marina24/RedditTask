package com.keddits.data.source.base

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.keddits.BuildConfig
import com.keddits.Const
import com.keddits.api.ApiService
import com.keddits.api.deserializer.StringDeserializer
import io.reactivex.schedulers.Schedulers
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by marinaracu on 26.12.2017.
 */
abstract class BaseRemoteDataSource : BaseDataSource {

    protected lateinit var apiService: ApiService
    private lateinit var retrofit: Retrofit

    override fun init() {
        initRetrofit()
        initServices()
    }

    private fun initServices() {
        apiService = retrofit.create<ApiService>(ApiService::class.java)
    }

    private fun initRetrofit() {
        val level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val client = OkHttpClient.Builder().addInterceptor { chain -> val original = chain.request()
            val request = original.newBuilder()
//                    .header(ApiSettings.HEADER_AUTH_TOKEN, getAuthToken())
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }.addInterceptor(HttpLoggingInterceptor().setLevel(level)).build()

        retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(createGsonConverter())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build()
    }

    private fun createGsonConverter(): GsonConverterFactory {
        val builder = GsonBuilder()
        // for Null value serialization
        builder.serializeNulls()
        // for field exclusions
        builder.setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        })
        builder.registerTypeAdapter(String::class.java, StringDeserializer())
        return GsonConverterFactory.create(builder.create())
    }

    override fun clear() {

    }

}