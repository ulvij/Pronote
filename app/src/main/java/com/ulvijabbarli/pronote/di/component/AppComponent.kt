package com.ulvijabbarli.pronote.di.component

import android.app.Application
import com.ulvijabbarli.pronote.BaseApplication
import com.ulvijabbarli.pronote.di.builder.ActivityBuildersModule
import com.ulvijabbarli.pronote.di.module.AppModule
import com.ulvijabbarli.pronote.di.module.RoomModule
import com.ulvijabbarli.pronote.di.builder.ViewModelFactoryModule
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
        ViewModelFactoryModule::class,
        AppModule::class,
        RoomModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}