package com.sephora.shop.listener

import com.sephora.shop.model.productlist.ProductItem


interface ProductSelectListener {
    fun onSelectProduct(productItem: ProductItem?)
}