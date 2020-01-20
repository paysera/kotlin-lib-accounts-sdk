package com.paysera.lib.accounts.clients

import TestConfiguration
import com.paysera.lib.accounts.entities.authorizations.AuthorizationFilter
import com.paysera.lib.accounts.retrofit.NetworkApiFactory
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetAuthorizationTest {

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
    fun getAuthorizations() {
        val result = apiClient.getAuthorizations(AuthorizationFilter(accountNumbers = listOf(TestConfiguration.testAccountNumber))).runCatchingBlocking()
        assert(result.isSuccess)
    }
}