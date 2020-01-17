package com.paysera.lib.accounts.entities.transfers

import com.paysera.lib.accounts.entities.common.BaseFilter

data class ConversionTransferFilter(
    val accountNumberList: List<String>,
    val statuses: List<String>
) : BaseFilter()