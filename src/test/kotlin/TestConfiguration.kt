import com.paysera.lib.common.entities.ApiCredentials
import com.paysera.lib.common.interfaces.TokenRefresherInterface
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.logging.HttpLoggingInterceptor

object TestConfiguration {

    // General variables
    val userAgent = "okhttp/3.12.1"
    val timeout: Long? = null
    val loggingLevel = HttpLoggingInterceptor.Level.BODY
    val baseUrl = "https://accounts.paysera.com/public/"
    val tokenRefresher = object : TokenRefresherInterface {
        override fun refreshToken(): Deferred<Any> {
            return CompletableDeferred(1)
        }

        override fun isRefreshing(): Boolean {
            return false
        }
    }

    // User credentials
    val apiCredentials = ApiCredentials("your_token")
    val userId = 123456 // your user id

    // Test variables
    // General tests
    val testAccountNumber = "your_test_evp_number" // e.g. "EVP......."
    val testCardAccountNumber = "your_card_evp_number" // e.g. "EVP......"
    val testIbanNumber = "your_iban" // e.g. "LTxxxxxxx....xx"
    val testCountry = "lt"


    // Authorizations test
    val authorizationUserIdList = listOf(1234, 5678)
    // Other Variables
    val existingTransferId = "transfer_id"

}
