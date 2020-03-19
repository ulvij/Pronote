package com.ulvijabbarli.pronote.di.base.builder

import com.ulvijabbarli.pronote.di.base.PerActivity
import com.ulvijabbarli.pronote.di.main.MainFragmentProvider
import com.ulvijabbarli.pronote.di.main.MainModule
import com.ulvijabbarli.pronote.di.main.MainViewModelsModule
import com.ulvijabbarli.pronote.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            MainViewModelsModule::class,
            MainFragmentProvider::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}