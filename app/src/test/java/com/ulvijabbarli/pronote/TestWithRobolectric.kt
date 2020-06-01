package com.ulvijabbarli.pronote

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ulvijabbarli.pronote.util.ClassUnderTest
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestWithRobolectric {

    private var context = ApplicationProvider.getApplicationContext<Context>()
    private val expectedResult = "HELLO_WORLD"

    @Test
    fun read_string_from_context_localized_string() {
        val myUnderTestObject = ClassUnderTest(context)

        val result: String = myUnderTestObject.getHelloWorldString()

        assertThat(result).isEqualTo(expectedResult)
    }
}