package com.paysera.lib.accounts.entities.cards

import com.google.gson.annotations.SerializedName

data class PaymentCardDelivery(
    @SerializedName("preference_owner_id")
    val ownerId: Int,
    @SerializedName("shipping_address")
    val shippingAddress: CardShippingAddress,
    @SerializedName("delivery_type")
    val deliveryType: String
)