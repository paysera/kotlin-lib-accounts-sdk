package com.paysera.lib.accounts.entities.transfers

import com.google.gson.annotations.SerializedName

data class TransferPurposeCodes(
    @SerializedName("purpose_codes")
    val items: List<String>
)