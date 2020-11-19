package com.paysera.lib.accounts.entities.informationRequests

data class PSRequestedDocuments(
    val question: String,
    val documents: List<String>
)