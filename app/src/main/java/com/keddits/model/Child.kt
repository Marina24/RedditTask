package com.keddits.model

import com.google.gson.annotations.SerializedName

/**
 * Created by marinaracu on 04.12.2017.
 */
data class Child(@SerializedName("data") var data : DataInside? = null)