package com.paysera.lib.accounts.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.CardLimit
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.lang.reflect.Type
import java.math.RoundingMode

class CardLimitDeserializer : JsonDeserializer<CardLimit> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): CardLimit {
        val jsonObject = json.asJsonObject.getAsJsonObject("amount")
        var amount = jsonObject.get("amount").asBigDecimal
        amount = amount.setScale(2, RoundingMode.FLOOR)
        val currency = jsonObject.get("currency").asString
        return CardLimit(Money.of(CurrencyUnit.of(currency), amount))
    }
}