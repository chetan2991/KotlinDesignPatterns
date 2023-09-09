package com.chetan.kotlindesignpatterns.behavioural

import org.junit.Test

interface Command{
    fun execute()
}
class OrderAddCommand(val id  : Long ) : Command{
    override fun execute() {
        println("Added order id $id")
    }
}
class OrderPayCommand( val id : Long ) : Command{
    override fun execute() {
        println("order pay executed for order $id")
    }
}
class CommandProcessor{
    private val queue = arrayListOf<Command>()

    fun addToQueue(command: Command) : CommandProcessor = apply {
        queue.add(command)
    }
    fun processCommands() : CommandProcessor = apply {
        queue.forEach {
            it.execute()
        }
        queue.clear()
    }
}
class CommandTest{
    @Test
    fun testCommand(){
        CommandProcessor()
            .addToQueue(OrderAddCommand(1L))
            .addToQueue(OrderAddCommand(2L))
            .addToQueue(OrderPayCommand(1L))
            .addToQueue(OrderPayCommand(2L))
            .processCommands()
    }
}