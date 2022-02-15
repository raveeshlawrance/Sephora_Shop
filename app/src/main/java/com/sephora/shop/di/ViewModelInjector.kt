package com.sephora.shop.di

import com.sephora.shop.model.productlist.ProductListViewModel
import com.sephora.shop.networkimpl.APIServices
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(RepositoryModule::class)])
interface ViewModelInjector {
    fun inject(productListViewModel: ProductListViewModel)
    fun inject(apiServices: APIServices)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: RepositoryModule): Builder
    }
}