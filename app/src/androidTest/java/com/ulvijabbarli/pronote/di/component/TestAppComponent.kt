package com.ulvijabbarli.pronote.di.component

import android.app.Application
import com.ulvijabbarli.pronote.di.base.component.AppComponent
import com.ulvijabbarli.pronote.di.module.TestAppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TestAppModule::class
    ]
)
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent

    }
}