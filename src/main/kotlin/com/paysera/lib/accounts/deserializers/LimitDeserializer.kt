package com.paysera.lib.accounts.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.Limit
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.lang.reflect.Type
import java.math.RoundingMode

class LimitDeserializer : JsonDeserializer<Limit> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Limit {
        val jsonObject = json.asJsonObject.getAsJsonObject("amount")
        var amount = jsonObject.get("amount").asBigDecimal
        amount = amount.setScale(2, RoundingMode.CEILING)
        val currency = jsonObject.get("currency").asString
        return Limit(Money.of(CurrencyUnit.of(currency), amount))
    }
}