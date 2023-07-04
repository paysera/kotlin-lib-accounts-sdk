package com.paysera.lib.accounts.entities

data class PSOfficeAddress(
    val name: String,
    val country: String,
    val city: String,
    val address: String,
    val postalCode: String,
    val officeId: String,
    val shipmentCode: String
)