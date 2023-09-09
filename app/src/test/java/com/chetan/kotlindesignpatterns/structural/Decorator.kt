package com.chetan.kotlindesignpatterns.structural

import org.junit.Test

interface CofeeMachine{
    fun makeSmallCofee()
    fun makeLargeCofee()
}
//existing code
class NormalCofeeMachine : CofeeMachine{
    override fun makeSmallCofee() {
       println("normal cofee machine making small cofee")
    }

    override fun makeLargeCofee() {
        println("normal cofee machine making large cofee")

    }

}

//Decorator
class EnhanceCofeeMachine( private val cofeeMachine: CofeeMachine )  : CofeeMachine by cofeeMachine {
//overriding a behaviour
    override fun makeLargeCofee() {
    println("enhance cofee machine making large cofee")

}

    //extending behaviour
    fun makeMilkCofee(){
        println("enhance cofee machine making milk cofee")
        cofeeMachine.makeSmallCofee()
        println("enhance cofee machine adding milk to cofee")

    }
}

class DecoratorTest{

    @Test
    fun testDecorator(){
        val normalCofeeMachine = NormalCofeeMachine()
        val enhanceCofeeMachine = EnhanceCofeeMachine(normalCofeeMachine)
        enhanceCofeeMachine.makeSmallCofee()
        enhanceCofeeMachine.makeLargeCofee()
        enhanceCofeeMachine.makeMilkCofee()
    }



}