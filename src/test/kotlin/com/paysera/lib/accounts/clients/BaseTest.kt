package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.retrofit.NetworkApiFactory
import com.paysera.lib.common.entities.ApiCredentials
import com.paysera.lib.common.interfaces.TokenRefresherInterface
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_METHOD)
open class BaseTest {

    companion object {
        private val userAgent = "okhttp/3.12.1"
        private val apiCredentials = ApiCredentials(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJldnBiYW5rIiwiaXNzIjoiYXV0aF9hcGkiLCJleHAiOjE1Nzk3MTM3MjIsImp0aSI6Il9wTzFGVktLS2lRZUdHZ0o4OFZXR1ZwQWhxWV9pWUdqIiwicHNyOnMiOlsibG9nZ2VkX2luIiwiY29uZmlybWVkX2xvZ19pbiJdLCJwc3I6dSI6IjE4NjYwMTIiLCJwc3I6c2lkIjoieEQzcldrUHVSTWNjZzl5RHh4dklTUzlYamRNUUE0aEciLCJwc3I6YSI6eyJ1c2VyX2lkIjoiMTg2NjAxMiJ9LCJpYXQiOjE1Nzk2NzA1MjJ9.vt_s1I3-8hwtAuMg6B_mfGAwkJgjquNNYUy9N79zgqXAEImJIRhRR74t6w-xm_owQAZCfHnktxclfsriCsPCP8Y_dnmgkbsVY97KB_qGbSwne7RHSm-JDID-zU6JQzMzArF59rBfyzH5koOsAMiM4Ttc-AoVwDg38nQi_fibY_XG7AAWKaGvphLlnvw3IPEZfwi2C9yxwV4cSMsMTh_HHpQVgQWtO5_-UE8ZSLYtE3EkarAqJadNYweIqbkz65ua123RfxTBX2VUS9QXfRRNVzm-YVo1QZ3vshKltt25IUGnHnpKIi8ltwW-S1m7QUY4LlKV3arsxkuhj2q3j3aJe7FU6as-KDpC03ZoYRptDaKbxcQVWSTuAmnM53JZ1_rozxuNDzr6AtGRtF14g-BZs7RbLbACcyHPwb-fW-PCQGQoNDKNpaGJVxunnB7eSrSvqpPw0Y1MgRE-DmeC6mboe8SbDfqhLJpY4HSurEodsbCHzIQkpXvvtmTr1Ts4M83G5QuGaNQdjrP4x50YzXwD8CwzXtdPYmvs1goO0C6IX5OoPSkxRxzkOetIEuV_wFvDP9m659DSHzZsqEzhWJsf6acdog-JZoFe-CdP_rmyPa9M4xJT1mqveltfaQkbf7Yfu4lSIQ1shLzpS9LEeMitrmYqIOCzZ6gNNbodVassifE"
        )
        private val timeout: Long? = null
        private val loggingLevel = HttpLoggingInterceptor.Level.BODY

        private val baseUrl = "https://accounts.paysera.com/public/"
        private val tokenRefresher = object : TokenRefresherInterface {
            override fun refreshToken(): Deferred<Any> {
                return CompletableDeferred(1)
            }

            override fun isRefreshing(): Boolean {
                return false
            }
        }
    }

    protected lateinit var apiClient: AccountsApiClient

    @BeforeAll
    open fun setUp() {
        apiClient = NetworkApiFactory(
            userAgent,
            apiCredentials,
            timeout,
            loggingLevel
        ).createClient(baseUrl, tokenRefresher)
    }

    @AfterAll
    open fun tearDown() {
        apiClient.cancelCalls()
    }
}