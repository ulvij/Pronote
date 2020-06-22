package com.ulvijabbarli.pronote.di.component

import android.app.Application
import com.ulvijabbarli.pronote.FakeNoteRepository
import com.ulvijabbarli.pronote.TestApplication
import com.ulvijabbarli.pronote.di.base.builder.ActivityBuildersModule
import com.ulvijabbarli.pronote.di.base.builder.ViewModelModule
import com.ulvijabbarli.pronote.di.module.TestAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        TestAppModule::class
    ]
)
interface TestApplicationComponent : AndroidInjector<TestApplication> {

    fun noteRepository():FakeNoteRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent

    }

}