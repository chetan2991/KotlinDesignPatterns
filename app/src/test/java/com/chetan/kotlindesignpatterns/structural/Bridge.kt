package com.chetan.kotlindesignpatterns.structural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

interface Device {
    var valume : Int
    fun getName() : String
}

class Radio : Device {
    override var valume: Int = 0
    override fun getName() = "radio $this "
}
class TV : Device {
    override var valume = 0
    override fun getName() = "TV $this "
}
interface Remote{
    fun volumeUp()
    fun volumeDown()
}

class BasicRemote( val device: Device ): Remote{
    override fun volumeUp() {
        device.valume++
    }

    override fun volumeDown() {
        device.valume--
    }

}

class BridgeTest{
    @Test
    fun testBridge(){
        val tv  = TV()
        val radio = Radio()
        val tvRemote  = BasicRemote(tv)
        val radioRemote = BasicRemote(radio)

        tvRemote.volumeUp()
        tvRemote.volumeDown()
        tvRemote.volumeUp()


        radioRemote.volumeUp()
        radioRemote.volumeDown()
        radioRemote.volumeUp()
        assertThat(radio.valume).isEqualTo(1)
        assertThat(tv.valume).isEqualTo(1)
    }
}