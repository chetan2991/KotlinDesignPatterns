package com.chetan.kotlindesignpatterns.behavioural

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

interface HandlerChain{
    fun addHeader(inputHader : String) : String
}
class AuthenticationHeader(val token : String?, var next : HandlerChain? = null) : HandlerChain{
    override fun addHeader(inputHader: String) =
        "$inputHader/Autherization : $token".let {
            next?.addHeader(it) ?: it
        }

}
class ContentTypeHeader( val contentType : String?, var next: HandlerChain? = null ) : HandlerChain{
    override fun addHeader(inputHader: String) =
        "$inputHader/ contenType : $contentType".let {
            next?.addHeader(it) ?: it
        }
}

class BodyPlayLoadHeader(val body : String? , var next: HandlerChain? = null ): HandlerChain{
    override fun addHeader(inputHader: String) =
        "$inputHader\n $body".let {
            next?.addHeader(it) ?: it
        }
}
class ChainOfResponsibilitiesTest{

    @Test
    fun testChainOfResponsibilities(){
        val authenticationHeader = AuthenticationHeader("1232343")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPlayLoadHeader = BodyPlayLoadHeader("body \n username  : john ")
        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPlayLoadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Header with authentication")
        println(messageWithAuthentication)
        println("----------------------")

        val headerWithoutAuthentication = contentTypeHeader.addHeader("Header without authentication")
        println(headerWithoutAuthentication)

        assertThat(messageWithAuthentication).isEqualTo(
            """Header with authentication/Autherization : 1232343/ contenType : json
 body 
 username  : john 
            """.trimIndent()
        )

        assertThat(headerWithoutAuthentication).isEqualTo(
            """Header without authentication/ contenType : json
 body 
 username  : john 
            """.trimIndent()
        )
    }
}