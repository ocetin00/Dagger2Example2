package com.oguzhancetin.dagger2example2.di

import androidx.lifecycle.ViewModelProvider
import com.oguzhancetin.dagger2example2.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class ViewModelFactoryModule {

        @Binds
        abstract fun bindViewModelFactory(viewmodelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory



}
