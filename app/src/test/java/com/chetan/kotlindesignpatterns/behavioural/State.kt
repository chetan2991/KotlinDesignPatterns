package com.chetan.kotlindesignpatterns.behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

sealed class AutherizationState
object UnAutherized : AutherizationState()
class Autherized(val userName : String ): AutherizationState()

class AutherizationPresenter{
    private var state : AutherizationState = UnAutherized
    val isAutherize : Boolean
        get() = when(state){
            is UnAutherized -> false
            is Autherized -> true
        }
    val username : String
        get() = when(val state = this.state){
            is Autherized -> state.userName
            is UnAutherized -> "Unknown"
        }

    fun loginUser(userName: String){
        state = Autherized(userName)
    }

    fun logOut(){
        state = UnAutherized
    }

    override fun toString(): String {
        return "user $username is login : $isAutherize"
    }
}
class StateTest{
    @Test
    fun testState(){
        val autherizationPresenter = AutherizationPresenter()
        autherizationPresenter.loginUser("admin")
        println(autherizationPresenter)

        assertThat(autherizationPresenter.isAutherize).isEqualTo(true)
        assertThat(autherizationPresenter.username).isEqualTo("admin")

        autherizationPresenter.logOut()
        println(autherizationPresenter)

        assertThat(autherizationPresenter.isAutherize).isEqualTo(false)
        assertThat(autherizationPresenter.username).isEqualTo("Unknown")
    }

}