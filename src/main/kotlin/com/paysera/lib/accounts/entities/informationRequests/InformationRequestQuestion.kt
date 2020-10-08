package com.paysera.lib.accounts.entities.informationRequests

data class InformationRequestQuestion(
    val id: String,
    val question: String,
    val inputType: String,
    val required: Boolean,
    val answer: String?
) {
    enum class InformationRequestQuestionInputType(val value: String) {
        TEXT("text"),
        TEXT_AREA("textarea"),
        DATE("date"),
        FILE("file")
    }
}