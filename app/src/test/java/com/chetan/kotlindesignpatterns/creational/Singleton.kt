package com.chetan.kotlindesignpatterns.creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

object NetworkDriver {
    init {

        println("network driver init $this ")
    }
    fun log() : NetworkDriver = apply { println("netowork driver $this") }
}

class SingleTonTest{

    @Test
    fun testSingleton(){
        println("start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        assertThat(networkDriver1).isSameAs(NetworkDriver)
        assertThat(networkDriver2).isSameAs(NetworkDriver)

    }

}