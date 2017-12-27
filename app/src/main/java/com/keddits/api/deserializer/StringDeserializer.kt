package com.keddits.api.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

/**
 * Created by marinaracu on 26.12.2017.
 */
class StringDeserializer : JsonDeserializer<String> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): String? {
        if (json.isJsonObject || json.isJsonArray) {
            return json.toString()
        } else if (json.isJsonNull) {
            return null
        }
        return json.asString
    }
}