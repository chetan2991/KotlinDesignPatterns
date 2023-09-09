package com.chetan.kotlindesignpatterns.creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AlertBox {
    var message : String?  = null
    fun show() {
        println("alertbox $this")
    }
}
class Window{
    val box by lazy {
        AlertBox()
    }
    fun showMessage( message : String ){
        box.message = message
        box.show()
    }
}

class Window2{
    lateinit var box : AlertBox
    fun showMessage( message : String ){
        box = AlertBox()
        box.message = message
        box.show()
    }
}
class WindowTest{
    @Test
    fun windowTest(){
        val window = Window()
        window.showMessage("show message")
        assertThat(window.box).isNotNull

        val window2 = Window2()
        window2.showMessage("show message")
        assertThat(window2.box).isNotNull
    }
}