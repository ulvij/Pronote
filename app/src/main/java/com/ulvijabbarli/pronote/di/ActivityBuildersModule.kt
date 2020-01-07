package com.ulvijabbarli.pronote.di

import com.ulvijabbarli.pronote.di.main.MainModule
import com.ulvijabbarli.pronote.di.main.MainViewModelsModule
import com.ulvijabbarli.pronote.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainModule::class, MainViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity

}