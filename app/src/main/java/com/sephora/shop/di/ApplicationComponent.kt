package com.sephora.shop.di

import android.app.Application
import android.content.Context
import com.sephora.shop.base.SephoraShopApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(stackHolderApplication: SephoraShopApplication?)
    @get:ApplicationContext
    val context: Context?
    val application: Application?
}
