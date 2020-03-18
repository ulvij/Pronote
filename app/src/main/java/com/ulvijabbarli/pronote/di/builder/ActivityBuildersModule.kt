package com.ulvijabbarli.pronote.di.builder

import com.ulvijabbarli.pronote.di.PerActivity
import com.ulvijabbarli.pronote.di.main.MainModule
import com.ulvijabbarli.pronote.di.main.MainViewModelsModule
import com.ulvijabbarli.pronote.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class, MainViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity

}