package com.sephora.shop.base

import androidx.lifecycle.ViewModel
import com.sephora.shop.di.DaggerViewModelInjector
import com.sephora.shop.di.RepositoryModule
import com.sephora.shop.di.ViewModelInjector
import com.sephora.shop.model.productlist.ProductListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(RepositoryModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ProductListViewModel -> injector.inject(this)
        }
    }
}