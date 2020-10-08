package com.paysera.lib.accounts.entities.informationRequests

import com.paysera.lib.accounts.entities.transfers.TransferBeneficiary
import org.joda.money.Money
import java.util.*

data class TransferInformation(
    val amount: Money,
    val direction: String,
    val otherParty: TransferBeneficiary,
    val purposeDetails: String,
    val createdAt: Date?
)