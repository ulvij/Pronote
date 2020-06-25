package com.ulvijabbarli.pronote.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.ulvijabbarli.pronote.TestApplication

class CustomTestRunner:AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }

}