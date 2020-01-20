package com.paysera.lib.accounts.clients

import TestConfiguration
import com.paysera.lib.accounts.entities.cards.CategorizedAccountNumbersFilter
import com.paysera.lib.accounts.retrofit.NetworkApiFactory
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TransferTest {

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
    fun getTransfer() {
        val response = apiClient.getTransfer(TestConfiguration.existingTransferId).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getIbanInformation() {
        val response = apiClient.getIbanInformation(TestConfiguration.testIbanNumber).runCatchingBlocking()
        assert(response.isSuccess)
        assert(response.getOrNull() != null)
    }

    @Test
    fun getCategorizedAccountNumbers() {
        val response = apiClient.getCategorizedAccountNumbers(CategorizedAccountNumbersFilter()).runCatchingBlocking()
        assert(response.isSuccess)
    }

    @Test
    fun getTransferPurposeCodes() {
        val response = apiClient.getTransferPurposeCodes().runCatchingBlocking()
        assert(response.isSuccess)
    }
}