package com.paysera.lib.accounts.serializers

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.paysera.lib.accounts.entities.Limit
import java.lang.reflect.Type

class LimitSerializer : JsonSerializer<Limit> {

    override fun serialize(src: Limit, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()

        val amountObject = JsonObject()
        amountObject.addProperty("amount", src.amount.amount.toInt())
        amountObject.addProperty("currency", src.amount.currencyUnit.toString())

        jsonObject.add("amount" ,amountObject)
        return jsonObject
    }
}