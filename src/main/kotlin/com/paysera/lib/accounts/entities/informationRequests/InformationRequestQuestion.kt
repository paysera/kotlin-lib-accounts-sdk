package com.paysera.lib.accounts.entities.informationRequests

data class InformationRequestQuestion(
    val id: String,
    val question: String,
    val inputType: String,
    val required: Boolean,
    val answer: String?
) {
    enum class InformationRequestQuestionInputType(val value: String) {
        INPUT_TYPE_TEXT("text"),
        INPUT_TYPE_TEXT_AREA("textarea"),
        INPUT_TYPE_DATE("date")
    }
}