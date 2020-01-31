package com.paysera.lib.accounts.entities.cards

import com.google.gson.annotations.SerializedName

data class PaymentCardExpiringOrderRestriction(
    @SerializedName("ordering_allowed")
    val orderingAllowed: Boolean,
    val reason: String
)