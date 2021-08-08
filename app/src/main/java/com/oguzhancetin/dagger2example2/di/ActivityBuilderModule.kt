package com.oguzhancetin.dagger2example2.di

import com.oguzhancetin.dagger2example2.di.auth.AuthModule
import com.oguzhancetin.dagger2example2.di.auth.AuthScoped
import com.oguzhancetin.dagger2example2.ui.auth.AuthActivity
import com.oguzhancetin.dagger2example2.di.auth.AuthViewModelsModule
import com.oguzhancetin.dagger2example2.di.main.MainFragmentBuildersModule
import com.oguzhancetin.dagger2example2.di.main.MainModule
import com.oguzhancetin.dagger2example2.di.main.MainScope
import com.oguzhancetin.dagger2example2.di.main.MainViewModelModule
import com.oguzhancetin.dagger2example2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @AuthScoped
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributeAndroidInjector() : AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,MainViewModelModule::class, MainModule::class]
    )
    abstract fun contributeMainInjector() : MainActivity



}