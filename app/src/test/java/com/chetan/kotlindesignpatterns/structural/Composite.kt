package com.chetan.kotlindesignpatterns.structural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

open class Equipment(
    open val price : Int,
    val name  : String
)

open class Composite(name: String ) : Equipment(0, name){
    private val equipments = ArrayList<Equipment>()
    override val price: Int
        get() = equipments.map { it.price }.sum()
    fun add(equipment: Equipment) = apply { equipments.add(equipment)}
}
class Computer : Composite("PC")
class Processor : Equipment(100,"Processor")
class HardDrive : Equipment(300,"HardDrive")
class Memory : Composite("Momory")
class ROM : Equipment(50   ,"Rom")
class RAM : Equipment(50,"Ram")

class CompositeTest{
    @Test
    fun testComposite()
    {
        val memory: Composite = Memory()
            .add(ROM())
            .add(RAM())

        val computer : Composite = Computer()
            .add(Processor())
            .add(HardDrive())

        assertThat(computer.name).isEqualTo("PC")
        assertThat(computer.price).isEqualTo(400)
    }
}