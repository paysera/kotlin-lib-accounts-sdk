package com.paysera.lib.accounts.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.cards.Price
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.lang.reflect.Type

class PriceDeserializer : JsonDeserializer<Price> {

	override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Price {
		val jsonObject = json?.asJsonObject
		val amount = jsonObject?.get("amount")?.asBigDecimal
		val currency = jsonObject?.get("currency")?.asString
		return Price(Money.of(CurrencyUnit.of(currency), amount))
	}

}