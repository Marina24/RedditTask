package com.keddits.model

import android.support.annotation.IntegerRes
import com.google.gson.annotations.SerializedName

/**
 * Created by marinaracu on 04.12.2017.
 */
data class DataInside(@SerializedName("subreddit") var subreddit : String,
                      @SerializedName("title") var title : String,
                      @SerializedName("score") var score : Int,
                      @SerializedName("thumbnail") var thumbnail : String,
                      @SerializedName("author") var author : String,
                      @SerializedName("created_utc") var createdUtc : Int,
                      @SerializedName("num_comments") var numComments : Int,
                      @SerializedName("url") var url : String)