package com.ulvijabbarli.pronote

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import com.ulvijabbarli.pronote.di.component.DaggerTestApplicationComponent
import com.ulvijabbarli.pronote.di.component.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    private lateinit var appComponent: TestApplicationComponent

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    companion object {
        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                    as TestApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestApplicationComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }
}