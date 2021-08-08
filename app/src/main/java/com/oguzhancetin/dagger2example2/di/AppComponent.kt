package com.oguzhancetin.dagger2example2.di

import android.app.Application
import com.oguzhancetin.dagger2example2.BaseApplication
import com.oguzhancetin.dagger2example2.SessionManager
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,ActivityBuilderModule::class,
    AppModule::class,ViewModelFactoryModule::class])
@Singleton
interface AppComponent : AndroidInjector<BaseApplication> {


    fun provideSessionManager(): SessionManager =   SessionManager()

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent


    }
}