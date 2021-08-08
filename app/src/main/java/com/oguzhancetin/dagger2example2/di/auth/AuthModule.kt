package com.oguzhancetin.dagger2example2.di.auth

import com.oguzhancetin.dagger2example2.network.auth.AuthAPi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    companion object{
        @AuthScoped
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthAPi? {
            return retrofit.create(AuthAPi::class.java)
        }


    }


}