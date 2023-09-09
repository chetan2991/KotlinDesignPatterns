package com.chetan.kotlindesignpatterns.behavioural

import org.junit.Test

class ChatUser( private val chatMediator: Mediator, private val name : String){
    fun send(msg : String ){
        println("sending message from $name message is $msg ")
        chatMediator.sendMessage(msg,this)
    }
    fun receive(msg : String ){
        println("$name receive message : $msg")
    }
}
class Mediator{
    private var users = arrayListOf<ChatUser>()

    fun sendMessage( msg: String, user: ChatUser ){
        users.
                filter { user != it }
            .forEach { it.receive(msg) }
    }

    fun addUser(user: ChatUser ) : Mediator = apply { users.add(user)  }
}

class MediatorTest{
    @Test
    fun testMediator(){
        val mediator = Mediator()
        val alice = ChatUser(mediator, "Alice")
        val bob = ChatUser(mediator, "Bob")
        val carol = ChatUser(mediator, "Carol")

        mediator.addUser(alice)
            .addUser(bob)
            .addUser(carol)

        carol.send("Hi EveryOne")
    }
}