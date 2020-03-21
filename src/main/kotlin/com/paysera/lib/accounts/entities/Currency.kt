package com.paysera.lib.accounts.entities

data class Currency(
    val code: String,
    val features: List<String>,
    val type: String
)