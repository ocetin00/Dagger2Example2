package com.oguzhancetin.dagger2example2.di.main

import com.oguzhancetin.dagger2example2.network.main.MainApi
import com.oguzhancetin.dagger2example2.ui.main.post.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    @MainScope
    fun provideMainApi(retrofit: Retrofit): MainApi{
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    @MainScope
    fun provideRecyclerAdapter (): PostRecyclerAdapter {
        return  PostRecyclerAdapter()
    }
}