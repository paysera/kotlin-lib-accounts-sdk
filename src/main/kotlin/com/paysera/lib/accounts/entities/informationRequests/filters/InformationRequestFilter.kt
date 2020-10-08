package com.paysera.lib.accounts.entities.informationRequests.filters

import com.paysera.lib.common.entities.BaseFilter

class InformationRequestFilter(
    val transferId: String?,
    val accountNumbers: List<String>?,
    val status: String?,
    val internalCommentRequired: Boolean?,
    limit: Int? = null,
    offset: Int? = null,
    orderBy: String? = null,
    orderDirection: String? = null,
    after: String? = null,
    before: String? = null
) : BaseFilter(
    limit = limit,
    offset = offset,
    orderBy = orderBy,
    orderDirection = orderDirection,
    after = after,
    before = before
)