package com.ulvijabbarli.pronote

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.ulvijabbarli.pronote.util.ClassUnderTest
import org.junit.Test

class TestWithRobolectric {

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val expectedResult = "HELLO_WORLD"

    @Test
    fun read_string_from_context_localized_string() {
        val myUnderTestObject = ClassUnderTest(context)

        val result: String = myUnderTestObject.getHelloWorldString()

        assertThat(result).isEqualTo(expectedResult)
    }
}