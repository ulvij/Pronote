package com.ulvijabbarli.pronote

import androidx.test.platform.app.InstrumentationRegistry
import com.ulvijabbarli.pronote.di.module.TestAppModule

//class TestInjector(private val testAppModule: TestAppModule) {
//
//    fun inject() {
//        val instrumentation = InstrumentationRegistry.getInstrumentation()
//        val app = instrumentation.targetContext.applicationContext as TestApplication
//
//        DaggerTestApplicationComponent.builder().application(app).build()
//
//    }
//}