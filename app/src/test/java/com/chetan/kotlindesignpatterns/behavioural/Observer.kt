package com.chetan.kotlindesignpatterns.behavioural

import org.junit.Test
import java.io.File

interface EventListener {
    fun update( eventType : String?, file : File? )
}
class EventManager( vararg operations : String ){
    var listeners  = hashMapOf<String, ArrayList<EventListener>>()
    init {

        for( operation in operations ){
            listeners[operation] = ArrayList<EventListener>()
        }
    }

    fun subscribe(eventType: String?, listener: EventListener ){
        val users = listeners.get(eventType)
        users?.add(listener)

    }
    fun unsubscribe(eventType: String?, listener: EventListener){
        val users = listeners.get(eventType)
        users?.remove(listener)
    }

    fun notify(eventType: String?, file: File?){
        val users = listeners.get(eventType)
        users?.let {
            for( listener in it ){
                listener.update(eventType, file)
            }
        }
    }
}
class Editor{
    var events = EventManager("open", "save")
    private var file : File? = null
    fun openFile(filePath : String ){
        file = File(filePath)
        events.notify("open",file)
    }
    fun saveFile(){
        file?.let {
            events.notify("save",it)
        }
    }
}

class EmailNotificationListener(private var email :String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email to $email some has perform $eventType with file ${file?.name}")
    }
}
class LogOpenListener(filename : String) : EventListener{
    override fun update(eventType: String?, file: File?) {
        println("log open for file $file some has perform $eventType with file ${file?.name}")
    }

}
class ObserverTest{
    @Test
    fun testOberver(){
        val editor = Editor()
        val logOpenListener = LogOpenListener("path/log.txt")
        val emailNotificationListener = EmailNotificationListener("test@gmail.com")
        editor.events.subscribe("open",logOpenListener)
        editor.events.subscribe("save",emailNotificationListener)
        editor.events.subscribe("open",emailNotificationListener)

        editor.openFile("test.txt")
        editor.saveFile()

    }
}