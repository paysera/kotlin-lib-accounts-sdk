package com.paysera.lib.accounts.entities.cards

import com.paysera.lib.common.entities.BaseFilter

data class CardDeliveryPricesFilter(
    val countryCode: String
) : BaseFilter()