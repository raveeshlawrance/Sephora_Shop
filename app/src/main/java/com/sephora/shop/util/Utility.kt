package com.sephora.shop.util

import android.content.Context


fun loadFromAsset(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use {
        it.readText()
    }
}

fun getOfferPrice(originalPrice: Int?, discPrice: Int?): String? {

    if(discPrice == originalPrice)
        return "$$originalPrice"
    var percentage : Float = ((originalPrice?.toFloat())!!.minus(discPrice!!.toFloat()))
    return "$" + originalPrice + "  $" + discPrice + "  (-" + (percentage?.div(originalPrice.toFloat()).times(100)) + "%)"
}