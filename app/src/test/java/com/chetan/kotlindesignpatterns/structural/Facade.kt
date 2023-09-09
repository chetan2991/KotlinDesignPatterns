package com.chetan.kotlindesignpatterns.structural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ComplexSystemStore(private val filePath : String ){
        private val cache : HashMap<String,String>

        init {

            cache = HashMap()
        }

    fun store(key: String, value : String ){
        cache[key] = value
    }
    fun read(key : String) = cache[key] ?: ""

    fun commit() = "storing cache data to file path $filePath"
}

data class User(val login : String)

//facade

class UserRepository{
    private val systemPreferences  = ComplexSystemStore("file/path")

    fun save(user : User ){
        systemPreferences.store("user_key",user.login)
        systemPreferences.commit()
    }

    fun findFirst() : User  = User(systemPreferences.read("user_key"))
}

class FacadeTest{
    @Test
    fun facadeTest(){
        val userRepo = UserRepository()
        val user  = User("John")
        userRepo.save(user)
        val finduser  = userRepo.findFirst()

        assertThat(finduser.login).isEqualTo("John")
    }
}