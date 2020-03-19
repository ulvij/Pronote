package com.ulvijabbarli.pronote.di.main

import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.di.base.PerActivity
import com.ulvijabbarli.pronote.di.base.ViewModelKey
import com.ulvijabbarli.pronote.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @PerActivity
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

}