package com.paysera.lib.accounts.entities.cards

data class CreatePaymentCardRequest (
    var cardOwnerId: Int? = null,
    var shippingAddress: CardShippingAddress? = null,
    var travelerAddress: CardShippingAddress? = null,
    var deliveryType: String? = null,
    var accountNumber: String? = null,
    var accountOwnerId: Int? = null,
    var chargeInfo: ChargeInfo? = null,
    var visualType: String? = null,
    var officeId: String? = null
)