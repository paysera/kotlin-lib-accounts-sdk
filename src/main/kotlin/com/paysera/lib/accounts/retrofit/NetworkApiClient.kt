package com.paysera.lib.accounts.retrofit

import com.paysera.lib.accounts.entities.*
import com.paysera.lib.accounts.entities.authorizations.*
import com.paysera.lib.accounts.entities.cards.*
import com.paysera.lib.accounts.entities.clientAllowances.PSClientAllowances
import com.paysera.lib.accounts.entities.informationRequests.PSInformationRequest
import com.paysera.lib.accounts.entities.informationRequests.PSInformationRequestAnswers
import com.paysera.lib.accounts.entities.informationRequests.PSInformationRequestFile
import com.paysera.lib.accounts.entities.informationRequests.PSInformationRequestUploadedFile
import com.paysera.lib.accounts.entities.preciousMetals.Bullion
import com.paysera.lib.accounts.entities.preciousMetals.BullionDealingCosts
import com.paysera.lib.accounts.entities.preciousMetals.BullionOption
import com.paysera.lib.accounts.entities.preciousMetals.UnallocatedBullionBalance
import com.paysera.lib.accounts.entities.preciousMetals.requests.BuyBullionItemRequest
import com.paysera.lib.accounts.entities.preciousMetals.requests.SellBullionItemRequest
import com.paysera.lib.accounts.entities.transfers.ConversionTransfer
import com.paysera.lib.accounts.entities.transfers.Transfer
import com.paysera.lib.accounts.entities.transfers.TransferBankParticipationInformation
import com.paysera.lib.common.entities.MetadataAwareResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface NetworkApiClient {

    // Account

    @POST("account/rest/v1/users/{userId}/accounts")
    fun createAccount(
        @Path("userId") userId: Int
    ): Deferred<Account>

    @PUT("account/rest/v1/accounts/{accountNumber}/activate")
    fun activateAccount(
        @Path("accountNumber") accountNumber: String
    ): Deferred<Account>

    @PUT("account/rest/v1/accounts/{accountNumber}/deactivate")
    fun deactivateAccount(
        @Path("accountNumber") accountNumber: String
    ): Deferred<Account>

    @PUT("account/rest/v1/accounts/{accountNumber}/descriptions")
    fun setDefaultAccountDescription(
        @Path("accountNumber") accountNumber: String,
        @Body request: SetDefaultAccountDescriptionRequest
    ): Deferred<Response<Void>>

    @GET("account/rest/v1/accounts/{accountNumber}/full-balance")
    fun getFullBalances(
        @Path("accountNumber") accountNumber: String,
        @Query("show_historical_currencies") showHistoricalCurrencies: Int
    ): Deferred<List<Balance>>

    @GET("currency/rest/v1/available-currencies")
    fun getAvailableCurrencies(
        @Query("account_owner_user_id") userId: Int,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Deferred<MetadataAwareResponse<AvailableCurrency>>

    // Questionnaire

    @GET("questionnaire/rest/v1/user/{userId}/questionnaire")
    fun getLastUserQuestionnaire(
        @Path("userId") userId: Int
    ): Deferred<Questionnaire>

    // Transfer

    @GET("transfer/rest/v1/transfers/{id}")
    fun getTransfer(
        @Path("id") id: String
    ): Deferred<Transfer>

    @GET("transfer/rest/v1/bank-information/{iban}")
    fun getIbanInformation(
        @Path("iban") iban: String
    ): Deferred<IbanInformation>

    @GET("transfer/rest/v1/bank-information/{iban}")
    fun getIbanInformation(
        @Path("iban") iban: String,
        @Query("currency") currency: String
    ): Deferred<IbanInformation>

    @GET("transfer/rest/v1/categorized-account-numbers")
    fun getCategorizedAccountNumbers(
        @Query("categories[]") categories: List<String>
    ): Deferred<List<CategorizedAccountNumbers>>

    @GET("transfer/rest/v1/purpose-codes")
    fun getTransferPurposeCodes(): Deferred<MetadataAwareResponse<String>>

    @GET("transfer/rest/v1/conversion-transfers")
    fun getConversionTransfers(
        @Query("account_number_list[]") accountNumberList: List<String>,
        @Query("statuses[]") statuses: List<String>
    ): Deferred<MetadataAwareResponse<ConversionTransfer>>

    @PUT("transfer/rest/v1/conversion-transfers/{transferId}/sign")
    fun signConversionTransfer(
        @Path("transferId") conversionTransferId: String
    ): Deferred<ConversionTransfer>

    @PUT("transfer/rest/v1/conversion-transfers/{transferId}/cancel")
    fun cancelConversionTransfer(
        @Path("transferId") conversionTransferId: String
    ): Deferred<ConversionTransfer>

    // Issued payment card

    @GET("issued-payment-card/v1/cards")
    fun getCards(
        @Query("account_numbers[]") accountNumbers: List<String>,
        @Query("statuses[]") statuses: List<String>,
        @Query("account_owner_id") accountOwnerId: String?,
        @Query("card_owner_id") cardOwnerId: String?
    ): Deferred<List<Card>>

    @GET("issued-payment-card/v1/card-designs")
    fun getPaymentCardDesigns(
        @Query("account_owner_id") accountOwnerId: Int?,
        @Query("client_type") clientType: String?
    ): Deferred<MetadataAwareResponse<PaymentCardDesign>>

    @POST("issued-payment-card/v1/cards")
    fun createCard(
        @Body card: CreatePaymentCardRequest
    ): Deferred<Card>

    @PUT("issued-payment-card/v1/cards/{id}/activate")
    fun activateCard(
        @Path("id") id: String
    ): Deferred<Card>

    @PUT("issued-payment-card/v1/cards/{id}/deactivate")
    fun deactivateCard(
        @Path("id") id: String
    ): Deferred<Card>

    @PUT("issued-payment-card/v1/cards/{id}/enable")
    fun enableCard(
        @Path("id") id: String
    ): Deferred<Card>

    @PUT("issued-payment-card/v1/cards/{id}/cancel")
    fun cancelCard(
        @Path("id") id: String
    ): Deferred<Card>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/card-limit")
    fun getCardLimit(
        @Path("accountNumber") accountNumber: String
    ): Deferred<CardLimit>

    @PUT("issued-payment-card/v1/accounts/{accountNumber}/card-limit")
    fun setCardLimit(
        @Path("accountNumber") accountNumber: String,
        @Body cardLimit: CardLimit
    ): Deferred<CardLimit>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/shipping-address")
    fun getCardShippingAddress(
        @Path("accountNumber") accountNumber: String
    ): Deferred<CardShippingAddress>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/card-order-restriction")
    fun getCardOrderRestriction(
        @Path("accountNumber") accountNumber: String
    ): Deferred<CardOrderRestriction>

    @PUT("issued-payment-card/v1/cards/{id}/pin")
    fun getCardPin(
        @Path("id") cardId: String,
        @Body cardCvv2: CardCvv2
    ): Deferred<CardPin>

    @GET("issued-payment-card/v1/card-delivery-prices/{country}")
    fun getCardDeliveryPrices(
        @Path("country") country: String
    ): Deferred<List<CardDeliveryPrice>>

    @GET("issued-payment-card/v1/card-account-issue-price")
    fun getCardIssuePrice(
        @Query("card_account_owner_id") cardAccountOwnerId: Int,
        @Query("card_owner_id") cardOwnerId: Int
    ): Deferred<CardIssuePrice>

    @GET("issued-payment-card/v1/card-issue-price/{country}/{clientType}/{cardOwnerId}")
    fun getCardIssuePrice(
        @Path("country") country: String,
        @Path("clientType") clientType: String,
        @Path("cardOwnerId") cardOwnerId: Int
    ): Deferred<CardIssuePrice>

    @GET("issued-payment-card/v1/card-delivery-date")
    fun getCardDeliveryDate(
        @Query("country") country: String,
        @Query("delivery_type") deliveryType: String
    ): Deferred<CardDeliveryDate>

    @GET("issued-payment-card/v1/card-delivery-countries")
    fun getCardDeliveryCountries(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Deferred<MetadataAwareResponse<String>>

    // Client allowance

    @GET("client-allowance/rest/v1/client-allowances/can-order-card")
    fun canOrderCard(
        @Query("user_id") userId: Int
    ): Deferred<ClientAllowance>

    @GET("client-allowance/rest/v1/client-allowances/can-fill-questionnaire")
    fun canFillQuestionnaire(
        @Query("user_id") userId: Int
    ): Deferred<ClientAllowance>

    // Permission

    @GET("permission/rest/v1/authorizations")
    fun getAuthorizations(
        @Query("account_numbers[]") accountNumbers: List<String>,
        @Query("valid_from") validFrom: Long?,
        @Query("valid_to") validTo: Long?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("order_by") orderBy: String?,
        @Query("order_direction") orderDirection: String?,
        @Query("replaced_authorization_ids") replacedAuthorizationIds: List<String>?
    ): Deferred<MetadataAwareResponse<Authorization>>

    @POST("permission/rest/v1/authorizations")
    fun createAuthorization(
        @Body authorization: CreateAuthorizationRequest
    ): Deferred<Authorization>

    @DELETE("permission/rest/v1/authorizations/{authorizationId}")
    fun deleteAuthorization(
        @Path("authorizationId") authorizationId: String
    ): Deferred<Response<Void>>

    @PUT("permission/rest/v1/authorizations/{authorizationId}")
    fun updateAuthorization(
        @Path("authorizationId") authorizationId: String,
        @Body authorization: CreateAuthorizationRequest
    ): Deferred<Authorization>

    @DELETE("permission/rest/v1/authorizations/{authorizationId}/users/{userId}")
    fun revokeUserAuthorization(
        @Path("authorizationId") authorizationId: String,
        @Path("userId") userId: Int
    ): Deferred<Response<Void>>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/card-delivery-preference")
    fun getPaymentCardDeliveryPreference(
            @Path("accountNumber") accountNumber: String
    ): Deferred<PaymentCardDelivery>

    @PUT("issued-payment-card/v1/accounts/{accountNumber}/card-delivery-preference")
    fun setPaymentCardDeliveryPreference(
            @Path("accountNumber") accountNumber: String,
            @Body paymentCardDelivery: PaymentCardDelivery
    ): Deferred<PaymentCardDelivery>

    @GET("issued-payment-card/v1/accounts/{accountNumber}/expiring-card-reorder-restriction")
    fun getPaymentCardExpiringOrderRestriction(
        @Path("accountNumber") accountNumber: String
    ): Deferred<PaymentCardExpiringOrderRestriction>

    @GET("permission/rest/v1/users/{userId}/limits")
    fun getUserSigningLimits(
        @Path("userId") userId: Int
    ): Deferred<UserLimits>

    @PUT("permission/rest/v1/authorizations/authorization-user-validation")
    fun getAuthorizationUserValidations(
        @Body body: AuthorizationUserValidationRequest
    ): Deferred<MetadataAwareResponse<UserValidationResult>>

    @GET("issued-payment-card/v1/card-order-restrictions")
    fun getCardOrderUserRestrictions(
        @Query("card_account_owner_id") cardAccountOwnerId: Int,
        @Query("card_owner_id") cardOwnerId: Int
    ): Deferred<MetadataAwareResponse<CardOrderRestriction>>

    // Precious metals

    @GET("bullion/rest/v1/item-options")
    fun getBullionOptions(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("order_by") orderBy: String?,
        @Query("order_direction") orderDirection: String?
    ): Deferred<MetadataAwareResponse<BullionOption>>

    @GET("bullion/rest/v1/items")
    fun getBullionItems(
        @Query("account_number") accountNumber: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("order_by") orderBy: String?,
        @Query("order_direction") orderDirection: String?
    ) : Deferred<MetadataAwareResponse<Bullion>>

    @GET("bullion/rest/v1/unallocated-balance")
    fun getUnallocatedBullionBalance(
        @Query("account_number") accountNumber: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("order_by") orderBy: String?,
        @Query("order_direction") orderDirection: String?
    ): Deferred<MetadataAwareResponse<UnallocatedBullionBalance>>

    @POST("bullion/rest/v1/items/buy")
    fun buyBullion(@Body request: BuyBullionItemRequest) : Deferred<Response<Void>>

    @POST("bullion/rest/v1/items/sell")
    fun sellBullion(@Body request: SellBullionItemRequest) : Deferred<Response<Void>>

    @GET("currency-exchange/rest/v1/currency-exchanges/spread-percentage")
    fun getBullionSpreadPercentage(
        @Query("account_number") accountNumber: String,
        @Query("from_currency") fromCurrency: String,
        @Query("to_currency") toCurrency: String,
        @Query("to_amount") toAmount: String
    ): Deferred<BullionDealingCosts>

    @GET("transfer/rest/v1/bank-participation/{swift}")
    fun getBankParticipationInformation(
        @Path("swift") swift: String
    ): Deferred<TransferBankParticipationInformation>

    @GET("client-allowance/rest/v1/client-allowances")
    fun getClientAllowances(): Deferred<List<PSClientAllowances>>

    @PUT("issued-payment-card/v1/cards/{id}/unblock-cvv")
    fun unblockCvv(
        @Path("id") cardId: String
    ): Deferred<Card>

    @GET("transfer-aml-information/rest/v1/information-requests")
    fun getInformationRequests(
        @Query("transfer_id") transferId: String?,
        @Query("account_numbers[]") accountNumbers: List<String>?,
        @Query("status") status: String?,
        @Query("internal_comment_required") internalCommentRequired: Boolean?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("order_by") orderBy: String?,
        @Query("order_direction") orderDirection: String?,
        @Query("after") after: String?,
        @Query("before") before: String?
    ): Deferred<MetadataAwareResponse<PSInformationRequest>>

    @GET("transfer-aml-information/rest/v1/information-requests/{id}")
    fun getInformationRequest(
        @Path("id") informationRequestId: String
    ): Deferred<PSInformationRequest>

    @POST("transfer-aml-information/rest/v1/information-requests")
    fun createInformationRequest(
        @Body informationRequest: PSInformationRequest
    ): Deferred<PSInformationRequest>

    @POST("transfer-aml-information/rest/v1/information-requests/{id}/files")
    fun uploadInformationRequestFile(
        @Path("id") informationRequestId: String,
        @Body file: PSInformationRequestFile
    ): Deferred<PSInformationRequestUploadedFile>

    @PUT("transfer-aml-information/rest/v1/information-requests/{id}/answer")
    fun answerInformationRequestQuestions(
        @Path("id") informationRequestId: String,
        @Body answers: PSInformationRequestAnswers
    ): Deferred<PSInformationRequest>
}