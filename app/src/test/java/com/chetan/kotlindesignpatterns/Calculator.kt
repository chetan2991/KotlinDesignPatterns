package com.chetan.kotlindesignpatterns

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Calculator {
    fun sum( a : Int, b : Int )  = a+b
}
class calculatorTest{

    @Test
    fun testSum(){
        val cal = Calculator()
        assertThat(cal.sum(5,4)).isEqualTo(9)
    }
}