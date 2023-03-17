package com.effectivem.android.presentation.di

import com.effectivem.android.presentation.login.LoginViewModel
import com.effectivem.android.presentation.page1.Page1ViewModel
import com.effectivem.android.presentation.profile.ProfileViewModel
import com.effectivem.android.presentation.register.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SignInViewModel(get(), get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { parameters -> ProfileViewModel(firstName = parameters.get(), get(), get()) }

    viewModel { parameters -> Page1ViewModel(firstName = parameters.get(), get(), get(), get()) }
}

