package com.sephora.shop.util

import android.content.Context


fun loadFromAsset(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use {
        it.readText()
    }
}