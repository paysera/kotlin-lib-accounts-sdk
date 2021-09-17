package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.cards.CardCvv2
import com.paysera.lib.common.exceptions.ApiError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CardOperationsTest : BaseTest() {

    private val cardId = "000"
    private val cvv2 = "000"

    @Test
    fun activateCard() {
        val response = apiClient.activateCard(cardId, CardCvv2(cvv2)).runCatchingBlocking()
        assert(response.isFailure && (response.exceptionOrNull() as ApiError).description == "Card not found")
    }
}