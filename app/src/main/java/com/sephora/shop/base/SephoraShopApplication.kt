package com.sephora.shop.base

import android.app.Application
import android.content.Context
import com.sephora.shop.di.ApplicationComponent
import com.sephora.shop.di.ApplicationModule
import com.sephora.shop.di.DaggerApplicationComponent
import java.security.*

class SephoraShopApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent
    private var instance: SephoraShopApplication? = null

    fun getApp(context: Context): SephoraShopApplication? {
        return context.applicationContext as SephoraShopApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build();
        applicationComponent.inject(this);
    }

    fun getStackAppContext(): Context? {
        return instance
    }
}