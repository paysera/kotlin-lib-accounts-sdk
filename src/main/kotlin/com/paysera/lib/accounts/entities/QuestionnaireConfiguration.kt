package com.paysera.lib.accounts.entities

data class QuestionnaireConfiguration(
    val isInitial: Boolean,
    val featureFlags: List<String>,
    val areasOfActivity: List<QuestionnaireAreaOfActivity>,
)