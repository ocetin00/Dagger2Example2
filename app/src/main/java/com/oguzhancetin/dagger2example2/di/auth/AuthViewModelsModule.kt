package com.oguzhancetin.dagger2example2.di.auth

import androidx.lifecycle.ViewModel
import com.oguzhancetin.dagger2example2.di.ViewModelKey
import com.oguzhancetin.dagger2example2.ui.auth.AuthViewModel
import com.oguzhancetin.dagger2example2.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel


}