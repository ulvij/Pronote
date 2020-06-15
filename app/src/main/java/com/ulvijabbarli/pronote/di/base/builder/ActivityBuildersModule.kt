package com.ulvijabbarli.pronote.di.base.builder

import com.ulvijabbarli.pronote.di.main.MainFragmentProvider
import com.ulvijabbarli.pronote.di.main.MainScope
import com.ulvijabbarli.pronote.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentProvider::class])
    abstract fun contributeMainActivity(): MainActivity

}