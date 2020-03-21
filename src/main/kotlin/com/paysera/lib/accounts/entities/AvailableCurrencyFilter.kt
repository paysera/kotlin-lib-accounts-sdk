package com.paysera.lib.accounts.entities

import com.paysera.lib.common.entities.BaseFilter

data class AvailableCurrencyFilter(
    val userId: Int
) : BaseFilter()