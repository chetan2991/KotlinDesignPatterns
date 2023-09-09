package com.chetan.kotlindesignpatterns.creational

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

abstract class Shape : Cloneable {
        var id : String? = null
        var type : String?  = null
        abstract fun draw()

    public override fun clone(): Any {
        var clone : Any? = null
        try {
            clone = super.clone()
        } catch (e : CloneNotSupportedException){
            e.printStackTrace()
        }
        return clone!!
    }
}
class Rectangle : Shape(){
    override fun draw() {
        println("In rectangle draw")
    }
    init {
        type = "Rectangle"
    }

}
class Square : Shape(){
    override fun draw() {
        println("In square draw")
    }
    init {
        type = "Square"
    }
}
class Circle : Shape(){
    override fun draw() {
        println("In circle draw")
    }
    init {
        type = "Circle"
    }
}
object ShapeCache{
    private val shapeMap = hashMapOf<String?, Shape>()

    fun loadCache(){
        val circle = Circle()
        val rectangle = Rectangle()
        val square = Square()

        shapeMap.put("1",circle)
        shapeMap.put("2",rectangle)
        shapeMap.put("3",square)

    }

    fun getShape(shapeId : String) : Shape {
        val cacheShape = shapeMap.get(shapeId)
        return cacheShape?.clone() as Shape
    }
}

class PrototypeTest{
    @Test
    fun prototypeTest(){
        ShapeCache.loadCache()
        val cloneShape1 = ShapeCache.getShape("1")
        val cloneShape2 = ShapeCache.getShape("2")
        val cloneShape3 = ShapeCache.getShape("3")

        cloneShape1.draw()
        cloneShape2.draw()
        cloneShape3.draw()

        assertThat(cloneShape1.type).isEqualTo("circle")
        assertThat(cloneShape2.type).isEqualTo("rectangle")
        assertThat(cloneShape3.type).isEqualTo("square")
    }
}