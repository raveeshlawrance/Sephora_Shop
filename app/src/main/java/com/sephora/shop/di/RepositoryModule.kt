package com.sephora.shop.di

import com.sephora.shop.networkimpl.APIServices
import com.sephora.shop.networkimpl.ProductRepository
import com.sephora.shop.networkimpl.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit


@Module
object RepositoryModule {

    @Provides
    internal fun provideRepository(apiServices: APIServices): ProductRepository {
        return ProductRepository(apiServices)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApiServices(retrofit: Retrofit): APIServices {
        return retrofit.create(APIServices::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return RetrofitBuilder.getRetrofit()
    }
}
