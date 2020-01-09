package com.paysera.lib.accounts.serializers

import com.google.gson.*
import com.paysera.lib.accounts.entities.common.Metadata
import com.paysera.lib.accounts.entities.common.MetadataAwareResponse
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class MetadataAwareResponseDeserializer<T : Any> : JsonDeserializer<MetadataAwareResponse<T>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext): MetadataAwareResponse<T> {
        val items = arrayListOf<T>()
        val itemsKey = json?.asJsonObject?.keySet()?.firstOrNull { it != "_metadata" }

        json?.asJsonObject?.getAsJsonArray(itemsKey)?.forEach {
            items.add(context.deserialize(it, typeClass()))
        }

        val metadata = context.deserialize<Metadata>(
            json?.asJsonObject?.getAsJsonObject("_metadata"),
            Metadata::class.java
        )

        return MetadataAwareResponse(items, metadata)
    }

    @Suppress("UNCHECKED_CAST")
    private fun typeClass(): Class<T> {
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<T>)
    }
}