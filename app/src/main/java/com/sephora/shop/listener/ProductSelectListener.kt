package com.sephora.shop.listener

import com.sephora.shop.model.productlist.ProductItem
import com.sephora.shop.model.productlist.ProductListResponse


interface ProductSelectListener {
    fun onSelectProduct(brandName: ProductListResponse?, productItem: ProductItem?)
}