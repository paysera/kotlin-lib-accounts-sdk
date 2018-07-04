package com.paysera.lib.accounts.clients

import com.paysera.lib.accounts.entities.Balance
import com.paysera.lib.accounts.entities.IbanInformation
import com.paysera.lib.accounts.entities.Limit
import com.paysera.lib.accounts.entities.cards.Card
import com.paysera.lib.accounts.entities.cards.CardsFilter
import com.paysera.lib.accounts.entities.cards.Cvv2
import com.paysera.lib.accounts.entities.cards.Pin
import com.paysera.lib.accounts.interfaces.TokenRefresherInterface
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import com.paysera.lib.accounts.retrofit.APIClient
import retrofit2.HttpException
import java.util.concurrent.TimeUnit.SECONDS

class AccountsApiClient(
    private val apiClient: APIClient,
    private val tokenRefresherInterface: TokenRefresherInterface
) {

    private val retryCondition = { errors: Observable<Throwable> ->
        errors.flatMap {
            val isUnauthorized = (it as HttpException).code() == 401
            if (isUnauthorized) {
                synchronized(tokenRefresherInterface) {
                    if (tokenRefresherInterface.isRefreshing()) {
                        Observable.timer(1, SECONDS).subscribeOn(Schedulers.io())
                    } else {
                        tokenRefresherInterface.refreshToken()
                    }
                }
            } else {
                Observable.error(it)
            }
        }
    }

    fun getIbanInformation(iban: String): Observable<IbanInformation> {
        return apiClient.getIbanInformation(iban).retryWhen(retryCondition)
    }

    fun getFullBalance(accountNumber: String): Observable<List<Balance>> {
        return apiClient.getFullBalances(accountNumber).retryWhen(retryCondition)
    }

    fun getCards(cardsFilter: CardsFilter): Observable<List<Card>> {
        return with(cardsFilter) {
            apiClient.getCards(accountNumbers, statuses, cardOwnerId, accountOwnerId).retryWhen(retryCondition)
        }
    }

    fun createCard(card: Card): Observable<Card> {
        return apiClient.createCard(card).retryWhen(retryCondition)
    }

    fun activateCard(cardId: String): Observable<Card> {
        return apiClient.activateCard(cardId).retryWhen(retryCondition)
    }

    fun deactivateCard(cardId: String): Observable<Card> {
        return apiClient.deactivateCard(cardId).retryWhen(retryCondition)
    }

    fun cancelCard(cardId: String): Observable<Card> {
        return apiClient.cancelCard(cardId).retryWhen(retryCondition)
    }

    fun getCardLimit(accountNumber: String): Observable<Limit> {
        return apiClient.getCardLimit(accountNumber).retryWhen(retryCondition)
    }

    fun setCardLimit(accountNumber: String, limit: Limit): Observable<Limit> {
        return apiClient.setCardLimit(accountNumber, limit).retryWhen(retryCondition)
    }

    fun getCardPin(cardId: String, cvv2: Cvv2): Observable<Pin> {
        return apiClient.getCardPin(cardId, cvv2).retryWhen(retryCondition)
    }
}