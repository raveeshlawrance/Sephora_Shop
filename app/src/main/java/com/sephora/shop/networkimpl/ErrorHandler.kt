package com.sephora.shop.networkimpl

import java.lang.Exception

interface ErrorHandler {
    fun handleError(ex : Exception) : Throwable
}