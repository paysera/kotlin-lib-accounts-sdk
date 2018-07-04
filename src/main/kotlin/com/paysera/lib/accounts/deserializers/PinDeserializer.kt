package com.paysera.lib.accounts.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.cards.Pin
import java.lang.reflect.Type

class PinDeserializer : JsonDeserializer<Pin> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Pin {
        val pinCode = json.asJsonObject.get("pin_code").asString
        return Pin(pinCode)
    }
}