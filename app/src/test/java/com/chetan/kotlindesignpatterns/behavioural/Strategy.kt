package com.chetan.kotlindesignpatterns.behavioural

import org.junit.Test

/**
 * client decide what kind of strategy printer will follow
 */
class Printer(private val stringFormatterStrategy : (String)->String){
    fun printString(message : String ){
        println(stringFormatterStrategy(message))
    }
}
val lowerCaseFormatter = {it :String -> it.lowercase()}
val upperCaseFormatter = {it :String -> it.uppercase()}

class StrategyTest {
    @Test
    fun testStrategy(){
        val inputString = "My name IS CHETAN"
        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.printString(inputString)

        val upperCasePrinter = Printer(upperCaseFormatter)
        upperCasePrinter.printString(inputString)
    }
}