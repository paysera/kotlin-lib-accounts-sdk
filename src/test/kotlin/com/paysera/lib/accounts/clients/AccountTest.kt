package com.paysera.lib.accounts.clients

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import runCatchingBlocking

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AccountTest : BaseTest() {

    private val testAccountNumber = "EVP......"

    @Test
    fun getFullBalances() {
        val response = apiClient.getFullBalance(testAccountNumber).runCatchingBlocking()
        assert(response.isSuccess)
        assert(response.getOrNull()?.isNotEmpty() == true)
    }

    @Test
    fun getClientPartner() {
        val response = apiClient.getClientPartners(0).runCatchingBlocking()
        assert(response.isSuccess && response.getOrNull()?.items?.isNotEmpty() == true)
    }
}