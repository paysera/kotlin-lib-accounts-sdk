package com.paysera.lib.accounts.entities.preciousMetals

import org.joda.money.Money

data class BullionOption(
    val identifier: String,
    val type: String,
    val photos: List<String>,
    val weights: List<BullionOptionWeight>,
    val fineness: Int,
    val dimensions: String,
    val maker: String,
    val countryOfOrigin: String,
    val preciousMetalType: String,
    val purchaseAmount: Money
) {

    object BullionOptionType {
        const val BAR = "bar"
        const val COIN = "coin"
    }
}