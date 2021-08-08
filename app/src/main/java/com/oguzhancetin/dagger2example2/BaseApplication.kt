package com.oguzhancetin.dagger2example2


import com.oguzhancetin.dagger2example2.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return  DaggerAppComponent.builder().application(this).build()
    }
}