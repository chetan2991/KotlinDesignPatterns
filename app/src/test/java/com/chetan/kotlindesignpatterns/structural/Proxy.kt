package com.chetan.kotlindesignpatterns.structural

import org.junit.Test

interface Image {
    fun displayImage()
}

class RealImage(private val fileName : String ) : Image{
    override fun displayImage() {
        println("realImage display")
    }
    private fun loadFromDisk(fileName: String){
        println("loading from disk")

    }
    init {
        loadFromDisk(fileName)
    }

}

class ProxyImage(private val fileName: String) : Image{
    private var realImage : RealImage?  = null
    override fun displayImage() {
        if(realImage==null)
            realImage = RealImage(fileName)

        realImage!!.displayImage()
    }

}
class ProxyTest{
    @Test
    fun testProxy(){
        val realImage = ProxyImage("")

        //loading image and then displaying
        realImage.displayImage()

        // only displaying image from cache not loading again
        realImage.displayImage()
    }
}