package com.ulvijabbarli.pronote.di

import androidx.lifecycle.ViewModelProvider
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}