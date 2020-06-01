package com.ulvijabbarli.pronote

import com.google.common.truth.Truth.assertThat
import com.ulvijabbarli.pronote.util.EmailValidator
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun subtraction_isCorrect() {
        assertEquals(3, 9 - 6)
    }

    @Test
    fun email_validator_returns_true(){
        assertThat(EmailValidator.isValidEmail("name@gmail.com")).isTrue()
    }

    @Test
    fun email_validator_returns_false(){
        assertThat(EmailValidator.isValidEmail("name@gmaicom")).isFalse()
    }

}
