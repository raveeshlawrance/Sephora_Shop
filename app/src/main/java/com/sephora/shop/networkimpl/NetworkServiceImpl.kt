package com.sephora.shop.networkimpl

interface NetworkServiceImpl {
    fun success(response : Any)
    fun error(errorMsg: String?)
    fun exception(t: Throwable?)
}