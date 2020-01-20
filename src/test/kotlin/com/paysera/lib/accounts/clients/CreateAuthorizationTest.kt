package com.paysera.lib.accounts.clients

import TestConfiguration
import com.paysera.lib.accounts.entities.authorizations.CreateAuthorizationRequest
import com.paysera.lib.accounts.retrofit.NetworkApiFactory
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CreateAuthorizationTest {

    private lateinit var apiClient: AccountsApiClient
    private var authorizationId: String? = null

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
        authorizationId?.let {
            apiClient.deleteAuthorization(it).runCatchingBlocking()
        }
        apiClient.cancelCalls()
        authorizationId = null
    }

    @Test
    fun createAuthorization() {
        val result = apiClient.createAuthorization(CreateAuthorizationRequest(
            accountNumber = TestConfiguration.testAccountNumber,
            userIds = TestConfiguration.authorizationUserIdList,
            readPermission = true,
            writePermission = false,
            signPermission = null
        )).runCatchingBlocking()
        assert(result.isSuccess)
        val authorization = result.getOrNull()
        assert(authorization != null)
        authorizationId = authorization?.id
    }
}