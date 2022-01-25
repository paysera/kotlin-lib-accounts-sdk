package com.paysera.lib.accounts.entities.transfers

data class PSAdditionalTransferInformation(
    val hash: String,
    val description: String?,
    val fundsOrigin: String?,
    val documents: List<PSTransferAmlDetailsDocument>?
)