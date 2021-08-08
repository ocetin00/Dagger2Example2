package com.oguzhancetin.dagger2example2.di.main

import androidx.lifecycle.ViewModel
import com.oguzhancetin.dagger2example2.di.ViewModelKey
import com.oguzhancetin.dagger2example2.ui.auth.AuthViewModel
import com.oguzhancetin.dagger2example2.ui.main.post.PostViewModel
import com.oguzhancetin.dagger2example2.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel
}