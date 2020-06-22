package com.ulvijabbarli.pronote

import com.ulvijabbarli.pronote.di.base.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class PronoteApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}