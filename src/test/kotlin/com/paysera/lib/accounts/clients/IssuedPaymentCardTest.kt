package com.paysera.lib.accounts.clients

import TestConfiguration
import com.paysera.lib.accounts.entities.CardLimit
import com.paysera.lib.accounts.entities.cards.CardsFilter
import com.paysera.lib.accounts.entities.cards.PaymentCardDesignFilter
import com.paysera.lib.accounts.entities.common.BaseFilter
import com.paysera.lib.accounts.retrofit.NetworkApiFactory
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IssuedPaymentCardTest {

    private lateinit var apiClient: AccountsApiClient

    @BeforeAll
    fun setUp() {
        apiClient = NetworkApiFactory(
            TestConfiguration.userAgent,
            TestConfiguration.apiCredentials,
            TestConfiguration.timeout,
            TestConfiguration.loggingLevel
        ).createClient(TestConfiguration.baseUrl, TestConfiguration.tokenRefresher)
    }

    @AfterAll
    fun tearDown() {
        apiClient.cancelCalls()
    }

    @Test
    fun getCards() {
        val response = apiClient.getCards(CardsFilter(listOf(TestConfiguration.testCardAccountNumber))).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getPaymentCardDesigns() {
        val response = apiClient.getPaymentCardDesigns(PaymentCardDesignFilter(TestConfiguration.userId, "natural")).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun createCard() {
    }

    @Test
    fun activateCard() {
    }

    @Test
    fun deactivateCard() {
    }

    @Test
    fun enableCard() {
    }

    @Test
    fun cancelCard() {
    }

    @Test
    fun getCardLimit() {
        val response = apiClient.getCardLimit(TestConfiguration.testCardAccountNumber).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun setCardLimit() {
        val response = apiClient.setCardLimit(TestConfiguration.testCardAccountNumber, CardLimit(Money.of(CurrencyUnit.EUR, BigDecimal("500")))).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardPin() {
    }

    @Test
    fun getCardShippingAddress() {
        val response = apiClient.getCardShippingAddress(TestConfiguration.testCardAccountNumber).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardOrderRestriction() {
        val response = apiClient.getCardOrderRestriction(TestConfiguration.testCardAccountNumber).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardDeliveryPrices() {
        val response = apiClient.getCardDeliveryPrices(TestConfiguration.testCountry).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardIssuePrice() {
        val response = apiClient.getCardIssuePrice(TestConfiguration.testCountry, "natural", TestConfiguration.userId).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardDeliveryDate() {
        val response = apiClient.getCardDeliveryDate(TestConfiguration.testCountry, "regular").runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getCardDeliveryCountries() {
        val response = apiClient.getCardDeliveryCountries(BaseFilter()).runCatchingBlocking()
        assert(response.isSuccess)
    }
}