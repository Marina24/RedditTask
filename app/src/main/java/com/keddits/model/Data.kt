package com.keddits.model

import com.google.gson.annotations.SerializedName

/**
 * Created by marinaracu on 21.12.2017.
 */
data class Data (@SerializedName("children") var children : MutableList<Child>)