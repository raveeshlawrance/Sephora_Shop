package com.sephora.shop.model.productlist

import androidx.lifecycle.MutableLiveData
import com.sephora.shop.base.BaseViewModel
import com.sephora.shop.networkimpl.NetworkServiceImpl
import com.sephora.shop.networkimpl.ProductRepository
import javax.inject.Inject

class ProductListViewModel : BaseViewModel() {
    @Inject
    lateinit var productRepository: ProductRepository

    val _text = MutableLiveData<ProductListResponse>()
    fun getProductList() {
        productRepository.getProductList(object: NetworkServiceImpl {
            override fun success(response: Any) {
                var accountResponse : ProductListResponse = response as ProductListResponse
                _text.value = accountResponse
            }

            override fun error(errorMsg: String?) {
            }

            override fun exception(t: Throwable?) {
            }

        })

    }
}