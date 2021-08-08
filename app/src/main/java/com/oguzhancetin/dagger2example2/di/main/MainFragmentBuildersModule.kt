package com.oguzhancetin.dagger2example2.di.main

import com.oguzhancetin.dagger2example2.ui.main.post.PostFragment
import com.oguzhancetin.dagger2example2.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment
}