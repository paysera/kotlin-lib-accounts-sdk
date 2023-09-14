package com.paysera.lib.accounts.entities

data class QuestionnaireConfigurationItem(
    val configuration: QuestionnaireConfiguration
)

data class QuestionnaireConfiguration(
    val isInitial: Boolean,
    val featureFlags: List<String>?,
    val areasOfActivity: List<QuestionnaireAreaOfActivity>?
)