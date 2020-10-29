package com.paysera.lib.accounts.entities.informationRequests

import java.util.*

data class InformationRequest(
    val id: String,
    val transferId: String,
    val transferInformation: TransferInformation?,
    val internalComment: String?,
    val comment: String?,
    val status: String,
    val createdAt: Date?,
    val answeredAt: Date?,
    val notifyClientBy: List<String>?,
    val questions: List<InformationRequestQuestion>,
    val requestedFrom: String,
    val files: List<InformationRequestFile>?,
    val requestedDocuments: RequestedDocuments
) {

    enum class InformationRequestStatus(val value: String) {
        WAITING_FOR_ANSWER("waiting_for_answer"),
        ANSWERED("answered"),
        COMPLETED("completed"),
        CANCELED("canceled")
    }

    enum class InformationRequestRequestedFrom(val value: String) {
        PAYER("payer"),
        BENEFICIARY("beneficiary")
    }
}