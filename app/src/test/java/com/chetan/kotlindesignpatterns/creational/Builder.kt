package com.chetan.kotlindesignpatterns.creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class  Component private constructor( builder: Builder){
    var param1 : String? = null
    var param2 : Int? = null
    var param3 : Boolean? = null

    class Builder {
        private var param1 : String? = null
        private var param2 : Int? = null
        private var param3 : Boolean? = null
        fun setParam1(param1 : String) = apply {  this.param1 = param1 }
        fun setParam2(param2 : Int) = apply {  this.param2 = param2 }
        fun setParam3(param3 : Boolean) = apply {  this.param3 = param3 }

        fun getParam1() = param1
        fun getParam2() = param2
        fun getParam3() = param3
        fun build() = Component(this)
    }
    init {

        param1 = builder.getParam1()
        param2 = builder.getParam2()
        param3 = builder.getParam3()
    }

}
class ComponentTest {

    @Test
    fun testBuilder(){
        val component = Component.Builder().setParam1("some value")
            .setParam2(1)
            .build()
        assertThat(component.param1).isEqualTo("some value")
        assertThat(component.param2).isEqualTo(1)
        assertThat(component.param3).isEqualTo(null)
    }
}