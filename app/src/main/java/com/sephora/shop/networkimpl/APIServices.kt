package com.sephora.shop.networkimpl

import com.sephora.shop.model.productlist.ProductListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface APIServices {
    @GET("api/v2.5/products?page[1]=1page[10]=30&include=featured_variant,featured_ad&filter[1]=sale&include=brand,option_types.option_values,featured_variant,featured_ad&sort=sales")
    fun getProductList() : Call<ProductListResponse>

}