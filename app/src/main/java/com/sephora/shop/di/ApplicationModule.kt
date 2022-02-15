package com.sephora.shop.di

import android.app.Application
import android.content.Context
import dagger.Module

import dagger.Provides


@Module
class ApplicationModule(app: Application) {
    private val mApplication: Application = app

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }
}
