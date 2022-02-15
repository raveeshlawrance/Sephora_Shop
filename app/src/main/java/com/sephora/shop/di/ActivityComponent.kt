package com.sephora.shop.di

import com.sephora.shop.ui.details.ProductDetailActivity
import com.sephora.shop.ui.productlist.ProductListActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(productDetailActivity: ProductDetailActivity)
    fun inject(productListActivity: ProductListActivity)
}
