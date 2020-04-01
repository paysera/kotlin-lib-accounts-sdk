package com.paysera.lib.accounts.serializers

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode

class MoneyAdapter : TypeAdapter<Money>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, money: Money) {
        out.beginObject()
        out.name("amount").value(money.amount.toPlainString())
        out.name("currency").value(money.currencyUnit.code)
        out.endObject()
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Money {
        var amount: String? = null
        var currency: String? = null
        `in`.beginObject()
        while (`in`.hasNext()) {
            val nextName = `in`.nextName()
            if (nextName == "amount") {
                amount = `in`.nextString()
            } else if (nextName == "currency") {
                currency = `in`.nextString()
            }
        }
        `in`.endObject()
        return Money.of(
            CurrencyUnit.of(currency),
            BigDecimal(amount),
            RoundingMode.HALF_UP
        )
    }
}