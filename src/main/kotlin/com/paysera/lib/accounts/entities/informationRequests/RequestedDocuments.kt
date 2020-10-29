package com.paysera.lib.accounts.entities.informationRequests

data class RequestedDocuments(
    val question: String,
    val documents: List<String>
)