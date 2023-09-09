package com.chetan.kotlindesignpatterns.behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

data class Memento(val state : String)
class Originator( var state : String ){
    fun createMemento() = Memento(state)
    fun restoreMemento( memento: Memento ){
        state = memento.state
    }
}
class CareTaker(){
    private var mementoList = arrayListOf<Memento>()
    fun saveState(state: Memento ){
        mementoList.add(state)
    }
    fun restore(index : Int ) = mementoList[index]
}
class MementoTest{
    @Test
    fun testMemento(){
        val originator = Originator("Initial state")
        val careTaker = CareTaker()
        careTaker.saveState(originator.createMemento())

        originator.state = "State 1"
        careTaker.saveState(originator.createMemento())
        originator.state = "State 2"
        careTaker.saveState(originator.createMemento())

        println("current state is ${originator.state}")

        assertThat(originator.state).isEqualTo("State 2")

        originator.restoreMemento(careTaker.restore(1))
        println("current state is ${originator.state}")
        assertThat(originator.state).isEqualTo("State 1")

        originator.restoreMemento(careTaker.restore(0))
        println("current state is ${originator.state}")
        assertThat(originator.state).isEqualTo("Initial state")

        originator.restoreMemento(careTaker.restore(2))
        println("current state is ${originator.state}")
        assertThat(originator.state).isEqualTo("State 2")




    }
}