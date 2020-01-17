package com.paysera.lib.accounts.entities.transfers

import org.joda.money.Money
import java.util.*

data class ConversionTransfer(
    val id: Int,
    val status: String,
    val details: String,
    val accountNumber: String,
    val clientId: Int,
    val from: Money,
    val to: Money,
    val type: String,
    val createdAt: Date?,
    val performAt: Date?
)