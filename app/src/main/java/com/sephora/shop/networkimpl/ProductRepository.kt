package com.sephora.shop.networkimpl

import androidx.lifecycle.MutableLiveData
import com.sephora.shop.model.productlist.ProductListResponse
import javax.inject.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Singleton
class ProductRepository @Inject constructor(apiServices: APIServices) {
    var apiServices: APIServices = apiServices

    fun getProductList(networkServiceImpl: NetworkServiceImpl): MutableLiveData<ProductListResponse> {
        val response = apiServices.getProductList()
        val accountResponse = MutableLiveData<ProductListResponse>()
        response?.enqueue(object : Callback<ProductListResponse> {
            override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<ProductListResponse>,
                response: Response<ProductListResponse>
            ) {
                if(response.isSuccessful)
                    networkServiceImpl.success(response.body()!!)
                else
                    networkServiceImpl.error(response.message())
            }
        })
        return accountResponse
    }
}